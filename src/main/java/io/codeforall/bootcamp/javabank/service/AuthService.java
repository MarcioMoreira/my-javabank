package io.codeforall.bootcamp.javabank.service;

import io.codeforall.bootcamp.javabank.model.Customer;

public interface AuthService {

    boolean authenticate(Integer id);
    Customer getAccessingCustomer();

}