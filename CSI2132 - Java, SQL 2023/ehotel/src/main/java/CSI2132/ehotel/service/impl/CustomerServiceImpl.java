package CSI2132.ehotel.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import CSI2132.ehotel.service.CustomerService;
import CSI2132.ehotel.repository.CustomerRepository;
import CSI2132.ehotel.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
    
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);	
	}
}
