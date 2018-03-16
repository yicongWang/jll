package com.zifisense.jll.client.command;

/**
 * 发送命令请求的内容
 *
 * Created by Floki on 2017/7/5.
 */
public class CommandBody {

    /** 设备在 OneNet 平台上的唯一标识 */
    private String deviceId;

    /** 控制的命令 */
    private Command cmd;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Command getCmd() {
        return cmd;
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }
}
