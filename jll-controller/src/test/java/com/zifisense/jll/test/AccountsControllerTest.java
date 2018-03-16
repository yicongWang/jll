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
public class AccountsControllerTest {
		private static  String url = "http://localhost:8091/accounts";
		private static  String access_token = null;
		@BeforeClass
	    public static void login(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();
	        	 map.put("account", "499345857@qq.com");
	        	 map.put("password", "111111");
	        	 String result = RestTester.exchange(url+"/login",map,HttpMethod.POST, String.class);
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
	    public void updatePwd(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 map.put("accountId", "1");
	        	 map.put("oldPass", "111111");
	        	 map.put("newPass", "111111");
	        	 String result = RestTester.exchange(url+"/updatePwd",map,HttpMethod.PUT, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void resetPwd(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 map.put("adminPwd", "111111");
	        	 map.put("account", "test001");
	        	 String result = RestTester.exchange(url+"/resetPwd",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void updateMobilePhone(){
	         try {
	        	 Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 map.put("accountId", "1");
	        	 map.put("mobilePhone", "15860487180");
	        	 String result = RestTester.exchange(url+"/updateMobilePhone",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void findAccountInfoByAccountId(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/findAccountInfo/1",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void findAccountInfoPage(){
	         try { 
	        	 Map<String,String>  map = new HashMap<>(); 
	        	 map.put("access_token",access_token);
	        	 String uri =  "?accountId=1&keys=项目";
	        	 String result = RestTester.exchange(url+"/findAccountInfoPage"+uri,map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void findAccountRoleList(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/findAccountRoleList",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    @Test
	    public void addAccount(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 map.put("userNameCn", "张三");
	        	 map.put("userNameEn", "conney");
	        	 map.put("mobilePhone", "15860487180");
	        	 map.put("email", "yicong.wang@zifisense.co.uk");
	        	 map.put("userPosition", "总经理");
	        	 map.put("roleIdentity", "level-three");
	        	 map.put("busiessGroupIds", "1,2,3");
	        	 map.put("projectIds", "1,2");
	        	 String result = RestTester.exchange(url+"/addAccount",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void updateAccount(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	          	 map.put("id", "130");
	        	 map.put("userNameCn", "王");
	        	 map.put("userNameEn", "123456");
	        	 map.put("mobilePhone", "13850575426");
	        	 map.put("userPosition", "经理");
	        	 map.put("projectIds", "1,3,4");
	        	 String result = RestTester.exchange(url+"/updateAccount",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void removeAccount(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 map.put("ids", "130,112");
	        	 String result = RestTester.exchange(url+"/removeAccount",map,HttpMethod.POST, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Test
	    public void getShortAcountList(){
	         try { Map<String,String>  map = new HashMap<>();  map.put("access_token",access_token);
	        	 String result = RestTester.exchange(url+"/getShortAcountList?identity=level-two",map,HttpMethod.GET, String.class);
	        	 System.out.println(result);
	         } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
}

