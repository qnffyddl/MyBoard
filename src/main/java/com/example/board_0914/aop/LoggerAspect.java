package com.example.board_0914.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

  @Before("execution(* com.example.board_0914.controller.*.*(..)) || execution(* com.example.board_0914..service.*Impl.*(..)) || execution(* com.example.board_0914..mapper.board.*Mapper.*(..))")
  public void logPrint(JoinPoint joinPoint) throws Throwable {
    String type = "";
    String name = joinPoint.getSignature().getDeclaringTypeName();
    if(name.indexOf("Controller") > -1) {
      type = "Controller  \t:  ";
    }
    else if(name.indexOf("Service") > -1) {
      type = "ServiceImpl  \t:  ";
    }
    else if(name.indexOf("Mapper") > -1) {
      type = "Mapper  \t\t:  ";
    }
    log.debug("before"+type + name + "." + joinPoint.getSignature().getName() + "()");
    //return joinPoint.proceed();
  }

  @AfterReturning("execution(* com.example.board_0914.controller.*.*(..)) || execution(* com.example.board_0914..service.*Impl.*(..)) || execution(* com.example.board_0914..mapper.board.*Mapper.*(..))")
  public void afterlogPrint(JoinPoint joinPoint) throws Throwable {
    String type = "";
    String name = joinPoint.getSignature().getDeclaringTypeName();
    if(name.indexOf("Controller") > -1) {
      type = "Controller  \t:  ";
    }
    else if(name.indexOf("Service") > -1) {
      type = "ServiceImpl  \t:  ";
    }
    else if(name.indexOf("Mapper") > -1) {
      type = "Mapper  \t\t:  ";
    }
    log.debug("after"+type + name + "." + joinPoint.getSignature().getName() + "()");
    //return joinPoint.proceed();
  }

}