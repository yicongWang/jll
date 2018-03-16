package com.zifisense.jll.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zifisense.jll.dao.ContactAdminMapperExt;
import com.zifisense.jll.model.ContactAdmin;
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.ContactAdminService;
import com.zifisense.jll.service.PushModelService;
import com.zifisense.jll.service.common.RoleEnum;
import com.zifisense.jll.vo.AccountShortVo;

/**
 * 联系管理员
 * @author wyc
 *
 */
@Service
public class ContactAdminServiceImpl implements ContactAdminService {

    private Logger logger = LoggerFactory.getLogger(ContactAdminServiceImpl.class);
    
    @Autowired
    private ContactAdminMapperExt contactAdminMapperExt;
    @Autowired
    private PushModelService pushModelService;
    @Autowired
    private AccountService accountService;
    @Transactional
	@Override
	public void save(String title, String content, Long createrId) {
		ContactAdmin contactAdmin = new ContactAdmin();
		contactAdmin.setContent(content);
		contactAdmin.setId(createrId);
		contactAdmin.setState(1);//已通知
		contactAdmin.setSumbitAccountId(createrId);
		contactAdmin.setTitle(title);
		contactAdmin.setCreateTime(new Date());
		contactAdminMapperExt.insert(contactAdmin);
		//发送邮件通知管理员
		List<AccountShortVo> adminAccountList = accountService.getShortAcountList(RoleEnum.ADMIN.getCode(), null);
		pushModelService.notifyEmailToAccount(title,content,adminAccountList);
	}
  
}
