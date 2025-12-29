# Spring Cloud 2021.0.x

## 接口

| 服务                             | 接口                                    | 说明                     |
|--------------------------------|---------------------------------------|------------------------|
| [gateway](gateway)             | http://127.0.0.1:5051/user/properties | 测试：服务间调用、读取 nacos 中的配置 |
| [user](user)                   | http://127.0.0.1:5053/properties      | 测试：读取 nacos 中的配置       |
| [user-rocketmq](user-rocketmq) |                                       | 测试：RocketMQ            |
| [user-seata](user-seata)       |                                       | 测试：Seata（服务内部的事务传播）    |
| [user-sentinel](user-sentinel) |                                       | 测试：Sentinel            |
