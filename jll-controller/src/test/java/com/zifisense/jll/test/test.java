package com.zifisense.jll.test;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import com.zifisense.jll.service.mqtt.config.Constant;

public class test {

	public static void main(String[] args) {
		
		String mess = "{\"deviceId\": \"1254a125\",\"companyCode\": \"SFFDSF\",\"deviceType\": \"智能门磁感应器\",\"dataFlag\": 1,\"dataDetail\": \"门开启，超过15分钟\",\"data\":\"0A010f\"}";
		
		try {
			publishMsg("1231", "e9dbd0ab151d5957cd9869a142ba2fd1/jll/property/ms/1254a125/updata", mess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	  public static void publishMsg(String clientId,String topic,String message) throws Exception{
			MQTT mqtt = null;
			BlockingConnection connection = null;
			try {
				mqtt = new MQTT();
				// 设置服务端的ip
				mqtt.setHost(Constant.MQTT_HOST);
				// 连接前清空会话信息
				mqtt.setCleanSession(Constant.MQTT_SERVICE_CLEAN_START);
				mqtt.setClientId(clientId);
				mqtt.setUserName(Constant.MQTT_USER_NAME);
				mqtt.setPassword(Constant.MQTT_PASSWORD);
				// 设置重新连接的次数
				mqtt.setReconnectAttemptsMax(Constant.MQTT_SERVICE_RECONNECTION_ATTEMPT_MAX);
				// 设置重连的间隔时间
				mqtt.setReconnectDelay(Constant.MQTT_SERVICE_RECONNECTION_DELAY);
				// 设置心跳时间
				mqtt.setKeepAlive(Constant.MQTT_SERVICE_KEEP_ALIVE);
				// 设置缓冲的大小
				mqtt.setSendBufferSize(Constant.MQTT_SERVICE_SEND_BUFFER_SIZE);
				// 创建连接
			    connection = mqtt.blockingConnection();
				// 开始连接
				connection.connect();
				connection.publish(topic, message.getBytes(), QoS.EXACTLY_ONCE, false);
				System.out.println("MQTTServer Message  Topic=" + topic + "  Content :" + message +" success!");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(null != connection && connection.isConnected()){
					connection.disconnect();
				}
			}
	  }
}
