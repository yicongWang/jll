package com.zifisense.jll.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AccountPageQo  extends AccountQo{
	
	/** 页码 */
    @ApiModelProperty(value = "当前页码(默认第一页)")
    private Integer pageNum = 1;

    /** 每页显示的条数 */
    @ApiModelProperty(value = "每页显示的条数(默认20条)")
    private Integer pageSize = 20;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

	
}
