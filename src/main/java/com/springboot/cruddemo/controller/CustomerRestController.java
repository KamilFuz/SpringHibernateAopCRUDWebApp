package com.springboot.cruddemo.controller;

import com.springboot.cruddemo.dao.CustomerDAO;
import com.springboot.cruddemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/api")
public class CustomerRestController {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerRestController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @RequestMapping("customer")
    public String customer() {
        return "list";
    }

}
