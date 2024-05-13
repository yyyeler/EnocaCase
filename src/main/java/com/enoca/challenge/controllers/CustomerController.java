package com.enoca.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.challenge.error.ErrorMessage;
import com.enoca.challenge.models.customer.Customer;
import com.enoca.challenge.services.CustomerService;


@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ErrorMessage errorMessage;

    @PostMapping("/api/v1/AddCustomer")
    public ErrorMessage createCustomer(@RequestBody Customer customer)
    {
        customerService.save(customer);
        errorMessage.setAll("200", "Customer is created");
    
        return errorMessage;
    }

    @GetMapping("/api/v1/Customers")
    public List<Customer> getCustomers()
    {
        return customerService.getCustomers();
    }
}
