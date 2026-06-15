# Spring Cloud Alibaba 示例

## 项目模块说明

| 模块                                               | Spring Boot | Spring Cloud | Spring Cloud Alibaba | Nacos |
|--------------------------------------------------|-------------|--------------|----------------------|-------|
| [nacos-bootstrap-3.x](nacos-bootstrap-3.x)       | 3.5.14      | -            | -                    | 3.2.2 |
| [seata-server-2.x](seata-server-2.x)             | 2.7.18      | -            | -                    | 2.0.4 |
| [sentinel-dashboard-1.x](sentinel-dashboard-1.x) | 2.5.12      | -            | -                    | -     |
| [spring-cloud-2021.0.x](spring-cloud-2021.0.x)   | 2.6.15      | 2021.0.9     | 2021.0.6.2           | 2.2.0 |
| [spring-cloud-2022.0.x](spring-cloud-2022.0.x)   | 3.0.13      | 2022.0.5     | 2022.0.0.2           | 2.2.3 |
| [spring-cloud-2023.0.x](spring-cloud-2023.0.x)   | 3.2.12      | 2023.0.6     | 2023.0.3.4           | 2.4.3 |
| [spring-cloud-2025.0.x](spring-cloud-2025.0.x)   | 3.5.15      | 2025.0.3     | 2025.0.0.1-SNAPSHOT  | 3.1.1 |
| [spring-cloud-2025.1.x](spring-cloud-2025.1.x)   | 4.1.0       | 2025.1.2     | 2025.1.0.1-SNAPSHOT  | 3.1.1 |

<details>
<summary>完整模块列表（含子模块）</summary>

| 模块                                                                                           | Spring Boot | Spring Cloud | Spring Cloud Alibaba | Nacos | packaging | 说明                                  |
|----------------------------------------------------------------------------------------------|-------------|--------------|----------------------|-------|-----------|-------------------------------------|
| [nacos-bootstrap-3.x](nacos-bootstrap-3.x)                                                   | 3.5.14      | -            | -                    | 3.2.2 | jar       | 用于快速启动 Nacos                        |
| [seata-server-2.x](seata-server-2.x)                                                         | 2.7.18      | -            | -                    | 2.0.4 | jar       | 用于快速启动 Seata                        |
| [sentinel-dashboard-1.x](sentinel-dashboard-1.x)                                             | 2.5.12      | -            | -                    | -     | jar       | 用于快速启动 sentinel-dashboard           |
| [spring-cloud-2021.0.x](spring-cloud-2021.0.x)                                               | 2.6.15      | 2021.0.9     | 2021.0.6.2           | 2.2.0 | pom       | Spring Cloud 2021.0.x 依赖管理          |
| [spring-cloud-2021.0.x/gateway](spring-cloud-2021.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2021.0.x/user](spring-cloud-2021.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2021.0.x/user-rocketmq](spring-cloud-2021.0.x/user-rocketmq)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：RocketMQ                         |
| [spring-cloud-2021.0.x/user-seata](spring-cloud-2021.0.x/user-seata)                         | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Seata（服务内部的事务传播）                 |
| [spring-cloud-2021.0.x/user-sentinel](spring-cloud-2021.0.x/user-sentinel)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Sentinel                         |
| [spring-cloud-2022.0.x](spring-cloud-2022.0.x)                                               | 3.0.13      | 2022.0.5     | 2022.0.0.2           | 2.2.3 | pom       | Spring Cloud 2022.0.x 依赖管理          |
| [spring-cloud-2022.0.x/gateway](spring-cloud-2022.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2022.0.x/user](spring-cloud-2022.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2022.0.x/user-rocketmq](spring-cloud-2022.0.x/user-rocketmq)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：RocketMQ                         |
| [spring-cloud-2022.0.x/user-seata](spring-cloud-2022.0.x/user-seata)                         | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Seata（服务内部的事务传播）                 |
| [spring-cloud-2022.0.x/user-sentinel](spring-cloud-2022.0.x/user-sentinel)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Sentinel                         |
| [spring-cloud-2023.0.x](spring-cloud-2023.0.x)                                               | 3.2.12      | 2023.0.6     | 2023.0.3.4           | 2.4.3 | pom       | Spring Cloud 2023.0.x 依赖管理          |
| [spring-cloud-2023.0.x/gateway](spring-cloud-2023.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2023.0.x/gateway-mvc](spring-cloud-2023.0.x/gateway-mvc)                       | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2023.0.x/user](spring-cloud-2023.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2023.0.x/user-rocketmq](spring-cloud-2023.0.x/user-rocketmq)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：RocketMQ                         |
| [spring-cloud-2023.0.x/user-seata](spring-cloud-2023.0.x/user-seata)                         | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Seata（服务内部的事务传播）                 |
| [spring-cloud-2023.0.x/user-schedulerx](spring-cloud-2023.0.x/user-schedulerx)               | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：SchedulerX                       |
| [spring-cloud-2023.0.x/user-sentinel](spring-cloud-2023.0.x/user-sentinel)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Sentinel                         |
| [spring-cloud-2025.0.x](spring-cloud-2025.0.x)                                               | 3.5.15      | 2025.0.3     | 2025.0.0.1-SNAPSHOT  | 3.1.1 | pom       | Spring Cloud 2025.0.x 依赖管理          |
| [spring-cloud-2025.0.x/gateway](spring-cloud-2025.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2025.0.x/gateway-mvc](spring-cloud-2025.0.x/gateway-mvc)                       | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2025.0.x/user](spring-cloud-2025.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2025.0.x/user-actuator](spring-cloud-2025.0.x/user-actuator)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置（测试 actuator） |
| [spring-cloud-2025.0.x/user-rocketmq](spring-cloud-2025.0.x/user-rocketmq)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：RocketMQ                         |
| [spring-cloud-2025.0.x/user-seata](spring-cloud-2025.0.x/user-seata)                         | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Seata（服务内部的事务传播）                 |
| [spring-cloud-2025.0.x/user-schedulerx](spring-cloud-2025.0.x/user-schedulerx)               | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：SchedulerX                       |
| [spring-cloud-2025.0.x/user-sentinel](spring-cloud-2025.0.x/user-sentinel)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Sentinel                         |
| [spring-cloud-2025.1.x](spring-cloud-2025.1.x)                                               | 4.1.0       | 2025.1.2     | 2025.1.0.1-SNAPSHOT  | 3.1.1 | pom       | Spring Cloud 2025.1.x 依赖管理          |
| [spring-cloud-2025.1.x/gateway-server-webflux](spring-cloud-2025.1.x/gateway-server-webflux) | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2025.1.x/gateway-server-webmvc](spring-cloud-2025.1.x/gateway-server-webmvc)   | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2025.1.x/user](spring-cloud-2025.1.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2025.1.x/user-actuator](spring-cloud-2025.1.x/user-actuator)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置（测试 actuator） |
| [spring-cloud-2025.1.x/user-rocketmq](spring-cloud-2025.1.x/user-rocketmq)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：RocketMQ                         |
| [spring-cloud-2025.1.x/user-seata](spring-cloud-2025.1.x/user-seata)                         | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Seata（服务内部的事务传播）                 |
| [spring-cloud-2025.1.x/user-schedulerx](spring-cloud-2025.1.x/user-schedulerx)               | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：SchedulerX                       |
| [spring-cloud-2025.1.x/user-sentinel](spring-cloud-2025.1.x/user-sentinel)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：Sentinel                         |

</details>

## [分支](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/wiki)

### 数据库

- [nacos-bootstrap-3.x sql](nacos-bootstrap-3.x/src/main/resources/sql)
    - [mysql-schema.sql](nacos-bootstrap-3.x/src/main/resources/sql/mysql-schema.sql)
        1. Nacos 数据库表结构
    - [mysql-data.sql](nacos-bootstrap-3.x/src/main/resources/sql/mysql-data.sql)
        1. Nacos 数据库数据
- [Seata sql](sql)
    - [mysql-schema.sql](sql/mysql-schema.sql)
        1. Seata 数据库表结构

### Nacos

- Nacos 后台管理地址：http://127.0.0.1:8080
- Nacos 用户名：`nacos`，密码：`nacos`

### Sentinel Dashboard

- Sentinel Dashboard 后台管理地址：http://127.0.0.1:58080
- Sentinel Dashboard 用户名：`sentinel`，密码：`sentinel`

## Makefile

项目根目录提供了 `Makefile`，包含常用开发命令，运行 `make` 或 `make help` 可查看完整帮助。

| 目标                          | 说明                                                 |
|-----------------------------|----------------------------------------------------|
| `help`                      | 显示帮助信息                                             |
| `format`                    | 使用 spring-javaformat 格式化 Java 代码                   |
| `clean`                     | 清理项目（`mvn clean`）                                  |
| `deep-clean`                | 深度清理（`mvn clean` + 移除运行时文件）                        |
| `package` / `build`         | 构建项目（跳过测试）                                         |
| `package-with-tests`        | 构建项目（含测试）                                          |
| `test`                      | 运行全部测试                                             |
| `verify`                    | 验证项目                                               |
| `dependency-tree`           | 显示依赖树                                              |
| `dependency-updates`        | 检查依赖更新                                             |
| `run-nacos`                 | 启动 nacos-bootstrap-3.x 模块                          |
| `run-seata`                 | 启动 seata-server-2.x 模块                             |
| `run-sentinel`              | 启动 sentinel-dashboard-1.x 模块                       |
| `run-gateway-webflux`       | 启动 spring-cloud-2025.1.x/gateway-server-webflux 模块 |
| `run-gateway-webmvc`        | 启动 spring-cloud-2025.1.x/gateway-server-webmvc 模块  |
| `run-user`                  | 启动 spring-cloud-2025.1.x/user 模块                   |
| `run-user-actuator`         | 启动 spring-cloud-2025.1.x/user-actuator 模块          |
| `run-user-rocketmq`         | 启动 spring-cloud-2025.1.x/user-rocketmq 模块          |
| `run-user-schedulerx`       | 启动 spring-cloud-2025.1.x/user-schedulerx 模块        |
| `run-user-seata`            | 启动 spring-cloud-2025.1.x/user-seata 模块             |
| `run-user-sentinel`         | 启动 spring-cloud-2025.1.x/user-sentinel 模块          |
| `test-*`                    | 单独测试对应模块（如 `test-user`）                            |
| `test-integration-2025.1.x` | 运行 spring-cloud-2025.1.x 集成测试                      |
| `cleanup-integration`       | 清理集成测试进程                                           |

## GitHub Actions CI/CD

1. [![Spring Cloud Alibaba 2021.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2021.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2021.0.x.yml)
2. [![Spring Cloud Alibaba 2022.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2022.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2022.0.x.yml)
3. [![Spring Cloud Alibaba 2023.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2023.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2023.0.x.yml)
4. [![Spring Cloud Alibaba 2025.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.0.x.yml)
5. [![Spring Cloud Alibaba 2025.1.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.1.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.1.x.yml)

## Maven 配置

由于使用了 spring-cloud-alibaba 的 GitHub Package Maven 快照，需要在 Maven `settings.xml` 中配置 GitHub Packages 认证：

```xml
<server>
    <id>github</id>
    <username>GitHub 用户名</username>
    <password>GitHub Token（需要 read:packages 权限）</password>
</server>
```

使用说明见：[spring-cloud-alibaba#4185](https://github.com/alibaba/spring-cloud-alibaba/issues/4185)

国内用户如果无法访问 GitHub Package，见：[spring-cloud-alibaba#4299](https://github.com/alibaba/spring-cloud-alibaba/issues/4299)

## 文档

1. https://seata.apache.org/zh-cn/docs/user/quickstart
2. https://seata.apache.org/zh-cn/docs/user/microservice
