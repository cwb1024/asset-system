package com.chengwenbi.common.exception;

public class ValidateParamsException extends Exception {
	
	public ValidateParamsException(Exception e){
        super(e);
    }

    public ValidateParamsException(String message, Exception e){
        super(message,e);
    }

    public ValidateParamsException(String message){
        super(message);
    }

}
