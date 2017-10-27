package com.activiti.demo.controller.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.activiti.demo.controller.IEmployeeController;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.entity.JsonResult;
import com.activiti.demo.service.IEmployeeService;


@Controller
public class EmployeeControllerImpl implements IEmployeeController{

    @Autowired
    private IEmployeeService employeeService;
    
    @RequestMapping(value = "/loginAction_login.do")
//    @ResponseBody
    public JsonResult findEmployeeByName(@RequestParam Map paramMap,HttpSession session) {
        JsonResult result = new JsonResult();
        Employee employee = employeeService.findEmployeeByName(String.valueOf(paramMap.get("name")));
        session.setAttribute("employee", employee);
        result.setResultData(employee);
        result.setResultState(1);
        result.setResultMessage("2");
        return result;
    }

    @RequestMapping("/index2.do")
//    @ResponseBody
    public String hello(@RequestParam String name, HttpSession session,HttpServletRequest request) throws Exception {
        JsonResult result = new JsonResult();
        System.out.println(name);
        Employee employee = employeeService.findEmployeeByName(name);
        result.setResultData(employee);
        result.setResultState(1);
        result.setResultMessage("2");
        request.setAttribute("employee", employee);
        return "main";
    }
    
    
    @RequestMapping("/loginAction_top.action")
//  @ResponseBody
  public String hello2(ModelMap paramMap, HttpSession session) throws Exception {
      return "top";
  }
    
    @RequestMapping("/loginAction_welcome.action")
//  @ResponseBody
  public String hello3(ModelMap paramMap, HttpSession session,HttpServletRequest request) throws Exception {
        Employee employee = employeeService.findEmployeeByName("范冰冰");
        request.setAttribute("employee", employee);
      return "welcome";
  }
    
    @RequestMapping("/loginAction_left.action")
//  @ResponseBody
  public String hello4(ModelMap paramMap) throws Exception {
      return "left";
  }
    
    @RequestMapping("/leaveBillAction_home.action")
//  @ResponseBody
  public String hello5(ModelMap paramMap) throws Exception {
      return "leaveBill/list";
  } 
    
    @RequestMapping("/workflowAction_deployHome.action")
//  @ResponseBody
  public String hello6(ModelMap paramMap) throws Exception {
      return "workflow/workflow";
  }
    
    @RequestMapping("/workflowAction_listTask.action")
//  @ResponseBody
  public String hello7(ModelMap paramMap) throws Exception {
      return "workflow/task";
  }
    
    
    
    
    
}
