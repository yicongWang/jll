package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 忘记密码(普通bean)
 * Created by DW on 2017/7/10.
 */
public class ForgotPwdQo extends BasicQo{
    private String authorization;
    private String newPass;
    private String confirmPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
