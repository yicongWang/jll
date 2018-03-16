package com.zifisense.jll.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * rest service 自定义配置类
 *
 * Created by Floki on 2017/6/28.
 */
@ConfigurationProperties(prefix = "rest.service")
public class RestServiceConfig {

    /** rest service 的资源访问根路径 */
    private String rootUrl;

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }
}
