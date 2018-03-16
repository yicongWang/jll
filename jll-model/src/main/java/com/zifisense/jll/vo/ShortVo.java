package com.zifisense.jll.vo;

import com.zifisense.jll.common.BasicVo;

/**
 *对象简称
 * @author wyc
 *
 */
public class ShortVo  extends BasicVo {

	private Long id;
   
    private String name;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
