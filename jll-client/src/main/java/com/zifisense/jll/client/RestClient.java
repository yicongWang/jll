package com.zifisense.jll.client;

import com.zifisense.jll.client.config.RestServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by Floki on 2017/6/28.
 */
@Component
public class RestClient {

    /** rest service 配置 */
    @Autowired
    protected RestServiceConfig config;

    protected RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        OkHttpClientHttpRequestFactory okHttpClientHttpRequestFactory = new OkHttpClientHttpRequestFactory();
        okHttpClientHttpRequestFactory.setConnectTimeout(500);
        okHttpClientHttpRequestFactory.setReadTimeout(1000);
        okHttpClientHttpRequestFactory.setWriteTimeout(1000);
        restTemplate.setRequestFactory(okHttpClientHttpRequestFactory);
    }
}
