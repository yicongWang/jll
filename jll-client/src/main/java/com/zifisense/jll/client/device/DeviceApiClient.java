package com.zifisense.jll.client.device;


import com.zifisense.jll.client.RestClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Floki on 2017/6/30.
 */
@Component
public class DeviceApiClient extends RestClient {
    /** 查询某个产品下的设备列表 */
    private static String GET_DEVICES = "/devices/{userId}/{productId}?token={token}&password={password}&time={time}&type={type}";

    /** 查询某个设备的 Query Token */
    private static String GET_QUERY_TOKEN = "/token/device/query/{userId}/{productId}?token={token}&password={password}&sn={sn}";

    /** 查询某个设备的 Cmd Token */
    private static String GET_CMD_TOKEN = "/token/device/cmd/{userId}/{productId}?token={token}&password={password}&sn={sn}";

    /** 查询某个设备的 Upload Token */
    private static String GET_UPLOAD_TOKEN = "/token/device/upload/{userId}/{productId}?token={token}&password={password}&sn={sn}";

    /** 获取某个设备的在线状态 */
    private static String GET_ONLINE = "/devices/online/{deviceId}?token={token}";

    /** 获取某个产品下的设备数量 */
    private static String GET_DEVICE_COUNT = "/devices/count/{userId}/{productId}?token={token}&password={password}";

    /**
     * 获取某个应用下的设备数量
     *
     * @param userId 用户id
     * @param productId 产品id
     * @param token 应用访问token
     * @param password 应用访问密码
     * @param title 设备类型
     * @return 返回设备数量
     */
    public int getDeviceCount(Integer userId, Integer productId, String token, String password, String title) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("token", token);
        params.put("password", password);

        String url = config.getRootUrl() + GET_DEVICE_COUNT;
        if (!StringUtils.isEmpty(title)) {
            url += "&title={title}";
            params.put("title", title);
        }
        return restTemplate.getForObject(url, Integer.class, params);
    }

    /**
     * 获取某个产品下的设备列表
     *
     * @param userId 用户id
     * @param productId 产品id
     * @param token 应用访问的token
     * @param password 应用访问的密码
     * @param time 获取时间
     * @param type 操作类型
     * @return 返回设备列表信息
     */
    public String getDevice(Integer userId, Integer productId, String token, String password, String time, Operation type) {
        Map<String, Object> params = new HashMap<>(6);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("token", token);
        params.put("password", password);
        params.put("time", time);
        params.put("type", type.name());
        return restTemplate.getForObject(config.getRootUrl() + GET_DEVICES, String.class, params);
    }

    /*public List<DeviceInfo> getDevice(Integer userId, Integer productId, String token, String password, String time, Operation type) {
        Map<String, Object> params = new HashMap<>(6);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("token", token);
        params.put("password", password);
        params.put("time", time);
        params.put("type", type.name());

        ParameterizedTypeReference<List<DeviceInfo>> responseType = new ParameterizedTypeReference<List<DeviceInfo>>(){};
        ResponseEntity<List<DeviceInfo>> list = restTemplate.exchange(config.getRootUrl() + GET_DEVICES, HttpMethod.GET, null, responseType, params);
        return list.getBody();
    }*/

    /**
     * 获取指定设备的查询 Token
     *
     * @param userId 用户id
     * @param productId 应用id
     * @param token 应用的查询token
     * @param password 应用查询token的密码
     * @param sn 设备sn
     * @return 返回设备查询的token
     */
    public String getQueryToken(Integer userId, Integer productId, String token, String password, String sn) {
        Map<String, Object> params = getTokenParam(userId, productId, token, password, sn);
        return restTemplate.getForObject(config.getRootUrl() + GET_QUERY_TOKEN, String.class, params);
    }

    /**
     * 获取指定设备的发送命令 Token
     *
     * @param userId 用户id
     * @param productId 应用id
     * @param token 应用的发送命令token
     * @param password 应用的发送命令token的密码
     * @param sn 设备sn
     * @return 返回设备发送命令的token
     */
    public String getCmdToken(Integer userId, Integer productId, String token, String password, String sn) {
        Map<String, Object> params = getTokenParam(userId, productId, token, password, sn);
        return restTemplate.getForObject(config.getRootUrl() + GET_CMD_TOKEN, String.class, params);
    }

    /**
     * 获取指定设备的上传数据 Token
     *
     * @param userId 用户id
     * @param productId 应用id
     * @param token 应用上传数据的token
     * @param password 应用上传数据的token的密码
     * @param sn 设备sn
     * @return 返回设备上传数据的token
     */
    public String getUploadToken(Integer userId, Integer productId, String token, String password, String sn) {
        Map<String, Object> params = getTokenParam(userId, productId, token, password, sn);
        return restTemplate.getForObject(config.getRootUrl() + GET_UPLOAD_TOKEN, String.class, params);
    }

    /**
     * 获取设备在线状态
     *
     * @param deviceId 设备id
     * @param token 设备查询token
     * @return 返回设备在线状态，false=离线，true=在线
     */
    public boolean getOnline(String deviceId, String token) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("deviceId", deviceId);
        params.put("token", token);
        return restTemplate.getForObject(config.getRootUrl() + GET_ONLINE, Boolean.class, params);
    }

    /**
     * 设置并返回获取相关token的请求参数
     *
     * @param userId 用户id
     * @param productId 应用id
     * @param token 应用响应的token
     * @param password 应用响应token的密码
     * @param sn 设备sn
     * @return 返回获取相关token的请求参数
     */
    public Map<String, Object> getTokenParam(Integer userId, Integer productId, String token, String password, String sn) {
        Map<String, Object> params = new HashMap(5);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("token", token);
        params.put("password", password);
        params.put("sn", sn);
        return params;
    }
}
