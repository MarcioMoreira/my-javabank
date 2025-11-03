package io.codeforall.bootcamp.javabank.controller;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.Account;
import java.util.HashMap;
import java.util.Map;



public class BalanceController {

    private Customer customer;
    private Map<Integer, Account> accounts = new HashMap<>();

    public BalanceController(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the balance of an {@link Account}
     *
     * @param id the id of the account
     * @return the account balance
     */
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    /**
     * Gets the total customer balance
     *
     * @return the balance
     */
    public double getBalance() {

        double balance = 0;
        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }
}
