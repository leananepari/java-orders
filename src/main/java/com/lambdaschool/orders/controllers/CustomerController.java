package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    // http://localhost:2018/customer/name/Stuart
    @GetMapping(value = "/customer/name/{name}",
                produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String name)
    {
        Customer c = customerService.findCustomerByName(name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2018/data/customer/new
    @PostMapping(value = "data/customer/new",
                 produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody
                                            Customer newCustomer)
        throws URISyntaxException
    {
        newCustomer = customerService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
