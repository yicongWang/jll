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
public class ReportControllerTest {
		private static  String url = "http://localhost:8091/report";
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
	    public void countAlarmReportAOne(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportAOne",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void countAlarmReportATwo(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportATwo",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void countAlarmReportAThree(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportAThree",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void countAlarmReportBOne(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportBOne",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void countAlarmReportBTwo(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportBTwo",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void countAlarmReportBThree(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportBThree",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void countAlarmReportCOne(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportCOne",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void countAlarmReportCTwo(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportCTwo",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void countAlarmReportCThree(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/countAlarmReportCThree",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void findAlarmPageByReportA(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/findAlarmPageByReportA",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void findAlarmPageByReportB(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/findAlarmPageByReportB",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void findAlarmPageByReportC(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/findAlarmPageByReportC",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

