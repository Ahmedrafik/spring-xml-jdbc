package employee.service;

import java.util.List;

import employee.model.Employee;
import employee.repository.EmployeeJdbcRepository;

public class EmployeeJdbcService implements EmployeeService{

	private EmployeeJdbcRepository employeeJdbcRepository;
	
	public EmployeeJdbcRepository getEmployeeJdbcRepository() {
		return employeeJdbcRepository;
	}

	public void setEmployeeJdbcRepository(EmployeeJdbcRepository employeeJdbcRepository) {
		this.employeeJdbcRepository = employeeJdbcRepository;
	}

	public void droptable(){
		employeeJdbcRepository.dropTable();
	}
	
	public void createTable(){
		employeeJdbcRepository.createTable();
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeJdbcRepository.saveEmployee(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeJdbcRepository.findAllEmployees();
	}

	@Override
	public Employee findBySsn(String ssn) {
		return employeeJdbcRepository.findBySsn(ssn);
	}

	@Override
	public Employee findById(Long id) {
		return employeeJdbcRepository.findById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeJdbcRepository.updateEmployee(employee);
	}

	@Override
	public Employee findLastHired() {
		return employeeJdbcRepository.findLastHired();	
	}

}
