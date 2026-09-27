# Spring Cloud 2026.0.x

## 接口

| 服务                                               | 接口                                    | 说明                         |
|--------------------------------------------------|---------------------------------------|----------------------------|
| [gateway-server-webflux](gateway-server-webflux) | http://127.0.0.1:5061/user/properties | 测试：服务间调用、读取 nacos 中的配置     |
| [gateway-server-webmvc](gateway-server-webmvc)   | http://127.0.0.1:5062/user/properties | 测试：服务间调用、读取 nacos 中的配置     |
| [user](user)                                     | http://127.0.0.1:5063/properties      | 测试：读取 nacos 中的配置           |
| [user-actuator](user-actuator)                   | http://127.0.0.1:5064/properties      | 测试：读取 nacos 中的配置（actuator） |
| [user-rocketmq](user-rocketmq)                   |                                       | 测试：RocketMQ                |
| [user-seata](user-seata)                         |                                       | 测试：Seata（服务内部的事务传播）        |
| [user-schedulerx](user-schedulerx)               |                                       | 测试：SchedulerX              |
| [user-sentinel](user-sentinel)                   |                                       | 测试：Sentinel                |
