package com.activiti.demo.service;

import com.activiti.demo.entity.Employee;

public interface IEmployeeService {

	//根据姓名查找员工
	Employee findEmployeeByName(String name);
}
