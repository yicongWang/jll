package com.zifisense.jll.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.zifisense.jll.service.cache.CodeTableCache;
import com.zifisense.jll.service.cache.ProjectCache;
import com.zifisense.jll.service.cache.RoleCache;
import com.zifisense.jll.service.mqtt.MqttClientApplication;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(StartupListener.class);
	@Autowired 
	private RoleCache roleCache;
	@Autowired
	private ProjectCache projectCache;
	
	@Autowired
	private CodeTableCache codeTableCache;
	@Autowired
	private MqttClientApplication mqttClientApplication;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            initData();
        }
    }

    private void initData() {
    	logger.debug("---initData--initAllRoleCache---start--");
    	roleCache.initAllRoleCache();
    	logger.debug("---initData--initAllRoleCache---end--");
    	
    	logger.debug("---initData--initAllProjectCache---start--");
    	projectCache.initAllProjectCache();
    	logger.debug("---initData--initAllProjectCache---end--");
    	
    	logger.debug("---initData--initCodeCache---start--");
    	codeTableCache.initCodeCache();
    	logger.debug("---initData--initCodeCache---end--");
    	
    	logger.debug("---initData--connectMqttService---start--");
    	mqttClientApplication.connectMqttService();
    	logger.debug("---initData--connectMqttService---end--");
    	
    }
}