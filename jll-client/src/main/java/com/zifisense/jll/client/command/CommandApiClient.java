package com.zifisense.jll.client.command;

import com.zifisense.jll.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Floki on 2017/6/30.
 */
@Component
public class CommandApiClient extends RestClient {
    /** 日志 */
    private Logger logger = LoggerFactory.getLogger(CommandApiClient.class);

    /** 发送命令的接口地址和参数定义 */
    private static String POST_SEND_COMMAND = "/commands/{deviceId}?token={token}&type={type}&peroid={peroid}";

    /** 获取命令状态的接口地址和参数定义 */
    private static String GET_COMMAND_STATUS = "/commands/status/{commandId}";

    /**
     * 发送命令
     *
     * @param deviceId 设备id
     * @param token 设备发送命令的token
     * @param data 命令的数据
     * @return 命令的id
     */
    public String sendCommand(String deviceId, String token, String data) {
        return sendCommand(deviceId, token, CommandType.JSON, 30, data);
    }

    /**
     * 发送命令
     *
     * @param deviceId 设备id
     * @param token 设备发送命令的token
     * @param type 发送命令的数据类型
     * @param peroid 命令有效时长，单位：秒
     * @param data 命令的数据
     * @return 命令的id
     */
    public String sendCommand(String deviceId, String token, CommandType type, int peroid, String data) {
        Map<String, Object> vars = new HashMap<>(4);
        vars.put("deviceId", deviceId);
        vars.put("token", token);
        vars.put("type", type.getType());
        vars.put("peroid", peroid);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> formEntity = new HttpEntity<>(data, requestHeaders);
        String url = config.getRootUrl() + POST_SEND_COMMAND;
        logger.info("Send Command Url={}, body={}, params={}", url, data, vars.toString());

        ResponseEntity<String> result;
        try {
            result = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class, vars);
        } catch (Exception e) {
            result = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class, vars);
        }
        return null == result ? "" : result.getBody();
    }

    /**
     * 获取指定命令的状态
     *
     * @param commandId 命令id
     * @return 命令状态：saved、sended、acked、failed、canceled
     */
    public String getCommandStatus(String commandId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("commandId", commandId);
        return restTemplate.getForObject(config.getRootUrl() + GET_COMMAND_STATUS, String.class, params);
    }

}
