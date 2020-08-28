## mysql优化

### mysql慢查询日志查看
查询慢查询是否开启以及慢查询日志存放路径

    show variables like 'slow_query%';
查询慢查询时间设置

    show variables like 'long_query_time';
    
### mysql in和exists
[参考文档1](https://blog.csdn.net/baidu_37107022/article/details/77278381)
[参考文档2](https://cloud.tencent.com/developer/article/1144244)
exists和in执行逻辑  

exists对外层数据进行循环，并根据后面的exists条件逐条判断，如果为true返回此条记录，否则不返回；
exists对外表用loop逐条查询，每次查询都会查看exists的条件语句，当exists里的条件语句能够返回记录行时（无论记录行是的多少，只要能返回），条件就为真，返回当前loop到的这条记录；
反之，如果exists里的条件语句不能返回记录行，则当前loop到的这条记录被丢弃，exists的条件就像一个bool条件，当能返回结果集则为true，不能返回结果集则为false 

**如果A表有n条记录，那么exists查询就是将这n条记录逐条取出，然后判断n遍exists条件**  

in查询相当于多个or条件的叠加
**in查询就是先将子查询条件的记录全都查出来，假设结果集为B，共有m条记录，然后再将子查询条件的结果集分解成m个，再进行m次查询** 
    
    select * from A where cc in(select cc from B) //主要使用A表索引，适用于A表大B表小情况
    select * from A where exists(select cc from B where cc=A.cc) //主要使用B表索引，适用于B表大A表小情况

    
    //如果cc字段是通过函数生成那where条件会失效
    select * from A where exists(select fun(xx) AS cc from B where cc=A.cc)
    
    
### mysql sum(format)数据异常问题
format(1230,2)-->1,230.00 （超过三位数）会增加逗号
然后对其结果进行sum会导致数据问题    
解决方法: format函数改为cast或者convert
