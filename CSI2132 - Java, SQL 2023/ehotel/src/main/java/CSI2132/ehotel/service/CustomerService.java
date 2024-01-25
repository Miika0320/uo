package CSI2132.ehotel.service;

import java.util.List;
import CSI2132.ehotel.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(Long id);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomerById(Long id);
}
