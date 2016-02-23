package com.iocean.employee_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import company.service.CompanyService;
import employee.factory.EmployeeFactory;
import employee.model.Employee;
import employee.repository.EmployeeJdbcRepository;
import employee.service.EmployeeJdbcService;
import employee.service.EmployeeService;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeJdbcTest{
	
	AbstractApplicationContext context;
	EmployeeService emplService;
	CompanyService companyService;
	EmployeeJdbcService employeeJdbcService;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		emplService = (EmployeeService)context.getBean("employeeService");
		companyService = (CompanyService)context.getBean("companyService");
		employeeJdbcService = (EmployeeJdbcService)context.getBean("employeeJdbcService");
		//employeeJdbcService.droptable();
		//employeeJdbcService.createTable();
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
		Employee simba = new Employee(1l, "Simba", "TheLionKing", "122345678", 1234.56, LocalDate.of(2015, 11, 26));
		Employee mouphasa = new Employee(2l, "Mouphasa", "TheLionKing", "1245678", 1234.56, LocalDate.of(2016, 01, 26));
		Employee timon = new Employee(3l, "Timon", "TheLionKing", "1223456", 1234.56, LocalDate.of(2006, 01, 02));
		Employee pumba = new Employee(4l, "Pumba", "TheLionKing", "1223478", 1234.56, LocalDate.of(2014, 11, 12));
		Employee skar = new Employee(5l, "Skar", "TheLionKing", "122344278", 1234.56, LocalDate.of(2015, 06, 26));
		Employee rafiki = new Employee(6l, "Rafiki", "TheLionKing", "122655678", 1234.56, LocalDate.of(1984, 11, 26));

		employeeJdbcService.saveEmployee(nala);
		employeeJdbcService.saveEmployee(simba);
		employeeJdbcService.saveEmployee(mouphasa);
		employeeJdbcService.saveEmployee(timon);
		employeeJdbcService.saveEmployee(pumba);
		employeeJdbcService.saveEmployee(skar);
		employeeJdbcService.saveEmployee(rafiki);
		
	}
	
	@Test
	public void testFindAllEmployees(){	
		assertEquals(7,employeeJdbcService.findAllEmployees().size());
	}
	
	@Test
	public void testFindBySsn(){
		assertEquals("Simba", employeeJdbcService.findBySsn("122345678").getFirstName());
	}
	
	@Test
	public void testFindById(){
		assertEquals("Simba", employeeJdbcService.findById(1l).getFirstName());
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
	/*
	@Test
	public void testMockFindAll(){
		assertEquals(null, emplService.findAllEmployees());
	}
	*/
	/* Test Company Service */
	/*
	@Test
	public void testfindAllEmployeesOfaCompany(){
		assertEquals(6, companyService.findAllEmployees().size());
	}
	*/
	@After
	public void close(){
		context.close();
	}
}
