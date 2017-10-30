package com.activiti.demo.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IEmployeeController {
    /** 根据姓名查找员工 */
    String findEmployeeByName(Map paramMap, HttpServletRequest request);
    
    String deployHome(HttpServletRequest request);
    /** 查看流程图  */
    String viewImage(Map paramMap,HttpServletResponse response);
}
