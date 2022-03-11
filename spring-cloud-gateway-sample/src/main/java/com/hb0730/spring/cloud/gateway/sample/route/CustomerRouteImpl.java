package com.hb0730.spring.cloud.gateway.sample.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class CustomerRouteImpl {

    private CustomerRouteDefinitionRepository repository;

    @Autowired
    public CustomerRouteImpl(CustomerRouteDefinitionRepository repository) {
        this.repository = repository;
    }

    /**
     * <p>
     * 新增
     * </p>
     *
     * @param id   id
     * @param path url
     * @return success
     */
    public String add(String id, String path) {
//        RouteDefinition definition = new RouteDefinition();
//        PredicateDefinition predicate = new PredicateDefinition();
//        Map<String, String> predicateParams = new HashMap<>(8);
//        definition.setId(id);
//        predicate.setName("Path");
//        predicateParams.put("pattern", "/baidu");
//        predicateParams.put("pathPattern", "/baidu");
//        predicate.setArgs(predicateParams);
//        definition.setPredicates(Arrays.asList(predicate));
        URI uri = UriComponentsBuilder.fromHttpUrl("http://" + path).build().toUri();
//        definition.setUri(uri);
//        repository.save(Mono.just(definition)).subscribe();

        RouteDefinition definition = new RouteDefinition();
        definition.setId(id);
        definition.setUri(uri);
        List<PredicateDefinition> definitions = new ArrayList<>();

        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName("Path");

        Map<String, String> args = new HashMap<>();
        args.put("pattern", "/baidu/**");
        predicateDefinition.setArgs(args);
        definitions.add(predicateDefinition);
        definition.setPredicates(definitions);

        List<FilterDefinition> filterDefinitions = new ArrayList<>();
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("StripPrefix");
        Map<String, String> filterArgs = new HashMap<>();
        filterArgs.put("parts", "1");
        filterDefinition.setArgs(filterArgs);
        filterDefinitions.add(filterDefinition);
        definition.setFilters(filterDefinitions);

        repository.save(Mono.just(definition)).subscribe();
        return "success";
    }

    /**
     * <p>
     * 修改
     * </p>
     *
     * @param id
     * @param path
     * @return
     */
    public String edit(String id, String path) {
        return "success";
    }

    public String delete(String id) {
        return "success";
    }
}
