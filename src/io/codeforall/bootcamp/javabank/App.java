package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.userInterface.Menu;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        AccountManager robber = new AccountManager();
        Bank bankRupted = new Bank(robber);
        Set<Customer> customers = new HashSet<>();

        Customer noa = new Customer();
        noa.setName("Noa");
        noa.setId(1);
        customers.add(noa);
        bankRupted.addCustomer(noa);
        int accountId = noa.openAccount(AccountType.CHECKING); // for default


        Prompt prompt = new Prompt(System.in, System.out);
        IntegerRangeInputScanner scanner = new IntegerRangeInputScanner(1, Integer.MAX_VALUE); // range
        int customerID = prompt.getUserInput(scanner);

        Customer currentCustomer = null;
        for (Customer c : customers) {
            if (c.getId() == customerID) {
                currentCustomer = c;
                break;
            }
        }

        if (currentCustomer == null) {
            System.out.println("Customer not found!");
        }

        System.out.println("\nPlease insert your customer name");
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.nextLine();

        for (Customer c : customers) {
            if (c.getName().equals(name)) {
                currentCustomer = c;
                break;
            }
        }
        if (currentCustomer == null) {
            System.out.println("Customer not found!");
            return;
        }


        while (true) {
            Menu menu = new Menu();
            IntegerRangeInputScanner menuOp = new IntegerRangeInputScanner(1, 5); // options range
            int menuOptions = prompt.getUserInput(menuOp);

            Scanner scanner3 = new Scanner(System.in);
            double amount;

            switch (menuOptions) {
                case 1:
                    // View Balance
                    System.out.println("\n---------------------------------------\n");
                    System.out.println("OPTION 1 - View Balance");
                    System.out.println("Balance -> " + currentCustomer.getBalance(accountId));

                    noa.showAccounts();

                    break;
                case 2:
                    // Make Deposit
                    System.out.println("\n---------------------------------------\n");
                    System.out.println("OPTION 2 - Make Deposit");
                    System.out.println("Whats the amount?");
                    amount = scanner3.nextDouble();
                    robber.deposit(accountId, amount);
                    System.out.println("Deposit succeded -> " + currentCustomer.getBalance(accountId));

                    break;
                case 3:
                    // Make Withdrawal
                    System.out.println("\n---------------------------------------\n");
                    System.out.println("OPTION 3 - Make Withdrawal");
                    System.out.println("Whats the amount?");
                    amount = scanner3.nextDouble();
                    if(amount > noa.getBalance(accountId) ){
                        System.out.println("Your not allowed. Not enought money!");
                    }
                    else {
                        robber.withdraw(accountId, amount);
                        System.out.println("Withdrawal succeded. New balance -> " + + currentCustomer.getBalance(accountId));
                    }

                    break;
                case 4:
                    // Open Account
                    System.out.println("\n---------------------------------------\n");
                    System.out.println("OPTION 4 - Open Account");
                    System.out.println("Choose type of account:");
                    System.out.println("\n1 - CHECKING");
                    System.out.println("2 - SAVINGS\n");

                    IntegerRangeInputScanner accountType = new IntegerRangeInputScanner(1, 2);// options range
                    int accountTypeChoice = prompt.getUserInput(accountType);

                        System.out.println("Whats the amount? Minimum 100");
                        AccountType type = (accountTypeChoice == 1) ? AccountType.CHECKING : AccountType.SAVINGS;
                        int newAccountId = noa.openAccount(type);
                        amount = scanner3.nextDouble();
                        robber.deposit(newAccountId, amount);
                        System.out.println("Succeded. New balance -> " + + currentCustomer.getBalance(accountId));

                    break;
                case 5:
                    // Quit
                    System.out.println("\n---------------------------------------\n");
                    System.out.println("OPTION 5 - LEAVING APP");
                    return;
            }

        }
    }


}
