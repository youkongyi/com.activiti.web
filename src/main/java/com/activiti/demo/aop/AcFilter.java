package com.activiti.demo.aop;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/AcFilter")
public class AcFilter implements Filter {
    private FilterConfig filterConfig;
    
    public void setFilterConfig(FilterConfig filterConfig) {  
        this.filterConfig = filterConfig;  
    }  
  
    @Override  
    public void destroy() {  
        this.filterConfig = null;
    }  
  
    @Override  
    public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException,ServletException {  
        System.err.println("...doFilter...");  
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
        return;
    }  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.filterConfig = filterConfig;  
    }  
  
    public static boolean isContains(String container, String[] regx) {
        boolean result = false;
        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }
    
    public static String getCookie(HttpServletRequest request,String cookieName) {
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookieName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
}