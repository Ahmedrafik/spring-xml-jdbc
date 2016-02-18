package com.iocean.employee_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import company.service.CompanyService;
import employee.factory.EmployeeFactory;
import employee.model.Employee;
import employee.service.EmployeeService;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest{
	
	AbstractApplicationContext context;
	EmployeeService emplService;
	CompanyService companyService;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		emplService = (EmployeeService)context.getBean("employeeService");
		companyService = (CompanyService)context.getBean("companyService");
	}
	
	@Test
	public void testEmployeeInit() throws Exception{
		EmployeeFactory empl = (EmployeeFactory)context.getBean("employeeFactory");
		List<Employee> lionking = empl.getListEmployee();
		assertEquals(6, lionking.size());
	}
	
	@Test
	public void testSaveEmployee(){
		Employee nala = new Employee(18l, "Nala", "LionQueen", "1596321", 15236.01, LocalDate.of(2014, 10, 20));
		emplService.saveEmployee(nala);
		assertEquals(7, emplService.findAllEmployees().size());
	}
	
	@Test
	public void testFindAllEmployees(){
		assertEquals(6,emplService.findAllEmployees().size());
	}
	
	@Test
	public void testFindBySsn(){
		assertEquals("Simba", emplService.findBySsn("122345678").getFirstName());
	}
	
	@Test
	public void testUpdateEmployee(){
		Employee zazu = new Employee(2l, "Zazu", "ThelionKing", "13579", 102563.02, LocalDate.of(1569, 06, 21));
		emplService.updateEmployee(zazu);
		assertEquals("Zazu", emplService.findById(2l).getFirstName());
	}
	
	@Test
	public void testFindLastHired(){
		assertEquals("Mouphasa", emplService.findLastHired().getFirstName());
	}
	
	/* Test MockService */
	
	@Test
	public void testMockFindAll(){
		assertEquals(null, emplService.findAllEmployees());
	}
	
	/* Test Company Service */
	
	@Test
	public void testfindAllEmployeesOfaCompany(){
		assertEquals(6, companyService.findAllEmployees().size());
	}
	
	@After
	public void close(){
		context.close();
	}
}
