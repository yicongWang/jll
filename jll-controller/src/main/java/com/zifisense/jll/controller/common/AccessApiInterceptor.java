package com.zifisense.jll.controller.common;



import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.util.RedisUtil;

/**
 * AccessApiInterceptor 拦截器 检查权限
 * Created by ztz on 2016/6/22.
 */
public class AccessApiInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AccessApiInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.redis.keytimeout}")
    private long keytimeout;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> tClass = handlerMethod.getBeanType();
        AccessRequired annotation = tClass.getAnnotation(AccessRequired.class);
        //update by dw 20160902 附加AccessRequired注解到==》类、接口(包括注解类型)或enum上面
        if(annotation==null) {//类上面没有，在检查方法上
            Method method = handlerMethod.getMethod();
            annotation = method.getAnnotation(AccessRequired.class);
        }
        try {
            if (annotation != null) {
                String accessToken = request.getHeader("access_token");
                response.setContentType("text/html;charset=utf-8");
                //String permissionCode = request.getHeader("per_code");
                String URI = request.getRequestURI();
                logger.info("===============请求的URI ：" + URI+" ===============");
                /*String requestMethod = request.getMethod();
                String methodName = method.getName();*/
                JSONObject jsonObject = new JSONObject();
                //如果token不为空,则取值权限，为空则返回token为空请求参数错误400
                if (accessToken != null) {
                    RedisUserData redisUserData = (RedisUserData) redisUtil.get(accessToken);
                    if(redisUserData!=null){	
                        /*Set<String> allowAccessRULSet = redisUserData.getAllowAccessRULSet();
                        if(allowAccessRULSet.size() > 0){
                            //if (allowAccessRULSet.contains(URI)) {
                            if(this.isMatch(allowAccessRULSet,URI)){
                                redisUtil.updateExprieTime(accessToken, keytimeout);
                                return true;
                            } else {
                                jsonObject.put("code", HttpStatus.UNAUTHORIZED);
                                jsonObject.put("msg", "该角色没有权限访问或系统未添加此权限.");
                                response.getWriter().print(jsonObject.toString());
                                return false;
                            }
                        }else if(allowAccessRULSet.size() == 0){
                            jsonObject.put("code", HttpStatus.UNAUTHORIZED);
                            jsonObject.put("msg", "该角色没有赋予任何权限访问.");
                            response.getWriter().print(jsonObject.toString());
                            return false;
                        }else{
                            jsonObject.put("code", HttpStatus.UNAUTHORIZED);
                            jsonObject.put("msg", "未知的权限错误,请联系管理员.");
                            response.getWriter().print(jsonObject.toString());
                            return false;
                        }*/
                    }else{
                        jsonObject.put("code",ResponseCode.ACCESS_INVALID);
                        jsonObject.put("msg", "令牌失效,请重新登录.");
                        response.getWriter().print(jsonObject.toString());
                        return false;
                    }
                } else {
                    jsonObject.put("code", ResponseCode.AUTHORIZE_FAILED);
                    jsonObject.put("msg", "未通过鉴权,请求令牌有误.");
                    response.getWriter().print(jsonObject.toString());
                    return false;
                }
            }
        }catch(Exception e){
            logger.error("Error from AccessApiInterceptor!",e);
            e.printStackTrace();
        }
        // 没有注解通过拦截
        return true;
    }


    /**
     * 检查当前uri 是否是以允许的uri开头的
     * @param allowAccessRULSet 允许的uri集合
     * @param uri   当前uri
     * @return  true:表示 是；false：否
     */
    private boolean isMatch(Set<String> allowAccessRULSet, String uri){
        // TODO: 2017/6/28  暂时不验证，返回true
        return true;
/*        for(String allowURI : allowAccessRULSet){
//           logger.info("allowURI = "+allowURI+"\t【"+uri.startsWith(allowURI)+"]");
            if(uri.startsWith(allowURI)){
                return true;
            }
        }
        return false;*/
    }


}
