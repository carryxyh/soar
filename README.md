## Soar技术文档

### 功能需求
1. 跨机房切换
2. 支持异步（提供方异步）
3. 集群容错
4. 服务治理
5. Server端限流
6. client端负载均衡
7. 插件化
8. 泛化调用
9. 多语言支持（？）

### 核心接口/抽象
**Server**：服务器，包括开启和关闭<br/>
**Exchanger**：交换器，负责实际上数据传输<br/>
**Filter**：过滤器<br/>
**Cluster**：集群，负责做集群容错<br/>
**Invoker**：服务/调用方抽象<br/>
**SoarContext**：请求上下文<br/>
**Registry**：注册中心<br/>
**Serializer**：序列化<br/>
**LoadBalancer**：客户端负责均衡<br/>
**Breaker**：熔断<br/>
**Cacher**：缓存服务<br/>
**ServiceContainer**：服务容器（考虑支持spring和注解扫描两种方式实现）<br/>

### soar注册中心树形结构
![soar 注册中心树形结构](./soar-zk-data.png)<br/>

### soar多机房和集群容错
![多机房和集群容错](./多机房和集群容错.png)
