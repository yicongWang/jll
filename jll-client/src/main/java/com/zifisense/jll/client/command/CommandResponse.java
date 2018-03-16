package com.zifisense.jll.client.command;

/**
 * 发送命令的响应
 *
 * Created by Floki on 2017/7/5.
 */
public class CommandResponse {
    /** 命令id */
    private String commandId;

    /** 命令状态 */
    private CommandStatus status;

    public CommandResponse(String commandId, CommandStatus status) {
        this.commandId = commandId;
        this.status = status;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }
}
