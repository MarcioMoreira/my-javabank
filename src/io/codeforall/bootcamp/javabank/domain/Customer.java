package io.codeforall.bootcamp.javabank.domain;

import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.domain.account.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * The customer domain entity
 */
public class Customer {

    private AccountManager accountManager;
    private Map<Integer, Account> accounts = new HashMap<>();
    private int id = 0;
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets me the id of the customer
     * @return customer id number
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the account manager
     *
     * @param accountManager the account manager to set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Opens a new account
     *
     * @param accountType the account type to be opened
     * @return the new account id
     * @see AccountManager#openAccount(AccountType)
     */
    public int openAccount(AccountType accountType) {
        Account account = accountManager.openAccount(accountType);
        accounts.put(account.getId(), account);
        return account.getId();
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
     * @return the customer balance
     */
    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public  void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println(name + " has no accounts yet.");
            return;
        }

        System.out.println("\n--- Accounts for " + name + " ---");
        for (Account account : accounts.values()) {
            System.out.println("Account ID: " + account.getId() +
                    " | Type: " + account.getAccountType() +
                    " | Balance: " + account.getBalance());
        }
    }

}
