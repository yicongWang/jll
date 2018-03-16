package com.zifisense.jll.client.data;

/**
 * Created by Floki on 2017/7/4.
 */
public enum StreamType {
    /** 设备产品信息数据流 */
    jllInfo

    /** 路灯状态信息数据流 */
    , jllStatus

    /** 路灯实时数据信息数据流 */
    , jllRealtime

    /** 设备接入网关信息数据流 */
    , gwInfo

    /** 设备故障信息 */
    , jllAlarm

    /** 心跳包信息 */
    , heartbeat

    /** 井盖倾斜角度信息 */
    , tiltStatus

    /** 智能电表电能信息 */
    , gpmElecInfo

    /** 智能电表功率信息 */
    , gpmPowerInfo

    /** 电表功率因素信息 */
    , gpmPowerFactorInfo
}