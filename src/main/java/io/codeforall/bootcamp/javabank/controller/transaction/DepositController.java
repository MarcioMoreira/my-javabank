package io.codeforall.bootcamp.javabank.controller.transaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller used for deposit transactions
 * @see AbstractAccountTransactionController
 */
@Controller
@RequestMapping
public class DepositController extends AbstractAccountTransactionController {

    /**
     * Deposits an amount on the account with the given id
     *
     * @see AbstractAccountTransactionController#submitTransaction(int, double)
     */
    @Override
    public void submitTransaction(int accountId, double amount) {
        accountService.deposit(accountId, amount);
    }
}
