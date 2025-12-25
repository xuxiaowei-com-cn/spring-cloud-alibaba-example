# Spring Cloud Alibaba 示例

## 项目模块说明

| 模块                                                                                           | Spring Boot | Spring Cloud | Spring Cloud Alibaba | Nacos | packaging | 说明                                  |
|----------------------------------------------------------------------------------------------|-------------|--------------|----------------------|-------|-----------|-------------------------------------|
| [nacos-bootstrap-3.x](nacos-bootstrap-3.x)                                                   | 3.4.9       | -            | -                    | 3.1.1 | jar       | 用于快速启动 Nacos                        |
| [spring-cloud-2021.0.x](spring-cloud-2021.0.x)                                               | 2.6.15      | 2021.0.9     | 2021.0.6.2           | 2.2.0 | pom       | Spring Cloud 2021.0.x 依赖管理          |
| [spring-cloud-2021.0.x/gateway](spring-cloud-2021.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2021.0.x/user](spring-cloud-2021.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2022.0.x](spring-cloud-2022.0.x)                                               | 3.0.13      | 2022.0.5     | 2022.0.0.2           | 2.2.3 | pom       | Spring Cloud 2022.0.x 依赖管理          |
| [spring-cloud-2022.0.x/gateway](spring-cloud-2022.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2022.0.x/user](spring-cloud-2022.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2023.0.x](spring-cloud-2023.0.x)                                               | 3.2.12      | 2023.0.6     | 2023.0.3.4           | 2.4.3 | pom       | Spring Cloud 2023.0.x 依赖管理          |
| [spring-cloud-2023.0.x/gateway](spring-cloud-2023.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2023.0.x/gateway-mvc](spring-cloud-2023.0.x/gateway-mvc)                       | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2023.0.x/user](spring-cloud-2023.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2025.0.x](spring-cloud-2025.0.x)                                               | 3.5.9       | 2025.0.1     | 2025.0.0.0           | 3.0.3 | pom       | Spring Cloud 2025.0.x 依赖管理          |
| [spring-cloud-2025.0.x/gateway](spring-cloud-2025.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2025.0.x/gateway-mvc](spring-cloud-2025.0.x/gateway-mvc)                       | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2025.0.x/user](spring-cloud-2025.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2025.0.x/user-actuator](spring-cloud-2025.0.x/user-actuator)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置（测试 actuator） |
| [spring-cloud-2025.1.x](spring-cloud-2025.1.x)                                               | 4.0.0       | 2025.1.0     | 2025.1.0.0-SNAPSHOT  | 3.1.0 | pom       | Spring Cloud 2025.1.x 依赖管理          |
| [spring-cloud-2025.1.x/gateway-server-webflux](spring-cloud-2025.1.x/gateway-server-webflux) | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux                     |
| [spring-cloud-2025.1.x/gateway-server-webmvc](spring-cloud-2025.1.x/gateway-server-webmvc)   | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                         |
| [spring-cloud-2025.1.x/user](spring-cloud-2025.1.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置              |
| [spring-cloud-2025.1.x/user-actuator](spring-cloud-2025.1.x/user-actuator)                   | 同上          | 同上           | 同上                   | 同上    | jar       | 测试：服务间调用、读取 nacos 中的配置（测试 actuator） |

### 数据库

- [sql](nacos-bootstrap-3.x/src/main/resources/sql)
    - [mysql-schema.sql](nacos-bootstrap-3.x/src/main/resources/sql/mysql-schema.sql)
        1. Nacos 数据库表结构
    - [mysql-data.sql](nacos-bootstrap-3.x/src/main/resources/sql/mysql-data.sql)
        1. Nacos 数据库数据
        2. Nacos 用户名：nacos，密码：nacos

### Nacos

- [NacosBootstrap_3_x.java](nacos-bootstrap-3.x/src/main/java/com/alibaba/nacos/bootstrap/NacosBootstrap_3_x.java)
  启动 nacos 时，如果存在环境变量 `NACOS_CREATE_TOKEN=true`，会创建一个 nacos `token` 并更新到 nacos 的配置（`token.yml`）中：
    1. nacos `token` 用于在测试类中修改 nacos 配置中心中的配置，用于配置动态刷新的测试
    2. 如果 nacos 启动时间过上，nacos `token` 可能会失效，需要重启 nacos
    3. 测试示例：
       [UserController_2025_0_x_Tests.java](spring-cloud-2025.0.x/user/src/test/java/cn/com/xuxiaowei/user/controller/UserController_2025_0_x_Tests.java)

## GitHub Actions CI/CD

1. [![Spring Cloud Alibaba 2021.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2021.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2021.0.x.yml)
2. [![Spring Cloud Alibaba 2022.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2022.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2022.0.x.yml)
3. [![Spring Cloud Alibaba 2023.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2023.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2023.0.x.yml)
4. [![Spring Cloud Alibaba 2025.0.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.0.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.0.x.yml)
5. [![Spring Cloud Alibaba 2025.1.x](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.1.x.yml/badge.svg)](https://github.com/xuxiaowei-com-cn/spring-cloud-alibaba-example/actions/workflows/spring-cloud-2025.1.x.yml)
