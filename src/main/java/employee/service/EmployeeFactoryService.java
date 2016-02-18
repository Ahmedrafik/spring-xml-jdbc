package employee.service;

import java.util.List;

import employee.model.Employee;
import employee.repository.EmployeeRepository;

public class EmployeeFactoryService implements EmployeeService {

	private EmployeeRepository employeRepository;
	
	public EmployeeFactoryService(EmployeeRepository employeRepository) {
		super();
		this.employeRepository = employeRepository;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeRepository.saveEmployee(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeRepository.findAllEmployees();
	}

	@Override
	public Employee findBySsn(String ssn) {
		return employeRepository.findBySsn(ssn);
	}
	
	public Employee findById(Long id){
		return employeRepository.findById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeRepository.updateEmployee(employee);
	}

	@Override
	public Employee findLastHired() {
		return employeRepository.findLastHired();
	}

}
