package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.account.Account;

import java.sql.*;

public class JDBCAccountService implements AccountService {

    private Connection dbConnection;


    @Override
    public void add(Account account) {
        if (account == null) {
            throw new RuntimeException("Error account is null");
        }
        try {
            String query = "INSERT INTO account (id, account_number, balance, type, customer_id, created_at) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, account.getId());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType().toString());

        } catch (SQLException e) {
            throw new RuntimeException("Error adding account", e);
        }
    }

    @Override
    public void deposit(int id, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit must be positive");
        }
        String query = "UPDATE account SET balance = balance + ? WHERE id = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Account not found for deposit: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error depositing into account " + id, e);
        }

    }

    @Override
    public void withdraw(int id, double amount) {

        if (amount <= 0) {
            throw new RuntimeException("Deposit must be positive");
        }

        String query = "UPDATE account SET balance = balance + ? WHERE id = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Account not found for deposit: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error depositing into account " + id, e);
        }

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

    }


}
