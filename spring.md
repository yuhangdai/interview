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
    - 存在则先销毁，会创建；否则直接创建bean factory子类
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
    *
    *
    *
12. Last step: publish corresponding event.

#### spring启动过程中需要解决问题

* 循环依赖问题
*

[参考资料1](https://juejin.im/post/6844904014337802248#heading-2)



