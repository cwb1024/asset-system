package com.chengwenbi.common.exception;

public class ServiceException extends Exception{

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String msg,Exception e) {
        super(msg,e);
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
