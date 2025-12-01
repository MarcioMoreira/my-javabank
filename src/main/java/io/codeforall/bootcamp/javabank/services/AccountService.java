package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.exceptions.AccountNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.CustomerNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.RecipientNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.TransactionInvalidException;
import io.codeforall.bootcamp.javabank.model.account.Account;

/**
 * Common interface for account services, provides methods to manage accounts
 */
public interface AccountService {

    /**
     * Add given account to AccountMap
     * @param account the account to add
     * @throws TransactionInvalidException
     */
    Account add(Account account) throws TransactionInvalidException;

    /**
     * Deposit amount in account with the given id
     * @param id the account id
     * @param amount the amount to deposit
     */
    void deposit(int id, double amount) throws AccountNotFoundException, TransactionInvalidException;

    /**
     * Withdraw amount from account with the given id
     * @param id the account id
     * @param amount the amount to withdraw
     */
    void withdraw(int id, double amount) throws AccountNotFoundException, TransactionInvalidException;

    /**
     * Transfer amount between two accounts
     * @param srcId the source account id
     * @param dstId the destiny account id
     * @param amount the amount to transfer
     */
    void transfer(int srcId, int dstId, double amount, int customerId) throws AccountNotFoundException, CustomerNotFoundException, TransactionInvalidException, RecipientNotFoundException;


    /**
     * Get the account with the given id
     * @param id the account id
     * @return the account
     * @throws AccountNotFoundException when the account doesn't exist
     */
    Account get(int id) throws AccountNotFoundException;
}
