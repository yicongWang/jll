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
public class AlarmControllerTest {
		private static  String url = "http://localhost:8091/alarm";
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
		    public void findCurrentAlarmPage(){
		         try {
		         	 Map<String,Object>  map = new HashMap<>();  
		        	 map.put("access_token",access_token);
		        	 map.put("appSourceCodes","FAS");
		        	 map.put("projectId","75");
		        	 map.put("startYm","201801");
		        	 map.put("endYm","201803");
		        	 String params = "appSourceCodes=FAS&projectId=75&startYm=201701&endYm=201812";
		        	 String result = RestTester.exchange(url+"/findCurrentAlarmPage?"+params,map,HttpMethod.GET, String.class);
		        	 System.out.println(result);
		         } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		
	    @Test
	    public void updateAlarmState(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 map.put("ids", "130,112");
	        	 String result = RestTester.exchange(url+"/updateAlarmState",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void exportCurrentAlarmList(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String params = "?appSourceCodes=FAS&projectId=75&startYm=201701&endYm=201812";
	        	 String result = RestTester.exchange(url+"/exportCurrentAlarmList"+params,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void findHistoryAlarmPage(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String params = "?appSourceCodes=FAS&projectId=&startYm=201701&endYm=201812&dataType=0";
	        	 String result = RestTester.exchange(url+"/findHistoryAlarmPage"+params,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void exportHistoryAlarmList(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String params = "?appSourceCodes=FAS&projectId=75&startYm=201701&endYm=201812";
	        	 String result = RestTester.exchange(url+"/exportHistoryAlarmList"+params,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

