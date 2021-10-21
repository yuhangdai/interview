### mybatis原理

#### mybatis四大对象
ParameterHandler：处理SQL的参数对象
ResultSetHandler：处理SQL的返回结果集
StatementHandler：数据库的处理对象，用于执行SQL语句
Executor：MyBatis的执行器，用于执行增删改查操作

#### mybatis原始调用

1.解析mybatis-config.xml配置文件，根据配置文件信息生成Configuration对象并注入SqlSessionFactory
    解析xml时生成的两个关键对象:Configuration、PreparedStatement
2.通过SqlSessionFactory生成sqlSession对象
    
3.通过sqlSession对象执行数据库操作

```
public static void main(String[] args) throws Exception {
    /******************************构造******************************/
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //创建SqlSessionFacory
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    /******************************分割线******************************/
    /******************************执行******************************/
    //SqlSession是帮我们操作数据库的对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    //获取Mapper
    DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
    Map<String,Object> map = new HashMap<>();
    map.put("id","123");
    System.out.println(mapper.selectAll(map));
    sqlSession.close();
    sqlSession.commit();
}
```

#### mybatis执行流程
- 读取mybatis-config和mapper.xml生成Configuration和MapperedStatement
- 执行sql


1.解析xml构造Configuration对象，并将其注入SqlSessionFactory;
2.SqlSessionFactory根据Configuration对象中的数据库相关信息创建SqlSession对象;
3.SqlSession对象获取mapper代理类;
```
    代理对象获取:
    1.解析mapper.xml文件时，将对应的mapper信息注册到MapperRegistry<Class<?>, MapperProxyFactory<?>>；
    2.根据mapper接口信息生成动态代理对象

    private final Class<T> mapperInterface;
    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
    }
    
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }
    
```
4.调用mapper对应的方法(调用代理对象的方法)
```
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      if (Object.class.equals(method.getDeclaringClass())) {
        // Object方法直接调用
        return method.invoke(this, args);
      } else {
        // 其它方法调用cachedInvoker
        return cachedInvoker(method).invoke(proxy, method, args, sqlSession);
      }
    } catch (Throwable t) {
      throw ExceptionUtil.unwrapThrowable(t);
    }
  }

    private MapperMethodInvoker cachedInvoker(Method method) throws Throwable {
        try {
          // A workaround for https://bugs.openjdk.java.net/browse/JDK-8161372
          // It should be removed once the fix is backported to Java 8 or
          // MyBatis drops Java 8 support. See gh-1929
          MapperMethodInvoker invoker = methodCache.get(method);
          if (invoker != null) {
            return invoker;
          }
    
          return methodCache.computeIfAbsent(method, m -> {
            if (m.isDefault()) {
              try {
                if (privateLookupInMethod == null) {
                  return new DefaultMethodInvoker(getMethodHandleJava8(method));
                } else {
                  return new DefaultMethodInvoker(getMethodHandleJava9(method));
                }
              } catch (IllegalAccessException | InstantiationException | InvocationTargetException
                  | NoSuchMethodException e) {
                throw new RuntimeException(e);
              }
            } else {
              return new PlainMethodInvoker(new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
            }
          });
        } catch (RuntimeException re) {
          Throwable cause = re.getCause();
          throw cause == null ? re : cause;
        }
      }

        
```

mapper调用执行过程

- 调用mapper类中继承自Object方法，直接执行
- m是default方法，构造DefaultMethodInvoker（jdk8以后接口可以存在default方法）
- 构造PlainMethodInvoker(走mybatis执行逻辑),PlainMethodInvoker.invoke()方法实际调用mapperMethod.execute()方法
```java
private static class PlainMethodInvoker implements MapperMethodInvoker {
    private final MapperMethod mapperMethod;

    public PlainMethodInvoker(MapperMethod mapperMethod) {
      super();
      this.mapperMethod = mapperMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args, SqlSession sqlSession) throws Throwable {
      return mapperMethod.execute(sqlSession, args);
    }
}
```
- **```mapperMethod.execute()执行流程```**
mapper.xml中一个select(update、delete)对应一个MappedStatement对象

根据MappedStatement获取CacheKey;
根据mappedStatement是否启用cache确定是否查询二级缓存（二级缓存的作用域是namespace，也可以理解为这里的ms）；
存在直接获取二级缓存数据；不存在查询数据库；

```
public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
    ErrorContext.instance().resource(ms.getResource()).activity("executing a query").object(ms.getId());
    if (closed) {
      throw new ExecutorException("Executor was closed.");
    }
    if (queryStack == 0 && ms.isFlushCacheRequired()) {
      clearLocalCache();
    }
    List<E> list;
    try {
      queryStack++;
      list = resultHandler == null ? (List<E>) localCache.getObject(key) : null;
      if (list != null) {
        handleLocallyCachedOutputParameters(ms, key, parameter, boundSql);
      } else {
        list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
      }
    } finally {
      queryStack--;
    }
    if (queryStack == 0) {
      for (DeferredLoad deferredLoad : deferredLoads) {
        deferredLoad.load();
      }
      // issue #601
      deferredLoads.clear();
      if (configuration.getLocalCacheScope() == LocalCacheScope.STATEMENT) {
        // issue #482
        clearLocalCache();
      }
    }
    return list;
}
```

查询数据库流程
构造StatementHandler
构造PreparedStatement
执行sql使用ResultHandler处理结果
```
@Override
  public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
    Statement stmt = null;
    try {
      Configuration configuration = ms.getConfiguration();
      // 构造StatementHandler
      StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
      // 构造PreparedStatement （包括设置参数）
      stmt = prepareStatement(handler, ms.getStatementLog());
      // 执行sql
      return handler.<E>query(stmt, resultHandler);
    } finally {
      closeStatement(stmt);
    }
  }

```

```
CacheKey获取方法
cacheKey.update(ms.getId()); // 方法全路径名
cacheKey.update(rowBounds.getOffset()); // 分页查询offset
cacheKey.update(rowBounds.getLimit()); // 分页查询limit
cacheKey.update(boundSql.getSql());   // 查询sql语句
// parameter参数
// environmentId
```

mybatis缓存机制
- 一级缓存
一级缓存是SqlSession级别的缓存，在操作数据库时需要构造 sqlSession对象，在对象中有一个(内存区域)数据结构（HashMap）用于存储缓存数据。
不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的

**如果多个请求同一个事务中，那么多个请求都在共用一个SqlSession，反之每个请求都会创建一个SqlSession**

生命周期
a、MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象。
Executor对象中持有一个新的PerpetualCache对象；当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。
b、如果SqlSession调用了close()方法，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用。
c、如果SqlSession调用了clearCache()，会清空PerpetualCache对象中的数据，但是该对象仍可使用。
d、SqlSession中执行了任何一个update操作(update()、delete()、insert()) ，都会清空PerpetualCache对象的数据，
但是该对象可以继续使用

- 二级缓存
MyBatis的二级缓存是Application级别的缓存，它可以提高对数据库查询的效率，以提高应用的性能

二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession去操作数据库得到数据会存在二级缓存区域，
多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。

二级缓存更新节点：
映射语句文件中的所有select语句将会被缓存。
映射语句文件中的所欲insert、update和delete语句会刷新缓存。
缓存会使用默认的Least Recently Used（LRU，最近最少使用的）算法来收回。
根据时间表，比如No Flush Interval,（CNFI没有刷新间隔），缓存不会以任何时间顺序来刷新。
缓存会存储列表集合或对象(无论查询方法返回什么)的1024个引用
缓存会被视为是read/write(可读/可写)的缓存，意味着对象检索不是共享的，而且可以安全的被调用者修改，不干扰其他调用者或线程所做的潜在修改

参考文档
[手把手带你阅读Mybatis源码（一）构造篇](https://www.cnblogs.com/javazhiyin/p/12340498.html)
[手把手带你阅读Mybatis源码（二）执行篇](https://www.cnblogs.com/javazhiyin/p/12344651.html)