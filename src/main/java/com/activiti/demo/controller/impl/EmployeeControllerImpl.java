package com.activiti.demo.controller.impl;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.activiti.demo.controller.IEmployeeController;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.service.IEmployeeService;
import com.activiti.demo.utils.BeanUtils;
import com.activiti.demo.utils.StringUtils;


@Controller
@SuppressWarnings("rawtypes")
public class EmployeeControllerImpl implements IEmployeeController{

    @Autowired
    private IEmployeeService employeeService;
    
    @RequestMapping(value = "/loginAction_login")
    public String findEmployeeByName(@RequestParam Map paramMap,HttpServletRequest request) {
        Employee employee = null;
        String name = null;
        try {
            name = String.valueOf(paramMap.get("name"));
            if(StringUtils.isNotNull(name)){
                employee = employeeService.findEmployeeByName(name);
                if(StringUtils.isNotNull(employee)){
                    request.getSession().setAttribute("employee", employee);
                    return "main";
                }
            }
            employee = (Employee) request.getSession().getAttribute("employee");
            if(StringUtils.isNotNull(employee)){
                name = employee.getName();
                if(StringUtils.isNotNull(name)){
                    return "main";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "../../login";
    }

    
    
    @RequestMapping("/loginAction_top.action")
  public String hello2(ModelMap paramMap) {
      return "top";
  }
    
    @RequestMapping("/loginAction_welcome.action")
  public String hello3(ModelMap paramMap){
      return "welcome";
  }
    
    @RequestMapping("/loginAction_left.action")
  public String hello4(ModelMap paramMap) throws Exception {
      return "left";
  }
    
    @RequestMapping("/leaveBillAction_home.action")
  public String hello5(ModelMap paramMap) throws Exception {
      return "leaveBill/list";
  } 
    
    @RequestMapping("/workflowAction_deployHome.action")
    public String deployHome(HttpServletRequest request){
        //1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = employeeService.findDeploymentList();
        List<ProcessDefinition> pdList = employeeService.findProcessDefinitionList();
        request.setAttribute("depList", depList);
        request.setAttribute("pdList", pdList);
      return "workflow/workflow";
  }
    
    @RequestMapping("/workflowAction_listTask.action")
  public String hello7(ModelMap paramMap) throws Exception {
      return "workflow/task";
  }
    
    @RequestMapping("/loginAction_logout.action")
  public String hello8(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
      return "../../login";
  }
    
    
    
}
