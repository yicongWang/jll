package com.zifisense.jll.client.command;

/**
 * Created by Floki on 2017/7/5.
 */
public class CommandGroupJllBody extends CommandBody {
    /** 组号 */
    private Long group;

    /** 顺序 */
    private String seq = "end";

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
