package io.codeforall.bootcamp.javabank.views;

import io.codeforall.bootcamp.javabank.controller.BalanceController;
import io.codeforall.bootcamp.javabank.domain.Customer;

public class BalanceView {

    private Customer customer;
    private BalanceController bc;

    public BalanceView(Customer customer, BalanceController bc) {
        this.customer = customer;
        this.bc = bc;
    }

    public void showAccountBalance(int id){
        if(customer.getAccountIds().contains(id)){
            System.out.println("BALANCE -> " + bc.getBalance(id));
        }
        System.out.println("THERE´S NO REGISTER OF BALANCE");
    }

    public void showTotalBalance(){
        if(!customer.getAccounts().isEmpty()){
            System.out.println("BALANCE -> " + bc.getBalance());
        }
        System.out.println("THERE´S NO REGISTER OF BALANCE");
    }



}
