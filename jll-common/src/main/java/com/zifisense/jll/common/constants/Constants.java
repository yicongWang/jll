package com.zifisense.jll.common.constants;

/**
 * 通用常量类
 * Created by DW on 2017/6/28.
 */
public class Constants {

    /** 验证码类型： 找回密码 */
    public static final String CAPTCHA_TYPE_RETRIEVE_PASSWORD = "RP";
    /** 验证码类型： 用户注册 */
    public static final String CAPTCHA_TYPE_USER_REGISTRATION = "RU";

    /** 终端类型： PC */
    public static final String TERMINAL_TYPE_PC = "PC";
    /** 终端类型： MOBLIE */
    public static final String TERMINAL_TYPE_MOBLIE = "MOBLIE";


    /** 策略频率： 一次 */
    public static final String STRATEGY_FREQUENCY_ONCE = "single";
    /** 策略频率： 每天 */
    public static final String STRATEGY_FREQUENCY_DAY = "day";
    /** 策略频率： 每周 */
    public static final String STRATEGY_FREQUENCY_WEEKLY = "week";
    /** 策略频率： 间隔 */
    public static final String STRATEGY_FREQUENCY_INTERVAL = "interval";

    /** 策略命令 执行类型： 自定义 */
    public static final String STRATEGY_COMMAND_EXECUTE_TYPE_CUSTOM = "custom";
    /** 策略命令 执行类型： 日出 */
    public static final String STRATEGY_COMMAND_EXECUTE_TYPE_SUNRISE = "sunrise";
    /** 策略命令 执行类型： 日落 */
    public static final String STRATEGY_COMMAND_EXECUTE_TYPE_SUNSET = "sunset";

    /** 策略命令  功能选择 开灯*/
    public static final String FUNCTION_SELECTION_TURN_ON = "on";
    /** 策略命令  功能选择 关灯*/
    public static final String FUNCTION_SELECTION_TURN_OFF = "off";
    /** 策略命令  功能选择 调节亮度*/
    public static final String FUNCTION_SELECTION_ADJUST_BRIGHTNESS = "dimming";
    /** 策略命令  功能选择 读取状态*/
    public static final String FUNCTION_SELECTION_READ_STATUS = "status";


    /** 下发策略 接入方类型 ：单播 */
    public static final String ISSUED_STRATEGY_UNICAST = "unicast";
    /** 下发策略 接入方类型 ：组播 */
    public static final String ISSUED_STRATEGY_MULTICAST = "group";
    /** 下发策略 接入方类型 ：广播 */
    public static final String ISSUED_STRATEGY_RADIO = "broadcast";


}
