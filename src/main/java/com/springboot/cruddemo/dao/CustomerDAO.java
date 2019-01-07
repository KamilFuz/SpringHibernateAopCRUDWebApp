package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> findAll();

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);
}
