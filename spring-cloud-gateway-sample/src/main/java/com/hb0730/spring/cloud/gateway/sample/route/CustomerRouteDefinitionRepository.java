package com.hb0730.spring.cloud.gateway.sample.route;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

/**
 * <p>
 * 自定义网关
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
@AllArgsConstructor
public class CustomerRouteDefinitionRepository implements RouteDefinitionRepository {
    private static Logger logger = LoggerFactory.getLogger(CustomerRouteDefinitionRepository.class);

    private final Map<String, RouteDefinition> routes = synchronizedMap(
            new LinkedHashMap<String, RouteDefinition>());

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        logger.debug("获取全部的路由");
        return Flux.fromIterable(routes.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        logger.debug("保存路由");
        return route.flatMap(r -> {
            routes.put(r.getId(), r);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        logger.debug("删除路由");
        return routeId.flatMap(id -> {
            if (routes.containsKey(id)) {
                routes.remove(id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(
                    new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }
}
