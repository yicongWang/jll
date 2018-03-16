package com.zifisense.jll.client.command;

/**
 * 命令枚举
 *
 * Created by Floki on 2017/6/30.
 */
public enum Command {

    /** 单控设备调光的命令 */
    dimming

    /** 单控开灯的命令 */
    , on

    /** 单控关灯的命令 */
    , off

    /** 单控查询灯信息的命令 */
    , info

    /** 单控查询灯状态的命令 */
    , status

    /** 单控查询灯实时信息的命令 */
    , realtime

    /** 设置路灯分组的命令 */
    , setGroup

    /** 组控开灯的命令 */
    , multiOn

    /** 组控关灯的命令 */
    , multiOff

    /** 组控查询灯信息的命令 */
    , multiInfo

    /** 组控查询灯状态的命令 */
    , multiStatus

    /** 组控查询灯实时信息的命令 */
    , multiRealtime

    /** 组控设备调光的命令 */
    , mutiDimming

    /** 群控开灯的命令 */
    , broadOn

    /** 群控关灯的命令 */
    , broadOff

    /** 群控调光的命令 */
    , broadDimming

    /** 群控查询设备信息的命令 */
    , broadInfo

    /** 群控查询设备状态的命令 */
    , broadStatus

    /** 群控查询设备实时信息的命令 */
    , broadRealtime

    /** 设置定时任务 */
    , setTask

    /** 控制继电器线路开关 */
    , setRelay
}
