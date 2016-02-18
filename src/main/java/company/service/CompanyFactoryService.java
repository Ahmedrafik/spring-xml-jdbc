package company.service;

import java.util.List;

import employee.model.Employee;
import employee.service.EmployeeService;

public class CompanyFactoryService implements CompanyService{

	private EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public List<Employee> findAllEmployees(){
		return employeeService.findAllEmployees();
	}
}
