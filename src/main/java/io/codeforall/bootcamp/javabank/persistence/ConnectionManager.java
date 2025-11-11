package io.codeforall.bootcamp.javabank.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASS = "postgres";
    private static final String DEFAULT_HOST = "localhost";
    public static final Integer DEFAULT_PORT = 5432;
    private static final String DEFAULT_DB = "javabank";

    private static final String CONNECTOR = "jdbc:postgresql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    public ConnectionManager(String user, String pass, String host, Integer port, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + ":" + port + "/" + database;
    }

    public ConnectionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DB);
    }

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
