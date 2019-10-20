package com.tw.apistackbase.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class CompanyServiceAspect {

    public static final boolean isValid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.tw.apistackbase.service.*.*(..))")
    private void generalPointCut(){}

    @Before("generalPointCut()")
    public void around (JoinPoint joinPoint){
        logger.info("Before for user access...");
    }

//    @Around("generalPointCut()")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("Around execution of {}", joinPoint);
//
//        if(isValid){
//            joinPoint.proceed();
//        }
//        else
//        {
//            logger.info("Something is wrong");
//        }
//
//    }

    @AfterThrowing(pointcut = "generalPointCut()")
    public void afterThrowing(JoinPoint joinPoint){
        logger.info("Exception found {}",joinPoint);
    }


    @After("generalPointCut()")
    public void after(JoinPoint joinPoint){
        logger.info("After execution of {}", joinPoint);
        joinPoint.getTarget().getClass().getSimpleName();
    }
}
