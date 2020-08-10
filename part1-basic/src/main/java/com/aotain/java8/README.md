### 接口默认方法
引入原因：之前的接口面向抽象而不是具体编程，当需要修改接口时，其所有的实现类均需要修改。
为了尽量减少对原有代码的影响，特别是jdk中代码的影响，引入了接口默认方法。
目前的 java 8 之前的集合框架没有 foreach 方法，通常能想到的解决办法是在JDK里给相关的接口添加新的方法及实现。
然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。所以引进的默认方法。
他们的目的是为了解决接口的修改与现有的实现不兼容的问题
### 枚举类抽象方法
使用枚举类抽象方法可以实现策略模式。每个不同的枚举类型调用自己对应的实现方法
    
    
### 匿名内部类
不管是抽象类还是接口，都可以通过匿名内部类的方式访问。
不能通过抽象类或者接口直接创建对象。对于上面通过匿名内部类方式访问接口，我们可以这样理解：一个内部类实现了接口里的抽象方法并且返回一个内部类对象，之后我们让接口的引用来指向这个对象   
    
### Lambda表达式
Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
使用 Lambda 表达式可以使代码变的更加简洁紧凑。 
   
    lambda表达式语法
       (parameters) ->  expression 
       (parameters) -> { statements; }
  
lambda表达式重要特征
- 可选类型声明：不需要声明参数类型，编译器可以同一识别参数值；
- 可选参数圆括号：一个参数无需定义圆括号，多个参数需要定义圆括号；
- 可选大括号：如果主体只包含一个表达式，可以省略大括号；
- 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明确表达式返回了一个数值。
    
变量作用域
    
   
### 函数式接口
- 函数式接口是指仅仅包含一个抽象方法的接口，但是可以有多个非抽象方法接口。
- 引入函数式接口是为了方便lambda表达式使用。
- 使用函数式接口创建匿名类时，可以通过将lambda表达式当作函数参数传递给函数式接口（只有一个抽象方法）
- 这样可以在原有的接口中使用lambda表达式创建匿名类，简化代码
    
#### 内置函数式接口

Predicate: Predicate 接口是只有一个参数的返回布尔类型值的 断言型 接口
    （parameter）-> { statement;} 用于判断参数是否满足statement条件
    
    Predicate<List> predicate = (list) -> list==null || list.isEmpty() 
    // 判断list是否为空
    predicate.test(Lists.newArrayList());
      
Function:Function 接口接受一个参数并生成结果。默认方法可用于将多个函数链接在一起（compose, andThen)

Supplier:Supplier 接口产生给定泛型类型的结果,Supplier 接口不接受参数

Consumer:Consumer 接口表示要对单个输入参数执行的操作

Comparator:Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法


### Optionals
[参考资料1](https://blog.kaaass.net/archives/764)
[参考资料2](https://www.cnblogs.com/rjzheng/p/9163246.html)


### Objects.requireNonNull(mapper);

### Stream
- stream() 为集合创建串行流。
- parallelStream() 为集合创建并行流。
- filter() 用于通过设置的条件过滤出元素
- limit() 用于获取指定数量的流
- sorted() 用于对流进行排序

Collectors 类实现了很多归约操作，
例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串