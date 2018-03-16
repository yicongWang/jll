package com.zifisense.jll.client.data;

import com.zifisense.jll.client.RestClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取设备数据api接口的连接
 *
 * Created by Floki on 2017/6/30.
 */
@Component
public class DataApiClient extends RestClient {

    /** 获取最近数据的接口地址和参数定义 */
    private static String GET_LATEST_DATA = "/datas/latest/{userId}/{productId}/{deviceId}?token={token}&stream={stream}&limit={limit}";

    /** 获取数据的接口地址和参数定义 */
    private static String GET_DATA = "/datas/{userId}/{productId}/{deviceId}?token={token}&stream={stream}&type={type}&time={time}";

    /**
     * 获取设备最新的一条数据
     *
     * @param userId 用户id
     * @param productId 产品id
     * @param deviceId 设备id
     * @param token 设备的查询token
     * @param stream 获取数据的代码(数据流)
     * @return 返回设备最新的数据
     */
    public String getLatestData(int userId, int productId, String deviceId, String token, StreamType stream) {
        Map<String, Object> params = new HashMap<>(6);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("deviceId", deviceId);
        params.put("token", token);
        params.put("stream", stream);
        params.put("limit", 1);
        return restTemplate.getForObject(config.getRootUrl() + GET_LATEST_DATA, String.class, params);
    }

    /**
     * 获取设备指定数据流的数据
     *
     *  @param userId 用户id
     * @param productId 产品id
     * @param deviceId 设备id
     * @param token 设备的查询token
     * @param stream 获取数据的代码(数据流)
     * @param type 获取类型：gt, gte, lt, lte
     * @param time 日期格式：yyyyMMddHHmmSS, 比如：20170510120000
     * @return 数据列表
     */
    public String getData(int userId, int productId, String deviceId, String token, StreamType stream, String type, String time) {
        Map<String, Object> params = new HashMap<>(6);
        params.put("userId", userId);
        params.put("productId", productId);
        params.put("deviceId", deviceId);
        params.put("token", token);
        params.put("stream", stream);
        params.put("type", type);
        params.put("time", time);
        return restTemplate.getForObject(config.getRootUrl() + GET_DATA, String.class, params);
    }
}
