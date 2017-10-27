package com.activiti.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.demo.dao.IEmployeeDAO;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private IEmployeeDAO employeeDAO;

    public Employee findEmployeeByName(String name) {
        return employeeDAO.findEmployeeByName(name);
    }

}
