package com.activiti.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.demo.dao.IEmployeeDAO;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private IEmployeeDAO employeeDAO;
    
    @Autowired
    private RepositoryService repositoryService;

    public Employee findEmployeeByName(String name) {
        return employeeDAO.findEmployeeByName(name);
    }

    /**查询部署对象信息，对应表（act_re_deployment）*/
    @Override
    public List<Deployment> findDeploymentList() {
        List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
                            .orderByDeploymenTime().asc()//
                            .list();
        return list;
    }
    
    /**查询流程定义的信息，对应表（act_re_procdef）*/
    @Override
    public List<ProcessDefinition> findProcessDefinitionList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
                            .orderByProcessDefinitionVersion().asc()//
                            .list();
        return list;
    }
    
    /** 使用部署对象ID和资源图片名称，获取图片的输入流 */
    @Override
    public InputStream findImageInputStream(String deploymentId,
            String imageName) {
        return repositoryService.getResourceAsStream(deploymentId, imageName);
    }
    
    /** 使用部署对象ID，删除流程定义 */
    @Override
    public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
    }
    
    /** 部署流程定义 */
    @Override
    public void saveNewDeploye(File file, String filename) {
        try {
            //2：将File类型的文件转化成ZipInputStream流
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            repositoryService.createDeployment()//创建部署对象
                            .name(filename)//添加部署名称
                            .addZipInputStream(zipInputStream)//
                            .deploy();//完成部署
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
