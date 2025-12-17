# Spring Cloud Alibaba 示例

## 项目模块说明

| 模块                                                                                           | Spring Boot | Spring Cloud | Spring Cloud Alibaba | Nacos | packaging | 说明                         |
|----------------------------------------------------------------------------------------------|-------------|--------------|----------------------|-------|-----------|----------------------------|
| [nacos-bootstrap-3.x](nacos-bootstrap-3.x)                                                   | 3.4.9       | -            | -                    | 3.1.1 | jar       | 用于快速启动 Nacos               |
| [spring-cloud-2025.0.x](spring-cloud-2025.0.x)                                               | 3.5.8       | 2025.0.0     | 2025.0.0.0           | 3.0.3 | pom       | Spring Cloud 2025.0.x 依赖管理 |
| [spring-cloud-2025.0.x/gateway](spring-cloud-2025.0.x/gateway)                               | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux            |
| [spring-cloud-2025.0.x/gateway-mvc](spring-cloud-2025.0.x/gateway-mvc)                       | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                |
| [spring-cloud-2025.0.x/user](spring-cloud-2025.0.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 用于服务                       |
| [spring-cloud-2025.1.x](spring-cloud-2025.1.x)                                               | 4.0.0       | 2025.1.0     | 2025.1.0.0-SNAPSHOT  | 3.0.3 | pom       | Spring Cloud 2025.1.x 依赖管理 |
| [spring-cloud-2025.1.x/gateway-server-webflux](spring-cloud-2025.1.x/gateway-server-webflux) | 同上          | 同上           | 同上                   | 同上    | jar       | gateway webflux            |
| [spring-cloud-2025.1.x/gateway-server-webmvc](spring-cloud-2025.1.x/gateway-server-webmvc)   | 同上          | 同上           | 同上                   | 同上    | jar       | gateway mvc                |
| [spring-cloud-2025.1.x/user](spring-cloud-2025.1.x/user)                                     | 同上          | 同上           | 同上                   | 同上    | jar       | 用于服务                       |

### 数据库

- [mysql-schema.sql](nacos-bootstrap-3.x/src/main/resources/sql/mysql-schema.sql)
    1. Nacos 数据库表结构和数据
