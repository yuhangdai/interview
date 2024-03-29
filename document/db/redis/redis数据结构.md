### redis 5种数据结构及其底层实现
- string
- hash 
- list
- set
- zset

### 底层实现

#### skiplist 
[skiplist跳表](http://zhangtielei.com/posts/blog-redis-skiplist.html)

空间换时间（节点通过增加多层指针减少查询时间）

用于构建支持查找的数据结构：一个是基于各种平衡树，一个是基于哈希表
但是skiplist与二者皆有所不同。

skiplist数据结构? 
- 为什么随机level?   
方便插入和删除，避免插入和删除时引起其它节点大量调整
- 随机数取值方法
    * 每个节点多包含第一层节点（每个节点都在第一层链表中）
    * 如果一个节点有第i（i>=1）层指针，那么它有第（i+1）层指针的概率为p
    * 节点最大层数不能超过maxLevel
    
redis skiplist取值p=1/4 maxLevel = 32;

- skiplist和树以及hash区别

    - skiplist和平衡树（二叉树、红黑树...）是有序的，hash是无序的；因此hash不适合范围查找
    - 单key值查找，hash在冲突较低情况下具有o(1)时间复杂度，树和skiplist具有o(logn)复杂度；
    - 范围查找，skiplist比平衡树更快； 
    - skiplist增删改操作比平衡树简单，不需要调整结构；
    - 在内存占用上，skiplist比平衡树更灵活；平衡树每个节点包含2个指针（分别指向左右子树），
    而skiplist每个节点包含的指针数目平均为1/(1-p)，当p=1/4时，仅为1.33个指针
    - 从算法实现难度上来比较，skiplist比平衡树要简单得多

当数据较少时，sorted set是由一个ziplist来实现的。
当数据多的时候，sorted set是由一个叫zset的数据结构来实现的，这个zset包含一个dict + 一个skiplist。
dict用来查询数据到分数(score)的对应关系，而skiplist用来根据分数查询数据（可能是范围查找）

### redis应用

- 计数器
- 分布式锁


### redis slot与hash以及hash一致性算法
- hash算法   
hash算法一般使用某一算法（比如CRC16）获取hash值，然后对hash值根据node节点取模,
确定数据分布到那一个node。比如分库分表时，需要将数据存储到不同的数据库，此时就可以
使用hash算法确定数据落入哪个节点。
缺点：机器扩容或者删除时会导致大量数据失效。

- 一致性hash   
以hash取值范围组成hash环,将node节点散列到环上。hash key根据hash值确定在环上位置，
并通过key值所处的位置逆时针移动遇到的第一个node节点作为key存储的服务器。

当node节点数据太少时，node节点在环上分布可能不太均匀，会导致数据在服务器上分布不均，
此时可以通过增加虚拟节点（如对节点编号node#1 node#2 node#3然后对其值进行hash，确定在hash环上位置），
让node节点在环上尽可能分布均匀。

- slot槽点    
redis既没有hash算法，也没有采用一致性hash算法，而是使用slot槽点(总共16384)，将slot槽点分布在各个master上。
每个master均匀分布若干槽点，然后将key对槽点总数取模，确定其存储的服务器。

### 缓存穿透 缓存击穿 缓存雪崩

- 缓存穿透
如果请求查询这条数据，缓存中查不到，会将请求打到下层的数据库上，这就是缓存穿透。
**需要注意的是，低频的缓存穿透是不可避免的，但是需要避免高频的缓存穿透**

    解决方法：   
    * 缓存空对象 将查询不到的数据缓存空数据
    * bloom filter保存数据库中存在的id集合

- 缓存击穿
缓存击穿指缓存热点数据过期失效，此时大量对热点数据的访问导致大量并发请求同时访问数据库，
数据库崩溃现象

    解决办法：
    * 热点数据不设置过期
    * 使用分布式锁，确保任何时间只能有一个请求能访问数据库，并将结果写入缓存，其后请求都通过缓存进行查询

- 缓存雪崩
多个热点key同时失效


   解决方法
   * 数据预热，缓存时间随机
   * 使用redis集群
    
    

[参考1](https://juejin.im/post/6847902224144662542)

   