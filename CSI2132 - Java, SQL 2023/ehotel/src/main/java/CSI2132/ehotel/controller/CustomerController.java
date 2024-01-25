package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import CSI2132.ehotel.entity.Customer;
import CSI2132.ehotel.service.CustomerService;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import CSI2132.ehotel.entity.Customer;
import CSI2132.ehotel.repository.CustomerRepository;

@Controller
public class CustomerController {
    
    private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@RequestMapping("/customers")
      public String customers(){
          return "customers";
      }

	

    @GetMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customer", customerService.getAllCustomers());
		return "customers";
	}
/* 
    @RequestMapping("/saveData")
    @ResponseBody
    public String saveData(Customer customer){
        repo.save(customer);
        return "Success";
    }
	*/

	@GetMapping("/createCustomer") // add
	public String createCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "createCustomer";
	}

	@PostMapping("/customers") // save to repo?
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}

	@GetMapping("/editCustomer/{customer_id}")
	public String editCustomerForm(@PathVariable Long customer_id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(customer_id));
		return "editCustomer";
	}

	@PostMapping("/customers/{customer_id}")
	public String updateCustomer(@PathVariable Long customer_id, @ModelAttribute("customers") Customer newcustomer, Model model) {
		Customer existingCustomer = customerService.getCustomerById(customer_id);
		existingCustomer.setCustomerId(customer_id);
		existingCustomer.setName(newcustomer.getName());
		existingCustomer.setAddress(newcustomer.getAddress());
		existingCustomer.setSin(newcustomer.getSin());
		existingCustomer.setRegistration_date(newcustomer.getRegistration_date());
		existingCustomer.setPassword(newcustomer.getPassword());
		customerService.updateCustomer(existingCustomer);
		return "redirect:/customers";
	}

	@GetMapping("/customers/{customer_id}")
	public String deleteCustomerString(@PathVariable Long customer_id) {
		customerService.deleteCustomerById(customer_id);
		return "redirect:/customers";
	}
}