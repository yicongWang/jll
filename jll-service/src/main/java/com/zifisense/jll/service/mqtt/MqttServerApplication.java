package com.zifisense.jll.service.mqtt;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zifisense.jll.service.mqtt.config.Constant;

@Service
public class MqttServerApplication {
    private static Logger logger = LoggerFactory.getLogger(MqttServerApplication.class);
/*	//private final static String CONNECTION_STRING = "tcp://192.168.0.112:1883";
	private final static String CONNECTION_STRING ="tcp://0.0.0.0:61613";
	private final static String CLIENT_USERNAME = "admin";
	private final static String CLIENT_PSW= "password";
	private final static boolean CLEAN_START = true;
	private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s	
	private final static String CLIENT_USERNAME = "mqttuser";
	private final static String CLIENT_PSW= "zifisense";
	public static Topic[] topics = { new Topic("china/beijing", QoS.AT_MOST_ONCE),
			new Topic("china/tianjin", QoS.AT_LEAST_ONCE), new Topic("china/henan", QoS.AT_MOST_ONCE) };
	public final static long RECONNECTION_ATTEMPT_MAX = 6;
	public final static long RECONNECTION_DELAY = 2000;
	public final static int SEND_BUFFER_SIZE = 2 * 1024 * 1024;// 发送最大缓冲为2M
*/
	/**
	 * @param clientId 客户	
	 * @param topic 订阅的主题
	 * @param message 主题的内容
	 * @throws Exception
	 */
  public  void publishMsg(String clientId,String topic,String message) throws Exception{
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
			logger.error("--publishMsg---error",e);
			e.printStackTrace();
		}finally {
			if(null != connection && connection.isConnected()){
				connection.disconnect();
			}
		}
  }
  
	
  
}
