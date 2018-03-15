package com.chengwenbi.controller.base;

import com.chengwenbi.common.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class BaseController {

    protected Result result = new Result<Object>(true);

}
