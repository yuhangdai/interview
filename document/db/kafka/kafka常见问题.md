### kafka相关概念
- 生产者
- 消费者（消费者组）
- topic
- partition

### 消费者重平衡（rebalance）

### kafka发送消息过程
[Java kafka producer 发送数据](https://blog.csdn.net/qq_27093465/article/details/107596269)

#### kafka消息顺序保证
Kafka保证同一个partition中的消息是有序的。即如果生产者按照一定的顺序发送消息，broker就会按照这个顺序把他们写入partition，消费者也会按照相同的顺序读取他们

### kafka生成消费模式
生产使用push模式，消费使用poll轮询实现
```
try {
  while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
    for (ConsumerRecord<String, String> record : records) {
      int updateCount = 1;
      if (map.containsKey(record.value())) {
        updateCount = (int) map.get(record.value() + 1);
      }
      map.put(record.value(), updateCount);
    }
  }
}finally {
  consumer.close();
}
```

[kafka中文文档](https://kafka.apachecn.org/documentation.html#introduction)
[参考文档](https://www.cnblogs.com/cxuanBlog/p/11939490.html)