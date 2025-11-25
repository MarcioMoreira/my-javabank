package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/customers")
public class CustomerListController {

    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerListController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method= RequestMethod.GET, path = "/list")
    public String getAllCustomersList(Model model) {

        List<Customer> customerList = customerService.getAllCustomers();

        model.addAttribute("customers", customerList);

        return "customersList";
    }

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }
}


