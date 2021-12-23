# SO-COIN
基于StackOverflow的知识图谱应用-服务端部分

项目演示视频：[基于StackOverflow的知识图谱应用_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV19r4y1D7wE/)

![](https://zzn-normal.oss-cn-beijing.aliyuncs.com/Pics/socoin%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84.png)

## 微服务架构介绍

- 使用 spring cloud 相关组件构建微服务应用
- 涉及的组件
  - Eureka：服务注册与发现（服务端与客户端）
  - Gateway：服务网关（使用spring boot actuator 监控端口），跨域与用户识别

## 模块功能介绍

- eureka-server

  - 服务注册与发现中心，含身份验证的注册中心

- api-gateway

  - 服务网关，添加了两个路由过滤器，分别用于身份验证和添加跨域

- user-service

  - 连接mysql数据库，执行业务逻辑

- neo4j-service

  - 连接neo4j数据库，灵活查询接口

- python-service

  - bert模型，部署在另一台服务器

## 数据库选型

- neo4j 图数据库
- mysql 用户数据库

## docker部署 & 持续集成

- 每个服务制作镜像
- 使用docker-compose编排启动多个镜像

- 持续集成获取代码之后执行run.sh脚本
