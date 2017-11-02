package com.activiti.demo.controller.impl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.activiti.demo.controller.IEmployeeController;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.entity.LeaveBill;
import com.activiti.demo.service.IEmployeeService;
import com.activiti.demo.utils.BeanUtils;
import com.activiti.demo.utils.StringUtils;


@Controller
@SuppressWarnings("rawtypes")
public class EmployeeControllerImpl implements IEmployeeController{

    @Autowired
    private IEmployeeService employeeService;
    
    @Override
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
        return "login";
    }

    @RequestMapping("/loginAction_top.action")
    public String hello2(ModelMap paramMap) {
        return "top";
    }

    @RequestMapping("/loginAction_welcome.action")
    public String hello3(ModelMap paramMap) {
        return "welcome";
    }

    @RequestMapping("/loginAction_left.action")
    public String hello4(ModelMap paramMap) throws Exception {
        return "left";
    }

    @RequestMapping("/leaveBillAction_home.action")
    public String hello5(ModelMap paramMap) throws Exception {
        return "list";
    }
    
    @RequestMapping("/workflowAction_deployHome")
    public String deployHome(HttpServletRequest request){
        //1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = employeeService.findDeploymentList();
        List<ProcessDefinition> pdList = employeeService.findProcessDefinitionList();
        request.setAttribute("depList", depList);
        request.setAttribute("pdList", pdList);
      return "workflow";
  }
    
    @RequestMapping("/workflowAction_listTask.action")
    public String hello7(ModelMap paramMap) throws Exception {
        return "task";
    }

    @RequestMapping("/loginAction_logout")
    public String hello8(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return "login";
    }

    /** 查看流程图  */
    @Override
    @RequestMapping("/workflowAction_viewImage")
    public String viewImage(@RequestParam Map paramMap,HttpServletResponse response) {
        String deploymentId = String.valueOf(paramMap.get("deploymentId"));
        String imageName = String.valueOf(paramMap.get("imageName"));
        InputStream in = null;
        OutputStream out = null;
        try {
            in = employeeService.findImageInputStream(deploymentId, imageName);
            out = response.getOutputStream();
            // 4：将输入流中的数据读取出来，写到输出流中
            for(int b = -1;(b = in.read()) != -1;){
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 删除部署信息  */
    @Override
    @RequestMapping("/workflowAction_delDeployment")
    public String delDeployment(@RequestParam Map paramMap){
        //1：获取部署对象ID 
        String deploymentId = String.valueOf(paramMap.get("deploymentId"));
        //2：使用部署对象ID，删除流程定义
        employeeService.deleteProcessDefinitionByDeploymentId(deploymentId);
        ////重定向到 /workflowAction_deployHome 的Controller方法(即deployHome)上
        return "redirect:/workflowAction_deployHome";
    }
    
    /** 发布流程  */
    @Override
    @RequestMapping("/workflowAction_newdeploy")
    public String newdeploy(HttpServletRequest request, String filename, @RequestParam("file") MultipartFile file){
        if(!file.isEmpty()) {
            //上传文件路径 E:\apache-tomcat-7.0.75\wtpwebapps\com.activiti.web
            String path = request.getServletContext().getRealPath("/file/");
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                file.transferTo(new File(path + File.separator + filename));
                employeeService.saveNewDeploye(filepath, filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "error";
        }
        return "redirect:/workflowAction_deployHome";
    }
    
    /** 添加请假申请 */
    @Override
    @RequestMapping("/leaveBillAction_input")
    public String input(Map paramMap){
        
        return "input";
    }
    
    /** 保存/更新，请假申请 */
    @SuppressWarnings("unchecked")
    @Override
    @RequestMapping("/leaveBillAction_save")
    public String save(@RequestParam Map paramMap, HttpServletRequest request){
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if(StringUtils.isNotNull(employee)){
            LeaveBill leaveBill = (LeaveBill) BeanUtils.reflectionCopyJavaBean(paramMap, LeaveBill.class);
            leaveBill.setUserId(employee.getId());
            leaveBill.setState("0");
            boolean flag = employeeService.save(leaveBill);
            if(flag){
                return "list";
            }
        }
        return "login";
    }
    
    /** 请假管理首页显示 */
    @Override
    @RequestMapping("/leaveBillAction_home")
    public String home(HttpServletRequest request){
        List<LeaveBill> list = employeeService.findLeaveBillList();
        request.setAttribute("list", list);
        return "list";
    }
}
