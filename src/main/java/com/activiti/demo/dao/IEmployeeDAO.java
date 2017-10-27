package com.activiti.demo.dao;

import com.activiti.demo.entity.Employee;

public interface IEmployeeDAO {
	//根据姓名查找员工
	Employee findEmployeeByName(String name);
}
