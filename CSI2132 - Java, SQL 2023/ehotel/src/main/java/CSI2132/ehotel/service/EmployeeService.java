package CSI2132.ehotel.service;

import java.util.List;
import CSI2132.ehotel.entity.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(Long id);
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployeeById(Long id);
}
