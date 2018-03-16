package com.zifisense.jll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.util.MailUtill;

/**
 * Created by DW on 2017/6/26.
 */
@RestController
public class TestController {
	 @Autowired
	 private MailUtill mailUtill; 
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "hello !";
    }
    
    @RequestMapping(value = "/sendemail",method = RequestMethod.GET)  
    public String sendEmail()  
    {  
    	mailUtill.sendEmail("499345857@qq.com", "主题", "内容");
        return  "success";
    }  
}
