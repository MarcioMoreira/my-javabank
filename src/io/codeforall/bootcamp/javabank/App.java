package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.Account;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.userInterface.Menu;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;

import java.util.HashSet;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        AccountManager robber = new AccountManager();
        Bank bankRupted = new Bank(robber);
        Set<Customer> customers = new HashSet<>();
        Set<Account> accounts = new HashSet<>();
        Customer customer1 = new Customer();

        Prompt prompt = new Prompt(System.in, System.out);
        IntegerRangeInputScanner scanner = new IntegerRangeInputScanner(1, Integer.MAX_VALUE); // range


        System.out.println("\nPlease insert your customer id");
        int userInput1 = prompt.getUserInput(scanner);

        Menu menu = new Menu();
        // Grab the user in a loop until a valid input is inserted
        IntegerRangeInputScanner scanner2 = new IntegerRangeInputScanner(1, 5); // options range
        int userInput2 = prompt.getUserInput(scanner2);


        switch (userInput2) {
            case 1:
                // View Balance
                System.out.println("\n---------------------------------------\n");
                System.out.println("OPTION 1 - View Balance");


                break;
            case 2:
                // Make Deposit
                System.out.println("\n---------------------------------------\n");
                System.out.println("OPTION 2 - Make Deposit");


                break;
            case 3:
                // Make Withdrawal
                System.out.println("\n---------------------------------------\n");
                System.out.println("OPTION 3 - Make Withdrawal");


                break;
            case 4:
                // Open Account
                System.out.println("\n---------------------------------------\n");
                System.out.println("OPTION 4 - Open Account");
                System.out.println("Choose type of account:");
                System.out.println("\n1 - CHECKING");
                System.out.println("2 - SAVINGS\n");

                int accountChoice = prompt.getUserInput(scanner2);
                switch (accountChoice) {
                    case 1:
                        System.out.println("CHECKING");
                        robber.openAccount(AccountType.CHECKING);
                        //accounts.add()

                        break;
                    case 2:
                        System.out.println("SAVINGS");
                        robber.openAccount(AccountType.CHECKING);

                        break;
                }
                break;
            case 5:
                // Quit
                System.out.println("\n---------------------------------------\n");
                System.out.println("OPTION 5 - LEAVING APP");
                return;
        }
    }
}
