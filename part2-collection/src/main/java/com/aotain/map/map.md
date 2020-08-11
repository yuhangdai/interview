### HashMap插入数据过程
- 初次插入初始化table（capacity获取方法）
- 对key进行hash (hashCode%n == n-1 && hashCode 当n为2的n次方时成立)
- 根据hash取模

    * 如果tab[i] == null 则直接新建node然后插入
    * 如果tab[i] != null (说明此位置已被占用)
    1. key值一样，更新value即可
    2. key值不一样（说明此位置被其他key value占用）
        + 冲突处理为treeNode(按treeNode方式添加元素)
        + 冲突处理为linkNode
            - 依次向后移动指针，如果遇到为空的直接新建node即可
            - 如果移动超过TREEIFY_THRESHOLD次都不为空，将节点转化为treeNode

#### hashMap扩容机制
   table.size > loadFactory * oldSize 时会触发扩容
   当前hash表的大小 >  loadFactory * oldSize
    
#### 如何获取大于n的最小2次幕（6-->8  12-->16）
    初始化时会默认初始化为2的幕次方
        1 | 1 = 1  
        1 | 0 = 1 
        0 | 1 = 1
        0 | 0 = 0
        
    static final int tableSizeFor(int cap) {
        int n = cap - 1;  // n第一位肯定为1
        n |= n >>> 1;     // （n第一位为1 n>>>1第二位为1）保证前两位都为1
        n |= n >>> 2;     // 前四位都为1
        n |= n >>> 4;     // 前八位都为1
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    
#### hashMap大小为何是2的n次幕(方便使用位运算计算对应的index)
   得到key对应hash值，
   (hash % n) == (n - 1) & hash 需要n为2的n次幕
   
   10 % 8 = （二进制）10
   7 & 10 = 0111 & 1010 = （二进制）0010 
#### hashMap底层实现
    // 默认装载因子
    DEFAULT_LOAD_FACTOR = 0.75f
    // table默认容量
    DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    // hash冲突键 >= 8 && hash键 >= 64将 
    // hash冲突链表转化为树进行处理
    TREEIFY_THRESHOLD = 8;
    // 
    MIN_TREEIFY_CAPACITY = 64;
    
   默认采用数组+链表实现
   当hash冲突key链表长度>8 && 数组长度 > 64时会将链表转化为红黑树（方便查找）进行冲突处理
    
#### hashMap多线程下会出现什么问题？
   hashMap **rehash**()执行过程
   多线程下rehash过程可能会导致环形链接，从而造成死循环    
    
#### ConcurrentHashMap 

- 1.初次插入初始化table（capacity获取方法）
- 2.对key进行hash (hashCode%n == n-1 && hashCode 当n为2的n次方时成立)
- 3.tabAt(tab,i)查看tab当前位置i是否存在元素
    + 若不存在（casAtTab()插入节点）  
        1. 若插入成功，break跳出死循环
        2. 若插入失败，重复操作1、2、3
    + 若存在（判断(fh = f.hash) == MOVED）
        * 相等 （transfer） 
        * tabAt(tab, i) == f
            1. i位置还未成功插入数据，进行插入操作
                + fh >= 0 
                    
                    如果key相等替换，否则加入到链表后面；
    
                + f instanceof TreeBin
                
                    插入到树节点中
            2. 判断是否需要将链表结构改为树结构


##### JDK1.7实现
    Segment实现分段锁
##### JDK1.8实现
    CAS 
参考文档
    [JAVA HASHMAP的死循环](https://coolshell.cn/articles/9606.html)
    [HashMap? ConcurrentHashMap?](https://crossoverjie.top/2018/07/23/java-senior/ConcurrentHashMap/)
    [hashMap常见面试问题](https://github.com/yuhangdai/JavaGuide/blob/master/docs/java/collection/HashMap.md)
    
#### TreeMap


#### HashSet(基于HashMap实现)
#### TreeSet(基于TreeMap实现)