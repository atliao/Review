package com.la.xuecheng.base.exception;

/**
 * @author LA
 * @createDate 2024-06-20-20:28
 * @description
 */
public class XueChengPlusException extends RuntimeException{

    private String errMessage;

    public XueChengPlusException(){

    }

    public XueChengPlusException(String message){
        super(message);
        this.errMessage = message;
    }

    public static void cast(String message){
        throw new XueChengPlusException(message);
    }

    public static void cast(CommonError error){
        throw new XueChengPlusException(error.getErrMessage());
    }
}
