# spring-cloud-gateway-sample
此项目演示spring-cloud-gateway 路由动态管理
## see 
<https://www.jianshu.com/p/68aa549b5897>
## 引入actuator用于展示端点

## see https://cloud.spring.io/spring-cloud-gateway/reference/html/#configuration
`RouteDefinitionRepository extends RouteDefinitionLocator, RouteDefinitionWriter`<br>
默认实现: `public class InMemoryRouteDefinitionRepository implements RouteDefinitionRepository `因此我们可以自定义实现

# 流程
1. 当我们查看`RouteDefinitionLocator`时，会发现多种实现,当我们选中其中一种内存模式`InMemoryRouteDefinitionRepository`(与数据库/redis类似)
2. 我们发现`public class InMemoryRouteDefinitionRepository implements RouteDefinitionRepository` ,由此，我们是否也可以实现`RouteDefinitionRepository`呢
3. 当我去实现`RouteDefinitionRepository`,另一个问题就是，如果通知`spring应用更新`,此时我们借助`actuator`[see](https://cloud.spring.io/spring-cloud-gateway/reference/html/#recap-the-list-of-all-endpoints),发现,可以更新路由
4. `GatewayControllerEndpoint`的实现,我们发现其父类`AbstractGatewayControllerEndpoint`中出现了`AbstractGatewayControllerEndpoint#refresh`方法`	this.publisher.publishEvent(new RefreshRoutesEvent(this));`
5. 我们知道在spring中event事件，就是发布订阅用于通知,所以当我们进行了数据库路由更新，缓存更新后通知gateway更新，去执行我们自定义实现的

## 具体我们可以参考`GatewayControllerEndpoint`端点