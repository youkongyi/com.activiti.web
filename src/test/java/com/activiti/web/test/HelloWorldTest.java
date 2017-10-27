package com.activiti.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.activiti.demo.controller.IEmployeeController;
import com.activiti.demo.dao.IEmployeeDAO;
import com.activiti.demo.entity.Employee;
import com.activiti.demo.mapper.EmployeeMapper;
import com.activiti.demo.service.IEmployeeService;
public class HelloWorldTest {
	@Test
	public void ActivitiDemo(){
		ProcessEngineConfiguration process = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//		process.setJdbcDriver("com.mysql.jdbc.Driver");
//		process.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?useUnicode=true&characterEncoding=UTF-8");
		process.setJdbcDriver("oracle.jdbc.OracleDriver");
		process.setJdbcUrl("jdbc:oracle:thin:@112.74.45.38:1521:orcl");
		process.setJdbcUsername("gehanbiao");
		process.setJdbcPassword("123");
		process.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine pro = process.buildProcessEngine();
		System.out.println(pro);
	}
	
	@Test
	public void activitiDemo2(){
		//初始化流程引擎
		ProcessEngine pro = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		System.out.println(pro);
	}
	public ApplicationContext ac;
	
	 
	 
	 @Test
	    public void test1(){
	        DataSource ds = ac.getBean("dataSource",DataSource.class);
	        System.out.println(ds);

	        SqlSessionFactory sql = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
	        System.out.println(sql);

	        
	       
	    }
	@Test
	public void test2(){
		 // 初始化流程引擎
        ProcessEngineConfiguration pro = ac.getBean("processEngineConfiguration",ProcessEngineConfiguration.class);
		System.out.println(pro.getDataSource());
		ProcessEngine po = ac.getBean("processEngine",ProcessEngine.class);
		System.out.println(po.getRepositoryService()
		.createDeploymentQuery()
		.deploymentName("排他网关")
		.singleResult().getId());
	}
	
	
	@Test
    public void test4(){
		IEmployeeDAO dao = ac.getBean("IEmployeeDAO",IEmployeeDAO.class);
		Employee userbo = dao.findEmployeeByName("范冰冰");
        System.out.println(userbo.getName());
    }
	
	@Test
    public void test5(){
		IEmployeeService dao = ac.getBean("employeeService",IEmployeeService.class);
		Employee userbo = dao.findEmployeeByName("范冰冰");
        System.out.println(userbo.getName());
    }
	
	 @Before
	    public void init(){
			ac = new ClassPathXmlApplicationContext("classpath:config/spring-dao.xml");
	    }
	
	@Test
    public void test6(){
		SqlSession  sqlSession = (SqlSession) ac.getBean("sqlSession");
		EmployeeMapper countryMapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee e = new Employee();
		e.setName("范冰冰");
		List<Employee> countries =  countryMapper.select(e);
		 System.out.println(countries.get(0).getRole());
	}
	
	@Test
    public void test7(){
		SqlSession  sqlSession = (SqlSession) ac.getBean("sqlSession");
		EmployeeMapper countryMapper = sqlSession.getMapper(EmployeeMapper.class);
		 List<Employee> countries = countryMapper.selectAll();
		 for(Employee e : countries){
			 System.out.println(e.getId() + " " + e.getName() + " " + e.getPassword() + " " + e.getRole() + " " +e.getEmail() + " " + e.getManagerId());
		 }
	}
	
	@Test
    public void test8(){
		SqlSession  sqlSession = (SqlSession) ac.getBean("sqlSession");
		EmployeeMapper countryMapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee e = new Employee();
		e.setId("6");
		e.setName("阮玲玉");
		e.setEmail("ruanlingyu@163.com");
		e.setRole("user");
		e.setManagerId("1");
		countryMapper.insertSelective(e);
	}
	
	@Test
    public void test9(){
		SqlSession sqlSession = (SqlSession) ac.getBean("sqlSession");
		EmployeeMapper countryMapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee e = new Employee();
		e.setName("阮玲玉");
//		e.setPassword("123");
		e.setEmail("ruanlingyu@163.com");
		e.setRole("user");
		e.setManagerId("1");
		e.setId("6");
		int a = countryMapper.updateByPrimaryKeySelective(e);
		System.out.println(a);
	}
	
	@Test
    public void test10(){
		TaskService taskService = (TaskService) ac.getBean("taskService");
		taskService.complete("9904");
		
	}
	
	@Test
    public void test11(){
	    IEmployeeController controller = ac.getBean("employeeControllerImpl",IEmployeeController.class);
	    Map<Object ,Object> term=new HashMap<Object,Object>();
	    term.put("name", new String []{"范冰冰"});
    }
	
}
