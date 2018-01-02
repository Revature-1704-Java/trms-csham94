package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException, IOException {

        DriverManager.registerDriver(new OracleDriver());

        Properties prop = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream("connection.properties");
        prop.load(in);

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }
}