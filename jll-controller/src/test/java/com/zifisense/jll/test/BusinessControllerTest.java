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
public class BusinessControllerTest {
		private static  String url = "http://localhost:8091/bussiessGroup";
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
	    public void findBussiessGroupInfoPage(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String uri =  "?bussiessGroupId=1&keys=A";
	        	 String result = RestTester.exchange(url+"/findBussiessGroupInfoPage"+uri,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	    @Test
	    public void addBussiessGroup(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 map.put("groupName", "业务组2");
	        	 String result = RestTester.exchange(url+"/addBussiessGroup",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void updateBussiessGroup(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 map.put("groupName", "业务组11");
	          	 map.put("id", "3");
	        	 String result = RestTester.exchange(url+"/updateBussiessGroup",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void removeBussiessGroup(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	          	 map.put("ids", "3,4");
	        	 String result = RestTester.exchange(url+"/removeBussiessGroup",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getShortBussiessGroupList(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getShortBussiessGroupList",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getBussiessGroupById(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getBussiessGroupById/1",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

