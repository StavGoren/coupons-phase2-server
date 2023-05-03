package com.stav.server.controllers;

import com.stav.server.beans.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @PostMapping
    public void crateCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer, int id) {
        System.out.println(customer);
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int id) {
        return null;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return null;
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int id) {

    }
}
