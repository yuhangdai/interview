## spring容器
### spring容器重要接口
- BeanFactory


    The root interface for accessing a Spring bean container
    This is the basic client view of a bean container;
    
- BeanDefinition


        A BeanDefinition describes a bean instance, which has property values,
    constructor argument values, and further information supplied by
    concrete implementations.    
    
- ApplicationContext


    Central interface to provide configuration for an application.
    This is read-only while the application is running, but may be
    reloaded if the implementation supports this.
    
    
#### spring容器启动及加载流程

1. Prepare this context for refreshing  
刷新预处理
2. Tell the subclass to refresh the internal bean factory  
刷新内部bean factory
    - 存在，则先销毁，后创建；否则直接创建bean factory子类
    - 加载xml信息读取到document对象
    - 解析document对象，完成BeanDefinition注册    
3. Prepare the bean factory for use in this context.
4. Allows post-processing of the bean factory in context subclasses.
5. Invoke factory processors registered as beans in the context.
6. Register bean processors that intercept bean creation.
7. Initialize message source for this context.
8. Initialize event multicaster for this context.
9. Initialize other special beans in specific context subclasses.
10. Check for listener beans and register them.
11. Instantiate all remaining (non-lazy-init) singletons.
    * 获取beanName集合（Iterate over a copy to allow for init methods which in turn register new bean definitions.
                   	 While this may not be part of the regular factory bootstrap, it does otherwise work fine.）
    * 触发所有非懒加载bean初始化过程（Trigger initialization of all non-lazy singleton beans...）
        通过&前缀来区分FactoryBean和普通的Bean实例
        - factoryBean（FactoryBean是一个只能生产指定对象的小工厂，而且这个小工厂还被大工厂给管理）
        - 普通bean
            DefaultListableBeanFactory#getBean --> AbstractBeanFactory#doGetBean
            
            1. 从缓存中获取单例bean，bean不为空（Eagerly check singleton cache for manually registered singletons.）
                 AbstractBeanFactory#getSingleton --> 
                            DefaultSingletonBeanRegistry#getSingleton解决bean循环依赖问题
                            (Return the (raw) singleton object registered under the given name.
                             <p>Checks already instantiated singletons and also allows for an early
                             reference to a currently created singleton (resolving a circular reference).)
                a. 存在则判断是否创建完成,若还在进行中，直接返回缓存中的bean（Returning cached instance of singleton bean）
                b. 若已经完成，则AbstractBeanFactory#getObjectForBeanInstance
            2. bean为空；需要通过BeanDefinition来实例化一个Bean返回
                Guarantee initialization of beans that the current bean depends on.
                Create bean instance.     
                
                创建bean主要工作 AbstractAutowireCapableBeanFactory#doCreateBean
                - 实例化
                  默认调用无参构造实例化Bean，构造参数依赖注入就是发生在这个步骤
                - 属性填充（DI依赖注入发生在此步骤）
                  利用反射和内省技术进行属性设置
                - 初始化（AOP发生在此步骤）
                              
12. Last step: publish corresponding event.

#### spring启动过程中需要解决问题

* 循环依赖问题
    
    三级缓存
    
    - 单例对象工厂的cache，缓存第一步实例化后的对象
    /** Cache of singleton factories: bean name --> ObjectFactory */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
    - 提前曝光的单例对象的Cache ，缓存第二步填充属性后的对象（用于检测循环引用，与singletonFactories互斥）
    /** Cache of early singleton objects: bean name --> bean instance */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    - 单例对象的cache，缓存第三步初始化完成后的对象
    /** Cache of singleton objects: bean name --> bean instance */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
        
    bean 实例化三个关键步骤（AbstractAutowireCapableBeanFactory.doCreateBean()）
    - 调用 createBeanInstance 方法进行实例化对象(Instantiate the bean.)
    - 调用 populateBean 方法进行依赖注入
    - 调用 initializeBean 方法执行 BeanPostProcessor 后置处理器
    
    java bean创建过程
    
    AbstractBeanFactory#getBean-->doGetBean-->DefaultSingletonBeanRegistry#getSingleton
    -->AbstractAutowireCapableBeanFactory#createBean-->doCreateBean
    
    
    
    /**
     * Return the (raw) singleton object registered under the given name.
     * <p>Checks already instantiated singletons and also allows for an early
     * reference to a currently created singleton (resolving a circular reference).
     * @param beanName the name of the bean to look for
     * @param allowEarlyReference whether early references should be created or not
     * @return the registered singleton object, or {@code null} if none found
     */
    @Nullable
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }
    
    
               
* 属性赋值实现（简单类型 集合...）

[参考资料1](https://juejin.im/post/6844904014337802248#heading-2)  
[循环引用问题](https://juejin.im/post/6865575492867588110)
[循环引用问题](https://cloud.tencent.com/developer/article/1497692)


### springmvc启动多applicationContext问题

[web项目多applicationContext容器问题](https://blog.csdn.net/Julycaka/article/details/79287812)

### spring bean循环引用
使用构造器注入方式无法解决；
属性注入可以正常生成bean对象，但是如果没有重写toString()方法，
输出对象会导致Stack Over Flow

循环引用bean创建流程
```
    Creating shared instance of singleton bean 'student'
    Creating instance of bean 'student'
    Eagerly caching bean 'student' to allow for resolving potential circular references
    Creating shared instance of singleton bean 'course'
    Creating instance of bean 'course'
    Returning eagerly cached instance of singleton bean 'student' that is not fully initialized yet - a consequence of a circular reference
    Finished creating instance of bean 'course'
    Finished creating instance of bean 'student'
    Returning cached instance of singleton bean 'course'
    Returning cached instance of singleton bean 'course'
```