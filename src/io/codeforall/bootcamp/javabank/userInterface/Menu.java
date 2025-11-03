package io.codeforall.bootcamp.javabank.userInterface;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Menu {

    private final String[] options = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};

    public Menu() {
        welcome();
    }

    private String welcome() {
        System.out.println("\n\n*** WELCOME TO JAVABANK ***\n");

        StringBuilder sb = new StringBuilder();
        sb.append("\n1 - ").
                append(options[0]).
                append("\n2 - ").
                append(options[1]).
                append("\n3 - ").
                append(options[2]).
                append("\n4 - ").
                append(options[3]).
                append("\n5 - ").
                append(options[4]);
        System.out.println(sb +"\n");
        return sb.toString();
    }

}
