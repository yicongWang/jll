package com.zifisense.jll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.dao.CodeTableMapperExt;
import com.zifisense.jll.model.CodeTable;
import com.zifisense.jll.model.CodeTableExample;
import com.zifisense.jll.service.CodeTableService;

/**
 * 码表
 * @author wyc
 *
 */
@Service
public class CodeTableServiceImpl implements  CodeTableService {

    @Autowired
    private CodeTableMapperExt codeTableMapperExt;

	@Override
	public List<CodeTable> getAllCodeTableList() {
		CodeTableExample example = new CodeTableExample();
        return codeTableMapperExt.selectByExample(example);
	}

	@Override
	public int save(CodeTable codeTable) {
		return codeTableMapperExt.insert(codeTable);
	}

}
