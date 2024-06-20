package com.la.xuecheng.base.exception;

/**
 * @author LA
 * @createDate 2024-06-20-20:32
 * @description
 */
public enum CommonError {

    UNKNOW_ERROR("执行过程异常，请重试"),
    PAPAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private String errMessage;

    public String getErrMessage(){

        return errMessage;
    }

    private CommonError(String errMessage){

        this.errMessage = errMessage;
    }
}
