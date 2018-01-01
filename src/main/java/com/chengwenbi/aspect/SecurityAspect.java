package com.chengwenbi.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {

    @Pointcut("execution(public * com.chengwenbi.controller..*(..))")
    public void verification() {
    }

    @Before("verification()")
    public void testAop() {
        System.out.println("Before:拦截进入testController的方法！");
    }
}
