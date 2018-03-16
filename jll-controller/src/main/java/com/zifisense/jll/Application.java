package com.zifisense.jll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.zifisense.jll.client.config.RestServiceConfig;
import com.zifisense.jll.service.common.SystemConfig;
import com.zifisense.jll.util.UserSettings;

/**
 * Created by DW on 2017/6/26.
 */
@SpringBootApplication
@EnableConfigurationProperties({ UserSettings.class, RestServiceConfig.class, SystemConfig.class})
@ComponentScan({"com.zifisense.jll"})
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
