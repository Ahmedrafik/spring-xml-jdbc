package employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import employee.model.Employee;

public class EmployeeJdbcRepository extends AbstractJdbcRepository implements EmployeeRepository {

	public void dropTable(){
		this.getJdbcTemplate().update("drop table if exists employee");
	}
	
	public void createTable(){
		this.getJdbcTemplate().update("CREATE TABLE employee ( id bigserial NOT NULL, "
				+ "firstname character varying(255),"
				+ " hiredate timestamp without time zone,"
				+ " lastname character varying(255),"
				+ " salary numeric(19,2),"
				+ " ssn character varying(255),"
				+ " CONSTRAINT employee_pkey PRIMARY KEY (id))");
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		Object[] params = new Object[] {employee.getId(), employee.getFirstName(), employee.getEngageDate(), employee.getLastName(), employee.getSalary(), employee.getSSNumber()};
		
		int[] types = new int[] {Types.LONGVARBINARY, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR};
		
		this.getJdbcTemplate().update("INSERT INTO employee (id, firstname, hiredate, lastname, salary, ssn) VALUES (?, ?, ?, ?, ?, ?)", params, types);
	}

	@Override
	public List<Employee> findAllEmployees() {
		List<Employee> listEmployee = this.getJdbcTemplate().query("select * from employee", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
				Employee empl = new Employee(rs.getLong("id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("ssn"), rs.getDouble("salary"), 
						rs.getDate("hiredate").toLocalDate());
				return empl;
			}
		});
		
		return listEmployee;
	}

	@Override
	public Employee findBySsn(String ssn) {
		Employee employee = this.getJdbcTemplate().queryForObject("select * from employee where ssn = ?", new Object[]{ssn}, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
				Employee empl = new Employee(rs.getLong("id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("ssn"), rs.getDouble("salary"), 
						rs.getDate("hiredate").toLocalDate());
				return empl;
			}
		});
		
		return employee;
	}

	@Override
	public Employee findById(Long id) {
		Employee employee = this.getJdbcTemplate().queryForObject("select * from employee where id = ?", new Object[]{id}, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
				Employee empl = new Employee(rs.getLong("id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("ssn"), rs.getDouble("salary"), 
						rs.getDate("hiredate").toLocalDate());
				return empl;
			}
		});
		
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		this.getJdbcTemplate().update("update employee set firstname = ?, hiredate = ?, lastname = ?, salary = ?, ssn = ? where id = ?", 
				employee.getFirstName(), employee.getEngageDate(), employee.getLastName(), employee.getSalary(), employee.getSSNumber(), employee.getId());
	}

	@Override
	public Employee findLastHired() {
		Employee employee = this.getJdbcTemplate().query("select * from employee order by hiredate limit 1", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
				Employee empl = new Employee(rs.getLong("id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("ssn"), rs.getDouble("salary"), 
						rs.getDate("hiredate").toLocalDate());
				return empl;
			}
		}).get(0);
		return employee;
	}
}
