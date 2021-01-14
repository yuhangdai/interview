### IDEA
1.IDEA debug启动慢 失败（有断点打在方法上，去掉此类型断点就行）
### JS
[js测试](https://www.w3school.com.cn/tiy/t.asp?f=html_a_target_framename)
1. 超链接target为_blank，导致href中方法无法执行 [解决办法](https://www.iteye.com/blog/czj4451-1989918)


### JAVA

#### **分布式锁实现**

setnx+expire(非原子操作，存在问题；使用luna脚本实现原子性)
单节点：set key unique_value nx px(value必须唯一) 
多节点：redLock（多节点使用set key value nx px可能存在问题，A取得master上锁，同步到slave时，
master挂掉，此时其它线程可以对同一key获取多个锁）

[redis多节点分布式锁实现](https://mp.weixin.qq.com/s/8uhYult2h_YUHT7q7YCKYQ)

#### zookeeper实现分布式锁
[zookeeper分布式锁](https://blog.csdn.net/weixin_38004638/article/details/97148292)

- 永久节点
- 临时节点
- 顺序节点


#### redis实现分布式锁
[Redis的分布式锁实现](https://juejin.cn/post/6844903830442737671)

#### **spring MVC处理流程**
#### **spring bean生命周期**
#### **异步线程如何获取执行结果**

#### java线程相关知识
[线程相关](https://zhuanlan.zhihu.com/p/26441926)

#### 线程和进程通信方式

##### 进程间通信方式(数据交换)
- 管道(pipe)：管道是一种半双工的通信方式，数据只能单向流动。只允许具有亲缘关系的进程间使用。
进程间的亲缘关系通常指父子进程关系。
- 有名管道(namedpipe):有名管道也是半双工的通信方式，但是它允许无亲缘关系进程间的通信
- 信号量：
- 消息队列：
- 信号：
- 共享内存：共享内存就是映射一段能被其他进程所访问的内存，这段共享内存由一个进程创建，但多个进程都可以访问。
- 套接字： 套接字也是一种进程间通信机制，与其他通信机制不同的是，它可用于不同及其间的进程通信

##### 线程间通信方式(同步)
- 锁机制(互斥锁、读写锁、条件变量)
- 信号量机制
- 信号机制

#### 如何获取异步线程执行结果

#### 线程池创建类
`ThreadPoolExecutor`
`核心参数`

### ftp
then you will need to use mdelete to delete multiple files, as delete will only delete a single file
ftp操作多个文件 使用prompt关闭交互模式
ftp delete命令不能删除文件夹



### soap ui
1.soap ui进行rest api调用时，传递中文参数报400错误。后台JSON解析异常   
[解决办法](https://blog.csdn.net/gongjin28_csdn/article/details/90750283)  
找到soap ui安装目录（可执行文件），在此目录下找到SoapUI-xxx.vmoptions文件，
在文件结尾添加如下两行代码即可。

    -Dsun.jnu.encoding=UTF-8
    -Dfile.encoding=UTF-8


### Mybatis
mybatis 分页参数混乱问题
[解决办法](https://blog.csdn.net/w8y56f/article/details/100710380)

oracle数据库连接池报错
[No more data to read from socket error](https://stackoverflow.com/questions/7839907/no-more-data-to-read-from-socket-error)
```
maxActive="100"
minIdle="10"
maxWait="10000"
initialSize="10"
```
      
      
### spring mvc 返回 return string和return modelandview区别

### div实现y轴滚动条
[DIV实现纵向滚动条overflow-y](cnblogs.com/wellsoho/p/5102014.html)

### idea打包war包并在tomcat运行时文件路径
[idea部署war包文件路径](https://blog.csdn.net/Victor_Cindy1/article/details/72680553)
${user.home}/.IntelliJIdea/system/tomcat

### IDEA显示各种toolbar
[IDEA toolbar显示与隐藏](https://blog.csdn.net/xb12369/article/details/106302577)

### sock5服务器搭建
[sock5](https://iblogs.top/2019/05/21/socks5%E4%BB%A3%E7%90%86%E6%9C%8D%E5%8A%A1%E5%99%A8%E6%90%AD%E5%BB%BA/)
[sock5一键搭建](https://github.com/wyx176/Socks5)

### hashMap多线程可能导致的问题
[hashMap多线程问题](https://www.cnblogs.com/aspirant/p/11504389.html)
### hash冲突解决方法
[hash冲突](https://zhuanlan.zhihu.com/p/29520044)
### 锁升级过程
[锁升级](https://blog.csdn.net/tongdanping/article/details/79647337)
### 红黑树定义
- 节点是红色或黑色
- 根是黑色
- 所有叶子都是黑色
- 从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
- 每个红色节点必须有两个黑色的子节点。（从每个叶子到根的所有路径上不能有两个连续的红色节点。)
### 红黑树重新涂色条件


