package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface CustomerService
{
    List<Customer> findAllCustomersAndOrders();
    Customer findCustomerByName(String name);
    void delete(long id);
    Customer save(Customer customer);
    Customer update(Customer customer, long id);

}
