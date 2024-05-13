package com.enoca.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.models.customer.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void save(Customer customer)
    {
        try 
        {
            customerRepository.saveAndFlush(customer);
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }

    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomer(long customerId) throws Exception
    {
        return customerRepository.findById(customerId).orElseThrow(() -> new Exception());
    }
}
