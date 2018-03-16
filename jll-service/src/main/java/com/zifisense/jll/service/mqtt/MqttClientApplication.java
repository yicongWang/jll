package com.zifisense.jll.service.mqtt;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.service.mqtt.config.Constant;

@Service
public class MqttClientApplication {
	    private MqttClient client;    
	    private MqttConnectOptions options;    
	    @Autowired
	    private PushCallback pushCallback;
	    
	    public void connectMqttService(){
	    	 try {    
		            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存    
		            client = new MqttClient(Constant.MQTT_HOST, Constant.MQTT_CLIENT_CLIENT_ID, new MemoryPersistence());    
		            // MQTT的连接设置    
		            options = new MqttConnectOptions();    
		            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接    
		            options.setCleanSession(true);    
		            // 设置连接的用户名    
		            options.setUserName(Constant.MQTT_USER_NAME);    
		            // 设置连接的密码    
		            options.setPassword(Constant.MQTT_PASSWORD.toCharArray());    
		            // 设置超时时间 单位为秒    
		            options.setConnectionTimeout(10);    
		            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制    
		            options.setKeepAliveInterval(20);    
		            // 设置回调    
		            client.setCallback(pushCallback);  
		          /*  MqttTopic topic = client.getTopic(TOPIC);    
		            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息      
		            options.setWill(topic, "close".getBytes(), 0, true); */  
		            client.connect(options);    
		            //订阅消息    
		            //int[] qos  = {0};    
		          //  String[] topic1 = {"test#"};    
		            //client.subscribe(topic1, Qos);    
		            client.subscribe(Constant.MQTT_CLIENT_TOPIC, 0);
		        } catch (Exception e) {    
		            throw new RuntimeException(e);   
		        }    
	    }
    

}