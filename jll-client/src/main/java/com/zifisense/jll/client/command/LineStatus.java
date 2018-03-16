package com.zifisense.jll.client.command;

/**
 * Created by Floki on 2017/10/11.
 */
public class LineStatus {
    /** 线控设备的线路号 */
    private Integer line;

    /** 控制状态：on=线路打开；off=线路关闭； */
    private String status;

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
