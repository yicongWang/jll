package com.zifisense.jll.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * Created by dds on 2016/3/14.
 *
 * @param <T> the type parameter
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> extends BaseResponse {
    /**
     * 单个对象
     */
    private T data;

    /**
     * The parameters Error map.
     * add by sxt 2016/07/25
     */
    private Map<String, String> errorMap;

    public CommonResponse() {
        super();
    }

    public static CommonResponse success(){
        return new CommonResponse(ResponseCode.SUCCESS,"Success");
    }

    public static CommonResponse success(int rows){
        return new CommonResponse(ResponseCode.SUCCESS,"Success; Affect the record "+rows);
    }
    public static CommonResponse success(Object data){
        CommonResponse comm = success();
        comm.setData(data);
        return comm;
    }

    public CommonResponse(int code) {
        super(code);
    }

    public CommonResponse(int code, String msg) {
        super(code, msg);
    }

    public CommonResponse(int code, String msg, Map<String, String> errorMap) {
        this(code, msg);
        this.errorMap = errorMap;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets error map.
     *
     * @return the error map
     */
    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    /**
     * Sets error map.
     *
     * @param errorMap the error map
     */
    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
