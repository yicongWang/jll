package com.zifisense.jll.client.command;

import java.util.List;

/**
 * Created by Floki on 2017/10/11.
 */
public class CommandLineStatusBody extends CommandBody {

    /** 线路状态列表 */
    private List<LineStatus> relayItems;

    public List<LineStatus> getRelayItems() {
        return relayItems;
    }

    public void setRelayItems(List<LineStatus> relayItems) {
        this.relayItems = relayItems;
    }
}
