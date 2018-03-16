package com.zifisense.jll.service.mqtt.config;

import com.zifisense.jll.util.RandCodeUtil;

public class Constant {
	/*public static final String API_KEY = "";
	public static final String VERSION = "";
	public static final String OPTYPE = "";
	public static final String UID = "";
	public static final String SUBCMD = "report";
	public static final String SUBTYPE = "responseDownData";
	public static final String MSG_TYPE = SUBCMD + SUBTYPE;
	public static final String MQTT_CLIENT_TOPIC = API_KEY +"/"+VERSION+"/"+OPTYPE+"/"+UID+"/"+MSG_TYPE; */ 
	public static final String CODE = "e9dbd0ab151d5957cd9869a142ba2fd1";
	
	public static final String MQTT_CLIENT_TOPIC = CODE+"/jll/property/#";
	
	/*public static final String MQTT_HOST = "tcp://192.168.0.230:1883"; 
    public static final String MQTT_USER_NAME = "mqttuser";  
    public static final String MQTT_PASSWORD = "zifisense"; */
	public static final String MQTT_HOST = "tcp://0.0.0.0:61613";  
	public static final String MQTT_USER_NAME = "admin";  
    public static final String MQTT_PASSWORD = "password"; 
    
    public static final String MQTT_CLIENT_CLIENT_ID = "jll:property"+RandCodeUtil.getRandomSmsCode(3); 
    public static final String MQTT_SERVICE_CLIENT_ID = "jll:property"+RandCodeUtil.getRandomSmsCode(3);
    public final static boolean MQTT_SERVICE_CLEAN_START = true;
	public final static short MQTT_SERVICE_KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s	
	public final static long MQTT_SERVICE_RECONNECTION_ATTEMPT_MAX = 6;
	public final static long MQTT_SERVICE_RECONNECTION_DELAY = 2000;
	public final static int MQTT_SERVICE_SEND_BUFFER_SIZE = 2 * 1024 * 1024;// 发送最大缓冲为2M
	
}
