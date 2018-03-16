package com.zifisense.jll.client;

/**
 * 通用请求参数
 *
 * Created by Floki on 2017/6/30.
 */
public class CommonParam {
    /** 用户id */
    private Integer userId;

    /** 产品id */
    private Integer productId;

    /** 产品的访问 Token */
    private String token;

    /** 产品的访问密码 */
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
