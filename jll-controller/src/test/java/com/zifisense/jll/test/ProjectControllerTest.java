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
public class ProjectControllerTest {
		private static  String url = "http://localhost:8091/project";
		private static  String access_token = null;
		@BeforeClass
	    public static void login(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();
	        	 map.put("account", "499345857@qq.com");
	        	 map.put("password", "Wyc111111");
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
	    public void findProjectInfoPage(){
	         try {
	         	 Map<String,Object>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	         	 String uri =  "?projectId=1&keys=";
	        	 String result = RestTester.exchange(url+"/findProjectInfoPage"+uri,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	    @Test
	    public void addProject(){
	         try {
	         	 Map<String,String>  map = new HashMap<>(); 
	        	 map.put("access_token",access_token);
	        	 map.put("projectNameCn", "中文项目1");
	        	 map.put("projectNameEn", "En-Project");
	        	 map.put("address", "厦门集美");
	        	// map.put("floorArea", "1000");
	        	 map.put("nameModel", "0");
	        	 map.put("accountIds", "144");
	        	 map.put("businessGroupIds", "2");
	        	 map.put("sysIdJoinCodes", "2:EASDFQ,1:SDASA");
	        	 
	        	 map.put("floorHight", "28.00");
	        	 map.put("bulidYear", "2018");
	        	 map.put("catchTime", "2018-02-01");
	        	 map.put("businessType", "xf");
	        	 map.put("zipCode", "3610000");
	        	 map.put("cityName", "厦门");
	        	 
	        	 
	        	 String result = RestTester.exchange(url+"/addProject",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void updateProject(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();
	        	 map.put("access_token",access_token);
	        	 map.put("id", "132");
		       	 map.put("projectNameCn", "中文项目2");
	        	 map.put("projectNameEn", "En-Project");
	        	 map.put("address", "厦门集美");
	        	// map.put("floorArea", "1000");
	        	 map.put("nameModel", "0");
	        	 map.put("accountIds", "90");
	        	 map.put("businessGroupIds", "2");
	        	 map.put("sysIdJoinCodes", "2:EASDFQ2,1:SDASA1");
	        	 
	        	 map.put("floorHight", "28.00");
	        	 map.put("bulidYear", "2018");
	        	 map.put("catchTime", "2018-02-01");
	        	 map.put("businessType", "xf");
	        	 map.put("zipCode", "3610000");
	        	 map.put("cityName", "厦门");
	        	 String result = RestTester.exchange(url+"/updateProject",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void removeProject(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	          	 map.put("ids", "131");
	        	 String result = RestTester.exchange(url+"/removeProject",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getShortProjectList(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getShortProjectList",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getProjectById(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getProjectById/73",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void getProjectSysCodeList(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getProjectSysCodeList/73",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getProjectAcountAble(){
	         try {
	         	 Map<String,String>  map = new HashMap<>();  
	        	 map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getProjectAcountAble",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
}

