package com.activiti.demo.dao;

import com.activiti.demo.entity.Employee;
import com.activiti.demo.entity.LeaveBill;


public interface IEmployeeDAO {
	//根据姓名查找员工
	Employee findEmployeeByName(String name);
	//查询自己的请假单的信息
	int updateLeaveBill(LeaveBill leaveBill);
	
	
}
