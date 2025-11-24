package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.view.BalanceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The {@link BalanceView} controller
 */
@Controller
@RequestMapping
public class BalanceController extends AbstractController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Gets the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return authService.getAccessingCustomer();
    }

    /**
     * Gets the customer balance
     *
     * @return the customer balance
     */
    public double getCustomerBalance() {
        return customerService.getBalance(authService.getAccessingCustomer().getId());
    }
}
