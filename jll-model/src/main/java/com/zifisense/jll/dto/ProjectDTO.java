package com.zifisense.jll.dto;

import com.zifisense.jll.model.Project;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 项目扩展DTO
 * Created by DW on 2017/6/29.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO extends Project {

    /**
     * 账户id
     */
    private Long accoutId;

    /**
     * 否存选中
     */
    private Boolean checked;

    public Long getAccoutId() {
        return accoutId;
    }

    public void setAccoutId(Long accoutId) {
        this.accoutId = accoutId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
