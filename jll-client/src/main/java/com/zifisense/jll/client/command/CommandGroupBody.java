package com.zifisense.jll.client.command;

import java.util.List;

/**
 * Created by Floki on 2017/7/5.
 */
public class CommandGroupBody extends CommandBody {

    /** 组号数组 */
    private List<Group> groupItems;

    public List<Group> getGroupItems() {
        return groupItems;
    }

    public void setGroupItems(List<Group> groupItems) {
        this.groupItems = groupItems;
    }
}
