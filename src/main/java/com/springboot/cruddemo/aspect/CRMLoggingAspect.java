package com.springboot.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.springboot.cruddemo.controller.*.*(..))")
    private void forControllerPackage(){}

    // do the same for service and dao
    @Pointcut("execution(* com.springboot.cruddemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.springboot.cruddemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow(){}

    // add @Before advice

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {

        // display method we are callig
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====>>> in @Before: calling method: " + method);

        // display the argument to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop thru and disply args
        for (Object tempArg : args) {
            myLogger.info("====>>> argument: " + tempArg);
        }
    }

    // add @AfterReturning advice

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display method we are returning from

        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====>>> in @AfterReturning: calling method: " + method);

        // display data returned
        myLogger.info("=====>>> result: " + result);

    }

}
