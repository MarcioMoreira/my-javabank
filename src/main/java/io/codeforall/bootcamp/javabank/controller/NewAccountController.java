package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.model.account.AccountType;
import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.view.NewAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The {@link NewAccountView} controller
 */
@org.springframework.stereotype.Controller
@RequestMapping("/account")
public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountFactory accountFactory;
    private AccountService accountService;

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Sets the account factory
     *
     * @param accountFactory the account factory to set
     */
    @Autowired
    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountFactory#createAccount(AccountType)
     */
    @Override
    public void init() {
        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        authService.getAccessingCustomer().addAccount(newAccount);
        return accountService.add(newAccount);
    }
}
