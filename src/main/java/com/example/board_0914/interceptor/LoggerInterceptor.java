package com.example.board_0914.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ preHandle ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    log.debug("    Request URI     : {}", request.getRequestURI());
    log.debug("    Request Method  : {}", request.getMethod());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ postHandle ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
  }

//  @Override
//  public void afterCompletion(
//      HttpServletRequest request, HttpServletResponse response,
//      Object obj, Exception e) {
//    log.debug("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ afterCompletion ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
//  }
}