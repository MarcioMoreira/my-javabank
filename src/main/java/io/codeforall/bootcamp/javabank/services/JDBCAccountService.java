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
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        String queryCheck = "SELECT balance FROM account WHERE id = ?";
        String queryUpdate = "UPDATE account SET balance = balance - ? WHERE id = ?";

        try (PreparedStatement psCheck = dbConnection.prepareStatement(queryCheck);
             PreparedStatement psUpdate = dbConnection.prepareStatement(queryUpdate)) {

            // Check current balance
            psCheck.setInt(1, id);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("Account not found for withdrawal: " + id);
            }

            double currentBalance = rs.getDouble("balance");
            if (currentBalance < amount) {
                throw new RuntimeException("Insufficient funds for withdrawal");
            }

            // Perform withdrawal
            psUpdate.setDouble(1, amount);
            psUpdate.setInt(2, id);
            psUpdate.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error withdrawing from account " + id, e);
        }

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        if (srcId == dstId) {
            throw new RuntimeException("Cannot transfer to the same account");
        }
        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }

        String querySrc = "SELECT balance FROM account WHERE id = ?";
        String queryUpdate = "UPDATE account SET balance = ? WHERE id = ?";

        try {
            // Start transaction
            dbConnection.setAutoCommit(false);

            // Get source balance
            double srcBalance;
            try (PreparedStatement psSrc = dbConnection.prepareStatement(querySrc)) {
                psSrc.setInt(1, srcId);
                ResultSet rs = psSrc.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Source account not found: " + srcId);
                }
                srcBalance = rs.getDouble("balance");
            }

            if (srcBalance < amount) {
                throw new RuntimeException("Insufficient funds in source account");
            }

            // Deduct from source
            try (PreparedStatement psUpdate = dbConnection.prepareStatement(queryUpdate)) {
                psUpdate.setDouble(1, srcBalance - amount);
                psUpdate.setInt(2, srcId);
                psUpdate.executeUpdate();
            }

            // Add to destination
            double dstBalance;
            try (PreparedStatement psDst = dbConnection.prepareStatement(querySrc)) {
                psDst.setInt(1, dstId);
                ResultSet rsDst = psDst.executeQuery();
                if (!rsDst.next()) {
                    throw new RuntimeException("Destination account not found: " + dstId);
                }
                dstBalance = rsDst.getDouble("balance");
            }

            try (PreparedStatement psUpdate = dbConnection.prepareStatement(queryUpdate)) {
                psUpdate.setDouble(1, dstBalance + amount);
                psUpdate.setInt(2, dstId);
                psUpdate.executeUpdate();
            }

            // Commit transaction
            dbConnection.commit();
            dbConnection.setAutoCommit(true);

        } catch (Exception e) {
            try {
                dbConnection.rollback();
                dbConnection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException("Error rolling back transaction", ex);
            }
            throw new RuntimeException("Error transferring funds", e);
        }
    }


    public void setConnection(Connection connection) {
        this.dbConnection = connection;
    }
}
