package employee.repository;

import java.time.LocalDate;
import java.util.List;

import employee.factory.EmployeeFactory;
import employee.model.Employee;

public class EmployeeFactoryRepository implements EmployeeRepository{

	private EmployeeFactory employeeFactory;
	
	public EmployeeFactory getEmployeeFactory() {
		return employeeFactory;
	}

	public void setEmployeeFactory(EmployeeFactory employeeFactory) {
		this.employeeFactory = employeeFactory;
	}



	@Override
	public void saveEmployee(Employee employee) {
		employeeFactory.addEmployee(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeFactory.getListEmployee();
	}

	@Override
	public Employee findBySsn(String ssn) {
		for(Employee empl : employeeFactory.getListEmployee()){
			if(empl.getSSNumber().equals(ssn)){
				return empl;
			}
		}
		return null;
	}
	
	public Employee findById(Long id){
		for(Employee empl : employeeFactory.getListEmployee()){
			if(empl.getId().equals(id)){
				return empl;
			}
		}
		return null;
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee emplToRemove = this.findById(employee.getId());
		employeeFactory.removeEmployee(emplToRemove);
		this.saveEmployee(employee);
	}

	@Override
	public Employee findLastHired() {
		LocalDate lastHiredDate = employeeFactory.getListEmployee().get(0).getEngageDate();
		Employee lastHired = employeeFactory.getListEmployee().get(0);
		for(Employee empl : employeeFactory.getListEmployee()){
			if(empl.getEngageDate().isAfter(lastHiredDate)){
				lastHired = empl;
			}
		}
		return lastHired;
	}

}
