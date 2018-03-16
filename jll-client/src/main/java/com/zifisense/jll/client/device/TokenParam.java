package com.zifisense.jll.client.device;

import com.zifisense.jll.client.CommonParam;

/**
 * 获取设备相关Token的请求参数
 *
 * Created by Floki on 2017/6/30.
 */
public class TokenParam extends CommonParam {
    /** 设备sn */
    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
