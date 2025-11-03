package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.operations.BalanceOperation;
import io.codeforall.bootcamp.javabank.application.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.application.operations.Operation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.WithdrawOperation;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private MenuInputScanner mainMenu;
    private int accessingCustomerId;
    private Prompt prompt;
    private Bank bank;
    private Map<Integer, Operation> operationsMap;

    /**
     * Starts the bank application
     */
    public void start() {

        mainMenu = buildMainMenu();

        accessingCustomerId = scanCustomerId();
       // operationsMap = buildOperationsMap();
        menuLoop();
    }

    private void menuLoop() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();
    }


    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

//    private Map<Integer, Operation> buildOperationsMap() {
//
//        Map<Integer, Operation> map = new HashMap<>();
//        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
//        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
//        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
//        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));
//
//        return map;
//    }
}
