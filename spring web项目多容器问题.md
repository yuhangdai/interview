### Spring MVC启动顺序

- 部署描述文件中(eg.tomcat的web.xml)由``元素标记的事件监听器会被创建和初始化

- 对于所有事件监听器，如果实现了`ServletContextListener`接口，将会执行其实现的`contextInitialized()`方法

- 部署描述文件中由``元素标记的过滤器会被创建和初始化，并调用其`init()`方法``
- 部署描述文件中由``元素标记的servlet会根据``的权值按顺序创建和初始化，并调用其`init()`方法

![spring_mvc启动顺序](.\image\spring_mvc启动顺序.jpg)

​		ContextLoaderListener基于web上下文级别的监听器在web容器加载的时候就创建了applicationContext对象，并且将spring配置文件中的bean加载在ioc容器当中

​		DispatcherServlet是一个请求分发控制器，和其他servlet一样拦截来自url-pattern中定义的请求。其中load-on-startup 标签的含义是指在web容器启动时就制定了servlet被加载的顺序，他的值必须是一个整数，当是一个大于等于0的整数的时候，容器在配置的时候就加载并且初始化这个servler，数值越小，优先被加载。当是一个负整数，或者没有制定的时候，在该servlet被调用的时候才加载

​		**在web.xml中我们可以看到contextLoaderListener和DispatcherServlet都会去加载spring的xml文件，值得说明的是这两种方式加载的spring生成的application是两个独立的application**

### 两个applicationContext初始化时机
- ContextLoaderListener初始化  
    在ContextLoaderListener#contextInitialized时会创建一个root web application context   
    
    
    /**
     * Initialize the root web application context.
     */
    public void contextInitialized(ServletContextEvent event) {
        this.contextLoader = createContextLoader();
        if (this.contextLoader == null) {
            this.contextLoader = this;
        }
        this.contextLoader.initWebApplicationContext(event.getServletContext());
    }
​		

- DispatcherServlet初始化  
    在servlet#init方法中会调用子类initServletBean方法，此方法在FrameworkServlet中进行了重写
而FrameworkServlet#initServletBean时也会创建初始化一个WebApplicationContext(FrameworkServlet#initWebApplicationContext)

        /**
    	 * Overridden method of {@link HttpServletBean}, invoked after any bean properties
    	 * have been set. Creates this servlet's WebApplicationContext.
    	 */
    	@Override
    	protected final void initServletBean() throws ServletException {
    		getServletContext().log("Initializing Spring FrameworkServlet '" + getServletName() + "'");
    		if (this.logger.isInfoEnabled()) {
    			this.logger.info("FrameworkServlet '" + getServletName() + "': initialization started");
    		}
    		long startTime = System.currentTimeMillis();
    
    		try {
    			this.webApplicationContext = initWebApplicationContext();
    			initFrameworkServlet();
    		}
    		catch (ServletException ex) {
    			this.logger.error("Context initialization failed", ex);
    			throw ex;
    		}
    		catch (RuntimeException ex) {
    			this.logger.error("Context initialization failed", ex);
    			throw ex;
    		}
    
    		if (this.logger.isInfoEnabled()) {
    			long elapsedTime = System.currentTimeMillis() - startTime;
    			this.logger.info("FrameworkServlet '" + getServletName() + "': initialization completed in " +
    					elapsedTime + " ms");
    		}
    	}
    	

### 两个applicationContext作用
  一般在applicationContext.xml(ContextLoaderListener加载的applicationContext)负责扫描非controller的类 ，
而在spring-mvc.xml(DispatcherServlet加载的applicationContext)中只负责扫描controller的类。
![applicationContext-application](.\image\applicationContext-application.jpg)
![spring-mvc-application](.\image\spring-mvc-application.jpg)

  在DispatcherServlet中可以引用由ContextLoaderListener所创建的ApplicationContext中的内容，而反过来不行。
因此可能导致重复构造bean，bean无法正常注入以及applicationContext无法获取不在此作用域bean的问题。 
可以通过将所有bean加载进父容器，这样可以避免上面问题产生。
通过父亲WebApplicationContext扫描所有的bean，包括controller，
即都在applicationContext.xml里加载，而servlet-context.xml里配置为空即可。

![application-context_xml](.\image\application-context_xml.jpg)
![web_xml](.\image\web_xml.jpg)



**参考资料**  
[Spring MVC传统两个applicationContext的继承关系](https://blog.csdn.net/Julycaka/article/details/79287812) 
[SpringMVC 启动流程及相关源码分析](https://www.jianshu.com/p/dc64d02e49ac)
   	