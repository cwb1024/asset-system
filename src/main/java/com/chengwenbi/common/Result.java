package com.chengwenbi.common;

/**
 * @Deception:
 * @author:chengwenbi
 * @Date:2017/11/22 23:40
 */
public class Result<T extends Object> {
    private boolean success;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        if (success == true) {
            this.setData("操作成功");
        }else {
            this.setData("操作失败");
        }
    }

    public Result(boolean b, String message) {
        this.success = b;
        this.message = message;
    }

    public Result(boolean b, Object data) {
        this.success = b;
        this.message = b == true ? "操作成功" : "操作失败";
        this.data = data;
    }

    public Result(boolean b, String message, Object data) {
        super();
        this.success = b;
        this.message = message;
        this.data = data;
    }

    public  void modifyResult(boolean success , Object data , String message){
        this.setData(data);
        this.setMessage(message);
        this.setSuccess(success);
    }

    public  void modifyResult(boolean success , Object data){
        this.setData(data);
        this.setSuccess(success);
    }

    public  void modifyResult( boolean success , String message){
        this.setMessage(message);
        this.setSuccess(success);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
