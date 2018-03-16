package com.zifisense.jll.dto;

import com.zifisense.jll.model.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 菜单扩展DTO
 * Created by DW on 2017/7/10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO extends Menu {



    /**
     * 否存选中
     */
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
