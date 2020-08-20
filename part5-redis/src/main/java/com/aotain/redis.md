## redis
### redis设置过期时间

### redis 5中数据结构

#### string
Redis 为了对内存做极致的优化，不同长度的字符串使用不同的结构体来表示.
#### list
Redis 的列表相当于 Java 语言中的 LinkedList，注意它是链表而不是数组
#### hash
Redis 中的字典相当于 Java 中的 HashMap，内部实现也差不多类似，都是通过 "数组 + 链表" 的链地址法来解决部分 哈希冲突，
同时这样的结构也吸收了两种不同数据结构的优点
#### set
Redis 的集合相当于 Java 语言中的 HashSet，它内部的键值对是无序、唯一的。
它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值 NULL
#### sortedSet(zset)
Redis 最具特色的一个数据结构了，它类似于 Java 中 SortedSet 和 HashMap 的结合体，
一方面它是一个 set，保证了内部 value 的唯一性，另一方面它可以为每个 value 赋予一个 score 值，用来代表排序的权重.
内部实现由「跳跃表」 数据结构实现


#### HyperLog Log
#### Bloom Filter
#### Geo Hash

#### 持久化
- RDB
- AOF