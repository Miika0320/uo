package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CSI2132.ehotel.entity.Employee;
import CSI2132.ehotel.service.EmployeeService;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@RequestMapping("/employees")
      public String employees(){
          return "employees";
      }

    @GetMapping("/employees")
	public String listEmployees(Model model) {
		model.addAttribute("employee", employeeService.getAllEmployees());
		return "employees";
	}

	@GetMapping("/createEmployee") // add
	public String createEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "createEmployee";
	}

	@PostMapping("/employees") // save to repo?
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/editEmployee/{employee_id}")
	public String editEmployeeForm(@PathVariable Long employee_id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(employee_id));
		return "editEmployee";
	}

	@PostMapping("/employees/{employee_id}")
	public String updateEmployee(@PathVariable Long employee_id, @ModelAttribute("employee") Employee newemployee, Model model) {
		Employee existingEmployee = employeeService.getEmployeeById(employee_id);
		existingEmployee.setEmployee_id(employee_id);
		existingEmployee.setName(newemployee.getName());
		existingEmployee.setAddress(newemployee.getAddress());
		existingEmployee.setRole(newemployee.getRole());
		existingEmployee.setManager_id(newemployee.getManager_id());
		existingEmployee.setSin(newemployee.getSin());
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/{employee_id}")
	public String deleteEmployeeString(@PathVariable Long employee_id) {
		employeeService.deleteEmployeeById(employee_id);
		return "redirect:/employees";
	}
}
