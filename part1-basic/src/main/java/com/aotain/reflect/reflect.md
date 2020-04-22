#### 获取Class对象的方式

1.知道具体类的情况下可以使用
Class alunbarClass = TargetObject.class;

2.通过 Class.forName()传入类的路径获取：
Class alunbarClass1 = Class.forName("cn.javaguide.TargetObject");

#### 通过反射获取类的属性和方法
通过class类我们可以
1.获取类中所有属性和方法
2.获取调用指定方法
3.获取指定参数并对其修改
4.修改属性访问属性
5.调用private方法

##### 参考文档
    1.[深入解析Java反射（1） - 基础](https://www.sczyh30.com/posts/Java/java-reflection-1/)