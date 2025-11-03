package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.operations.BalanceOperation;
import io.codeforall.bootcamp.javabank.application.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.application.operations.Operation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.WithdrawOperation;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.util.HashMap;
import java.util.Map;

public class MenuController {

    private MenuInputScanner mainMenu;
    private int accessingCustomerId;
    private Map<Integer, Operation> operationsMap;

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;
    }
}
