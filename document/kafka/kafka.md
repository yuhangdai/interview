### kafka相关疑难问题（追加写、offset读）

1. kafka如何保证消息不丢失

- 生产者
异步发送消息并获取消息发送结果(成功标志 acks=all)
producer发送消息设置合理的retries和retry时间间隔 

- 消费者
消费者通过offset获取消息，默认情况下获取消息后会自动提交更新offset值，
此时可能数据还未真正消费但是线程挂掉，这样会导致数据丢失。
解决办法：关掉自动提交，执行完逻辑后手动提交offset。
  
- 服务器
数据提交到kafka broker，主节点收到消息还未来得及与从节点同步，此时主节点挂掉会导致数据丢失。

解决办法：
分区（Partition）引入了多副本（Replica）机制。分区（Partition）中的多个副本之间会有一个叫做 leader 的家伙，其他副本称为 follower
replication.factor >= 3 保证每个 分区(partition) 至少有多个(>3)副本
min.insync.replicas > 1 设置同步中的分区大于1(保证至少有多余一个分区与leader处于同步状态，避免leader宕机数据丢失)
acks = all 设置消息发送成功的标志为多有节点均接收并保存消息

#### at most once和at least once
产生原因:消费消息和提交offset不是一个原子操作
如果自动提交：可能存在读取完消息提交offset，但是在处理消息时线程挂掉导致消息丢失问题；（at most once）
手动提交：可能存在消费完消息提交offset时线程挂掉，导致重复消费（at least once ）

2. kafka消息消费模式(pull push)
sender端采用push模式
producer端采用pull模式(while true轮询)

3. 如何确保消息有序

- 单partition或者消息都写入同一partition(指定partition id或者确保消息key相同)
- retries>0 && max.in.flight.requests.per.connection > 1 
retries retries 参数的值决定了生产者可以重发消息的次数，如果达到这个次数，生产者会放弃重试并返回错误
max.in.flight.requests.per.connection 该参数指定了生产者在收到服务器响应之前可以发送多少个消息

要确保单一partition有效性，可以设置retries>0 && max.in.flight.requests.per.connection = 1;
但是这样会导致消息发送的吞吐量较低；如果需要在保证吞吐量的情况下且单一partition消息有序，
可以设置enable.idempotence=true，开启生产者的幂等生产，保证消息有序

**解决重试机制引起的消息乱序为实现Producer的幂等性**，
Kafka引入了Producer ID（即PID）和Sequence Number。
对于每个PID，该Producer发送消息的每个<Topic, Partition>都对应一个单调递增的Sequence Number。
同样，Broker端也会为每个<PID, Topic, Partition>维护一个序号，并且每Commit一条消息时将其对应序号递增。
对于接收的每条消息，如果其序号比Broker维护的序号）大一，则Broker会接受它，否则将其丢弃：
如果消息序号比Broker维护的序号差值比一大，说明中间有数据尚未写入，即乱序，此时Broker拒绝该消息，
Producer抛出InvalidSequenceNumber如果消息序号小于等于Broker维护的序号，
说明该消息已被保存，即为重复消息，Broker直接丢弃该消息，Producer抛出DuplicateSequenceNumberSender发送失败后会重试，
这样可以保证每个消息都被发送到broker

4. kafka为何高效
[kafka高效原因](https://juejin.cn/post/6863050320646406158)

- 利用partition实现数据并行处理
- 顺序写磁盘(磁盘耗时=寻道时间+旋转延迟+数据传输)，避免大量寻道时间
- page cache缓存
- 零拷贝技术
- 批量处理(send写入磁盘)
- 数据压缩