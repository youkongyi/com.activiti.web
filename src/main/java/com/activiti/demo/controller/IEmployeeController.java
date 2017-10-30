package com.activiti.demo.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface IEmployeeController {
    //根据姓名查找员工
    String findEmployeeByName(Map paramMap, HttpServletRequest request);
    
    String deployHome(HttpServletRequest request);
}
