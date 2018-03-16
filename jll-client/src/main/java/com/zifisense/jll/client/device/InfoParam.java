package com.zifisense.jll.client.device;

import com.zifisense.jll.client.CommonParam;

/**
 * 获取设备信息的参数
 *
 * Created by Floki on 2017/6/30.
 */
public class InfoParam extends CommonParam {
    /** 时间，格式为：yyyyMMddHHmmSS */
    private String time;

    /** 类型：after、before, after: >= time, before < time */
    private String type;

    /** 获取数据的条数 */
    private Integer limit;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
