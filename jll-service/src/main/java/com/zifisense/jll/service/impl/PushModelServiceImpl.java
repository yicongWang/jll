package com.zifisense.jll.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.dao.PushModelMapperExt;
import com.zifisense.jll.model.PushModel;
import com.zifisense.jll.service.PushModelService;
import com.zifisense.jll.util.MailUtill;
import com.zifisense.jll.vo.AccountShortVo;

/**
 * 菜单ServiceImpl
 * @author wyc
 *
 */
@Service
public class PushModelServiceImpl implements PushModelService {

    @Autowired
    private PushModelMapperExt pushModelMapperExt;

    @Autowired
  	private MailUtill mailUtill; 
	@Override
	public List<PushModel> queryPushModelList() {
		return pushModelMapperExt.selectByExample(null);
	}

	@Override
	public void updatePushModel(Long id, Integer state, Long modifyId) {
		PushModel record = new PushModel();
		record.setId(id);
		record.setState(state);
		record.setModifier(modifyId);
		record.setModifyTime(new Date());
		pushModelMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
	public void notifyEmailToAccount(String title,String content,List<AccountShortVo> adminAccountList){
    	if(CollectionUtils.isNotEmpty(adminAccountList)){
    		for(AccountShortVo accountShortVo : adminAccountList){
    			if(StringUtils.isNotBlank(accountShortVo.getEmail())){
    				mailUtill.sendEmail(accountShortVo.getEmail(), title, content);
    			}
    		}
    	}
    }

}
