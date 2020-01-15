package com.hb0730.spring.cloud.gateway.sample.route;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Service
public class CustomerRouteImpl {
    private ApplicationEventPublisher publisher;

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
        publisher.publishEvent(new RefreshRoutesEvent(this));
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
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    public String delete(String id) {
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
