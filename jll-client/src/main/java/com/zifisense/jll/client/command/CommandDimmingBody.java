package com.zifisense.jll.client.command;

/**
 * Created by Floki on 2017/7/5.
 */
public class CommandDimmingBody extends CommandBody {
    /** 路灯的亮度值 */
    private Integer lightValue;

    public Integer getLightValue() {
        return lightValue;
    }

    public void setLightValue(Integer lightValue) {
        this.lightValue = lightValue;
    }
}
