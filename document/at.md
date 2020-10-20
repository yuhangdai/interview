### 系统
#### IDC/ISP信息安全管理系统
IDC - Internet Data Center   互联网数据中心
ISP - Internet Service Provider  互联网服务提供商
ISMI  - Information Security Management Interface  信息安全管理接口
ISMS  - Information Security Management System     信息安全管理系统
SMMS  - Security Monitor Management System         安全监管系统

ISMI是ISMS和SMMS之间的接口，主要功能包括基础数据管理、访问日志管理、信息安全管理、代码表发布等。

ISMI包括命令管道和数据通道。SMMS通过命令通道下发指令给ISMS，ISMS通过数据通道上传数据给SMMS。

##### 基础数据管理

- 页面主动上报
    isms smmsapi（smms-task）
    （new）点击上报-->校验数据完整性-->发送kafka消息(解耦)写等待表 
    
    job
    读取kafka指定类型消息(submitId)-->获取等待表中经营者、机房、用户数据
    -->组装上报数据节点xml-->拆分文件-->在指定目录下生成待上报xml文件
    
    filemanage
    将指定目录xml文件上报到smms指定目录下并备份
    
    smmsapi（smms-task）
    管局处理结果返回指定文件到999目录
    
- SMMS下发指令

    smmsapi（smms-webservice）
    通过webservice获取smms指令信息，解析(内容解密),发送kafka信息待job处理
    
    job
    读取kafka指定类型消息(submitId)-->获取等待表中经营者、机房、用户数据
    -->组装上报数据节点xml-->拆分文件-->在指定目录下生成待上报xml文件
    
    filemanage
    将指定目录xml文件上报到smms指定目录下并备份
    
    smmsapi（smms-task）
    管局处理结果返回指定文件到999目录
    
    
- 策略下发


##### 引申问题
项目模块多部署如何防止重复消费kafka消息？ 
（通过zookeeper leader选举确保代码同个项目多个进程同一时间只有一个执行）

后台定时任务不重启实现表达式修改？
spring quartz

数据多，mysql慢查询问题？

kafka消费问题？

单点登录问题？

接口调用数据加密安全问题？
 