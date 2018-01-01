package com.chengwenbi.controller.base;

import com.chengwenbi.common.Result;

import javax.servlet.http.HttpServletRequest;


public class BaseController {

    protected Result result = new Result<Object>(true);
    protected HttpServletRequest request;


    protected Object get(String name) {
        return request.getSession().getAttribute(name);
    }

    /**
     * session put
     */
    protected void set(String name, Object value) {
        request.getSession().setAttribute(name, value);
    }

    /**
     * session remove
     */
    protected void remove(String name) {
        request.getSession().removeAttribute(name);
    }

    /**
     * clear session
     */
    protected void clear() {

    }
}
