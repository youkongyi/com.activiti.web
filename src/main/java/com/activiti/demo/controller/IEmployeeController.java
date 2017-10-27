package com.activiti.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.activiti.demo.entity.JsonResult;

public interface IEmployeeController {
    //根据姓名查找员工
    JsonResult findEmployeeByName(Map paramMap, HttpSession session);
}
