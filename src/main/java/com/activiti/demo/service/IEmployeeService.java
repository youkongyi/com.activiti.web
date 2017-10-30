package com.activiti.demo.service;

import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import com.activiti.demo.entity.Employee;

public interface IEmployeeService {

	//根据姓名查找员工
	Employee findEmployeeByName(String name);
	
	List<Deployment> findDeploymentList();
	
	List<ProcessDefinition> findProcessDefinitionList();
}
