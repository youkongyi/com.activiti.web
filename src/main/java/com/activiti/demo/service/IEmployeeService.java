package com.activiti.demo.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import com.activiti.demo.entity.Employee;

public interface IEmployeeService {

	/** 根据姓名查找员工 */
	Employee findEmployeeByName(String name);
	/** 查询部署对象信息 */
	List<Deployment> findDeploymentList();
	/** 查询流程定义的信息 */
	List<ProcessDefinition> findProcessDefinitionList();
	/** 使用部署对象ID和资源图片名称，获取图片的输入流 */
	InputStream findImageInputStream(String deploymentId, String imageName);
	/** 删除部署信息 */
	void deleteProcessDefinitionByDeploymentId(String deploymentId);
}
