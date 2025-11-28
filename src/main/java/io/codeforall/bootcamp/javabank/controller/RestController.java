package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.command.CustomerDto;
import io.codeforall.bootcamp.javabank.converters.AccountToAccountDto;
import io.codeforall.bootcamp.javabank.converters.CustomerDtoToCustomer;
import io.codeforall.bootcamp.javabank.converters.CustomerToCustomerDto;
import io.codeforall.bootcamp.javabank.converters.RecipientToRecipientDto;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private CustomerService customerService;
    private CustomerToCustomerDto customerToCustomerDto;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private RecipientToRecipientDto recipientToRecipientDto;
    private AccountToAccountDto accountToAccountDto;

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerToCustomerDto getCustomerToCustomerDto() {
        return customerToCustomerDto;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

    public CustomerDtoToCustomer getCustomerDtoToCustomer() {
        return customerDtoToCustomer;
    }
    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    public RecipientToRecipientDto getRecipientToRecipientDto() {
        return recipientToRecipientDto;
    }

    @Autowired
    public void setRecipientToRecipientDto(RecipientToRecipientDto recipientToRecipientDto) {
        this.recipientToRecipientDto = recipientToRecipientDto;
    }

    public AccountToAccountDto getAccountToAccountDto() {
        return accountToAccountDto;
    }

    @Autowired
    public void setAccountToAccountDto(AccountToAccountDto accountToAccountDto) {
        this.accountToAccountDto = accountToAccountDto;
    }


    @RequestMapping(method = RequestMethod.GET, value = "api/customer/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomers() {
        return customerToCustomerDto.convert(customerService.list());

    }

    @RequestMapping(method = RequestMethod.GET, value = "api/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable("id")Integer id) {
        return customerService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/customer/{id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAccounts(@PathVariable("id")Integer id) {
        return customerService.get(id).getAccounts();
    }



    @RequestMapping(method = RequestMethod.POST, value = "api/customer/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> addUser(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        customerService.save(customerDtoToCustomer.convert(customerDto));
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }


}















