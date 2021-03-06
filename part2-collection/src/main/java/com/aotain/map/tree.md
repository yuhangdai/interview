### BST(binary search tree) 二分查找树
+ 左子树上所有结点的值均小于或等于它的根结点的值。  
+ 右子树上所有结点的值均大于或等于它的根结点的值。  
+ 左、右子树也分别为二叉排序树。 

树结构可能不平衡，极端情况下可能和链表差不多 

### 平衡二叉树
* 完全二叉树
* 红黑树
    1. 节点要么为红色，要么为黑色；
    2. 根节点为黑色；
    3. 叶子节点也为黑色；
    4. 红色节点的两个子节点都为黑色；
    5. 任意节点到叶子节点包含的黑色节点数相同。
    
性质4、5保证红黑树最大路径 <= (最小路径*2) 

[红黑树](https://www.cnblogs.com/AhuntSun-blog/p/12458115.html)
 
### B(B-)树(Balance-Tree)

**与自平衡二叉查找树不同，B树适用于读写相对大的数据块的存储系统，例如磁盘**
**B树减少定位记录时所经历的中间过程，从而加快存取速度。**
**B树这种数据结构可以用来描述外部存储。这种数据结构常被应用在数据库和文件系统的实现上**

对于大量数据，平衡二叉树由于每个节点最多只能包括两个子节点，导致树高度会较高，
此时会进行多次io寻道时间，导致查询文件系统较慢。
对于同样的数据，B树能保持较低的高度，减少寻道时间。同时也不用进行频繁的平衡操作

B树缺点：
    B树非叶子节点保存数据信息，会占用内存，导致相同内存存储根节点数量少。
    遍历比较复杂，需要对树进行中序遍历。
    
### B+树
   为了克服B树缺点，引入B+树。
   B+树，非叶子节点不保存数据信息，数据信息全部保存在叶子节点中；
   叶子节点通过指针链性连接，方便遍历查找