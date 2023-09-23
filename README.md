# changgou
### 畅购商城项目简介

- 畅购商城采用当前流行的微服务、分布式架构，前后端分离。
- 畅购商城属于B2C电商模式，运营商将自己的产品发布到网站上，会员注册后，在网站上将商品添加到购物车，并且下单，完成线上支付，用户还可以参与秒杀抢购。

### 商城项目技术特点

- 技术新
- 技术范围广
- 分布式
- 高并发、集群、负载均衡、高可用
- 海量数据
- 业务复杂
- 系统安全

### 畅购商城技术点

1. 采用springBoot搭建项目
2. 使用springCloud的各种组件
3. 负载均衡器Nignx
4. 分布式文件系统FastDFS
5. 全文检索技术ElasticSearch
6. 分布式缓存Redis
7. 模板引擎Thymeleaf
8. 消息中间件RabbitMQ
9. 权限控制框架SpringSecurity+Oauth2.0
10. 数据库同步工具Canal
11. 高可用解决方案openResty
12. 阿里云短信
13. 跨域问题解决方案CORS
14. 分布式ID生成器IdWorker
15. 微信支付
16. 二维码生成工具Qrious
17. 虚拟化容器Docker以及Redis、ElasticSearch服务集群

### 模块简介

#### 功能模块

- changgou-gateway: 网关模块，根据网站的规模和需要，可以将综合逻辑相关的服务用网关路由组合到一起。在这里还可以做鉴权和限流相关操作。
- changgou-service: 微服务模块，该模块用于存放所有独立的微服务工程。
  - changgou-service-file: 文件微服务
  - changgou-service-goods: 商品微服务
  - changgou-service-order: 订单微服务
  - changgou-service-pay: 支付微服务
  - changgou-service-search: 搜索微服务
  - changgou-service-seckill: 秒杀微服务
  - changgou-service-syscenter: 系统管理中心微服务
  - changgou-service-user: 用户微服务
- changgou-service_api: 对应工程的JavaBean、Feign、以及Hystrix配置，该工程主要对外提供依赖。
- changgou-transaction-fescar: 分布式事务模块，将分布式事务抽取到该工程中，任何工程如需要使用分布式事务，只需依赖该工程即可。
- changgou-web: web服务工程，对应功能模块如需要调用多个微服务，可以将他们写入到该模块中，例如网站后台、网站前台等

#### 其它模块

- changgou-common: 网关模块，根据网站的规模和需要，可以将综合逻辑相关的服务用网关路由组合到一起。在这里还可以做鉴权和限流相关操作。
- changgou-common-db: 微服务模块，该模块用于存放所有独立的微服务工程。
- changgou-auth: oauth2.0权限认证管理中心
- cahnggou-eureka: 注册中心