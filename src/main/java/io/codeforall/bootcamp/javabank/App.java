package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.services.*;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();

        JDBCAccountService jdbcAccountService = new JDBCAccountService();
        JDBCCustomerService jdbcCustomerService = new JDBCCustomerService();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(jdbcAccountService);
        bootstrap.setCustomerService(jdbcCustomerService);
        bootstrap.loadCustomers();

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
