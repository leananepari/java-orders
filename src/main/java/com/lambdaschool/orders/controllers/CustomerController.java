package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    // http://localhost:2018/customers/orders
    @GetMapping(value = "/customers/orders",
                produces = {"application/json"})
    public ResponseEntity<?> listCustomersWithTheirOrders()
    {
        List<Customer> customers = customerService.findAllCustomersAndOrders();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
