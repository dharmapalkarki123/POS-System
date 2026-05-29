package com.pos.service.impl;

import com.pos.modal.Customer;
import com.pos.repository.CustomerRepository;
import com.pos.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {



        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer updateCustomer=customerRepository.findById(id).orElseThrow(
        ()-> new Exception("Customer not found"));

        updateCustomer.setFullName(customer.getFullName());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPhone(customer.getPhone());



        return customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {

        Customer deleteCustomer=customerRepository.findById(id).orElseThrow(
                ()-> new Exception("Customer not found"));
        customerRepository.delete(deleteCustomer);

    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(
                ()-> new Exception("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword
        );
    }
}
