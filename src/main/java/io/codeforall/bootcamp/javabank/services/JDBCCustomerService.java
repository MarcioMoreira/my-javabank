package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCCustomerService implements CustomerService {

    private Connection dbConnection;

    public int count() {

        int result = 0;

        // create a new statement
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // create a query
        String query = "SELECT * FROM customer";

        // execute the query
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // get the results
        try {
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Customer get(Integer id) {

        if (id != null) {
            return null;
        }

        try {
            String query = "SELECT * FROM customer WHERE customer_id = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                return customer;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving customer with id " + id, e);
        }
        return null; // not found
    }

    @Override
    public List<Customer> list() {

        List<Customer> customerList = new ArrayList<>();

        try {
            String query = "SELECT * FROM customer";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customers", e);
        }
        return customerList;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        Set<Integer> setIdCustomers = new HashSet<>();

        if (id != null) {
            return null;
        }

        try {
            String query = "SELECT id FROM customer";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                setIdCustomers.add(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customers id", e);
        }
        return setIdCustomers;
    }

    @Override
    public double getBalance(int id) {
        try {
            String query = "SELECT SUM(balance) AS total_balance FROM account WHERE customer_id = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getDouble("total_balance");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving balance for customer " + id, e);
        }

        return 0;
    }

    @Override
    public void add(Customer customer) {
        try {
            String query = "INSERT INTO customer (first_name, id) VALUES (?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding new customer", e);
        }
    }

    public void setConnection(Connection connection) {
        this.dbConnection = connection;
    }
}
