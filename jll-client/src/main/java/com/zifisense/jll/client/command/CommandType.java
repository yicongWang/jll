package com.zifisense.jll.client.command;

/**
 * 发送命令的数据类型枚举
 *
 * Created by Floki on 2017/6/30.
 */
public enum CommandType {
    /** json类型 */
    JSON("json")

    /** int类型 */
    , INT("int")

    /** double类型 */
    , DOUBLE("double")

    /** string类型 */
    , STRING("string")

    /** bin类型(二进制) */
    , BIN("bin")

    ;

    /** 数据类型 */
    private String type;

    /** 构造方法 */
    CommandType(String type) {
        this.type = type;
    }

    /**
     * 获取命令的数据类型
     *
     * @return 返回命令的数据类型
     */
    public String getType() {
        return type;
    }
}
