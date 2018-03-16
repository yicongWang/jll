package com.zifisense.jll.service.mqtt;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zifisense.jll.model.AlarmRecord;
import com.zifisense.jll.service.AlarmRecordService;
import com.zifisense.jll.service.ProjectSysSourceService;
import com.zifisense.jll.service.common.CmptStatusEnum;
import com.zifisense.jll.service.common.CodeTableConstant;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.vo.ProjectSysSourceInfoVo;    
    
/**   
 * 发布消息的回调类   
 *    
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。   
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。 
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。   
 * 必须在回调类中实现三个方法：   
 *  public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。   
 *  public void connectionLost(Throwable cause)在断开连接时调用。   
 *  public void deliveryComplete(MqttDeliveryToken token))   
 *  接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。   
 *  由 MqttClient.connect 激活此回调。   
 *    
 */ 
@Service
public class PushCallback implements MqttCallback {    
    private static Logger logger = LoggerFactory.getLogger(PushCallback.class);
    
    @Autowired
	private MqttClientApplication mqttClientApplication;
    @Autowired
	private ProjectSysSourceService projectSysSourceService;
    @Autowired
   	private  AlarmRecordService alarmRecordService;
    
    public void connectionLost(Throwable cause) {    
        // 连接丢失后，一般在这里面进行重连    
        //System.out.println("连接断开，可以做重连中");   
        logger.info("the mqtt reconection...");
        mqttClientApplication.connectMqttService();
        logger.info("the mqtt Already connected");
    }    
      
    public void deliveryComplete(IMqttDeliveryToken token) {  
        System.out.println("deliveryComplete---------" + token.isComplete());    
    }  
  
    public void messageArrived(String topic, MqttMessage message) throws Exception {  
    	
        // subscribe后得到的消息会执行到这里面    
        System.out.println("接收消息主题 : " + topic);    
        System.out.println("接收消息Qos : " + message.getQos());    
        System.out.println("接收消息内容 : " + new String(message.getPayload()));   
    	try {
    		if(StringUtils.isNotBlank(topic)){
        		String result = new String(message.getPayload());
        		JSONObject json = JSONObject.parseObject(result);
        		if(json.containsKey("companyCode")){
        			List<ProjectSysSourceInfoVo>  list = projectSysSourceService.getProjectSysCodeInfoByCode(json.getString("companyCode"));
        			AlarmRecord record = null;
        			int updateRecord = 0;
        			if(CollectionUtils.isNotEmpty(list)){
        				record = new AlarmRecord();
        				Date currentDate = new Date();
        				ProjectSysSourceInfoVo projectSysSourceInfoVo = list.get(0);
            			BeanUtils.copyProperties(record, projectSysSourceInfoVo);
        				if(MapUtils.getInteger(json, "dataFlag") == 0){//该部件当前状态为正常，对之前告警信息进行归档
        					updateRecord = alarmRecordService.updateAlarmRecordByCondition(MapUtils.getString(json, "companyCode"), MapUtils.getString(json, "deviceType"),null, MapUtils.getString(json, "deviceId"));
    	    				record.setModifyTime(currentDate);//默认归档时间
        				}
        				
            			record.setCreateTime(currentDate);
                		record.setAlarmContent(MapUtils.getString(json, "dataDetail"));
                		//dataFlag:0：正常  1：告警--->DataType 0：告警 1：正常
                		record.setDataType(MapUtils.getInteger(json, "dataFlag") == 1?0:1);
                		record.setDeviceType(MapUtils.getString(json, "deviceType"));
                		record.setDeviceId(MapUtils.getString(json, "deviceId"));
                		//0:告警中 1:解除警报(归档)常规数据默认为归档yyyy-MM-dd
                		record.setAlarmState(MapUtils.getInteger(json, "dataFlag") == 1?0L:1L);
                		record.setAlarmYear(Integer.valueOf(DateUtil.dateToStr(currentDate, "yyyy")));
                		record.setAlarmMonth(Integer.valueOf(DateUtil.dateToStr(currentDate, "MM")));
                		record.setAlarmDay(Integer.valueOf(DateUtil.dateToStr(currentDate, "dd")));
                		record.setAlarmYm(Integer.valueOf(DateUtil.dateToStr(currentDate, "yyyyMM")));
                		record.setSysProjectCode(MapUtils.getString(json, "companyCode"));
                		record.setAlarmTime(currentDate);
                		alarmRecordService.save(record);
                		//有归档或者告警记录要通知相关人员
                		if(updateRecord > 0 || MapUtils.getInteger(json, "dataFlag") == 1){
                			alarmRecordService.sendNotifyByProjectId("告警通知",projectSysSourceInfoVo.getAppSourceName() +projectSysSourceInfoVo.getProjectName()+MapUtils.getString(json, "dataDetail"), projectSysSourceInfoVo.getProjectId());
	    				}
                		
            		}
        		}
        	}
		} catch (Exception e) {
			logger.error(" ---------- handle mqtt message error:"+e.getMessage());
		}
    }    
    
   
} 