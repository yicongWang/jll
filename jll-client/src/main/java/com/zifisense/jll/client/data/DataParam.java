package com.zifisense.jll.client.data;

import com.zifisense.jll.client.CommonParam;

/**
 * 获取数据的请求参数
 *
 * Created by Floki on 2017/6/30.
 */
public class DataParam extends CommonParam {

    /** 设备在OneNET平台上唯一标识 */
    private String deviceId;

    /** 设备的查询Token */
    private String token;

    /** 数据流 */
    private String stream;

    /** 返回数据的条数，默认返回1条 */
    private int limit = 1;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
