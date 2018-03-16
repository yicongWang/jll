package com.zifisense.jll.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.zifisense.jll.util.RestTester;

/**
 * @author wyc
 */
public class SysAppSourceControllerTest {
		private static  String url = "http://localhost:8091/sys";
		private static  String access_token = null;
		@BeforeClass
	    public static void login(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();
	        	 map.put("account", "499345857@qq.com");
	        	 map.put("password", "111111");
	        	 String result = RestTester.exchange("http://localhost:8091/accounts/login",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	        	 if(StringUtils.isNoneBlank(result) && StringUtils.contains(result,"accessToken")){
	        		 System.out.println(result.indexOf("accessToken"));
	        		 System.out.println(StringUtils.truncate(result, result.indexOf("accessToken")+14, 40));
	        		 access_token = StringUtils.truncate(result, result.indexOf("accessToken")+14, 40);
	        		 map.put("access_token",access_token);
	        	 }
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getSysSourceList(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getSysSourceList",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

