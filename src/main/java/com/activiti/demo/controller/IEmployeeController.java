package com.activiti.demo.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface IEmployeeController {
    /** 根据姓名查找员工 */
    String findEmployeeByName(Map paramMap, HttpServletRequest request);
    
    String deployHome(HttpServletRequest request);
    /** 查看流程图  */
    String viewImage(Map paramMap,HttpServletResponse response);
    /** 删除部署信息  */
    String delDeployment(Map paramMap);
    /** 发布流程  */
    String newdeploy(HttpServletRequest request, String filename, MultipartFile file);
    /** 添加请假申请 */
    String input(Map paramMap, HttpServletRequest request);
    /** 保存/更新，请假申请 */
    String save(Map paramMap, HttpServletRequest request);
    /** 请假管理首页显示 */
    String home(HttpServletRequest request);
    /** 删除请假申请 */
    String delLeaveBill(Map paramMap);
}
