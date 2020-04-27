### List

#### list遍历
    实现了RandomAccess接口的list遍历优先使用fori
    未实现RandomAccess接口的list遍历优先使用iterator
#### ArrayList
    arraylist遍历尽量使用fori(推荐)
#### LinkedList
    linkedlist遍历优先使用iterator
    其次foreach（foreach遍历底层也是通过iterator实现的）
    最后fori（千万不要使用普通for循环，效率极慢）
    
#### list扩容机制
    // 创建一个空Object[]数组
    ArrayList arrayList = new ArrayList(); 
    // 第一次新增 数组大小赋值为默认值10
    arrayList.add(e); 
    // 新增多次数组不扩容直到size=10
    arrayList.add(e); 
    // 新增了10个元素后会进行扩容,扩容到1.5倍
    // newSize = oldSize + oldSize>>1
    // 后续执行和前面类似
### Set
   保存不重复的集合数据
   

