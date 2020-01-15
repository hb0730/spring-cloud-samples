package com.hb0730.spring.cloud.gateway.sample.route;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController("gateway")
public class Controller {

    private CustomerRouteImpl customerRoute;

    public Controller(CustomerRouteImpl customerRoute) {
        this.customerRoute = customerRoute;
    }

    /**
     * id: baiduRoute
     * path: www.baidu.com
     * 访问: http://localhost:10000/baidu
     * @param id
     * @param path
     * @return
     */
    @RequestMapping("/add/{id}/{path}")
    public String add(@PathVariable String id,@PathVariable String path) {
        return customerRoute.add(id, path);
    }
}
