package com.zifisense.jll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.dao.AppSourceMapperExt;
import com.zifisense.jll.model.AppSource;
import com.zifisense.jll.model.AppSourceExample;
import com.zifisense.jll.model.AppSourceExample.Criteria;
import com.zifisense.jll.service.AppSourceService;

@Service
public class AppSourceServiceImpl implements AppSourceService {
	
	@Autowired private AppSourceMapperExt appSourceMapperExt;

	@Override
	public List<AppSource> getALLAppSourceInfoList() {
		AppSourceExample appSourceExample = new AppSourceExample();
		Criteria criteria = appSourceExample.createCriteria();
		criteria.andStateEqualTo(InfoState.NORMAL);
		return appSourceMapperExt.selectByExample(appSourceExample);
	}

}
