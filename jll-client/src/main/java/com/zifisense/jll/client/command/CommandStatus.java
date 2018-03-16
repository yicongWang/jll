package com.zifisense.jll.client.command;

/**
 * 命令状态
 *
 * Created by Floki on 2017/7/3.
 */
public enum  CommandStatus {
    /** 命令已保存 */
    saved

    /** 命令已发送 */
    , sended

    /** 终端设备已接收并执行了命令 */
    , acked

    /** 命令发送失败 */
    , failed

    /** 命令已取消 */
    , canceled

}
