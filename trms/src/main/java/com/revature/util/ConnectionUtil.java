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
		String url = "jdbc:oracle:thin:@mydbinstance.cnao0rahwpz0.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "trms_user";
		String password = "trmsp4ssw0rd";
		
		return DriverManager.getConnection(url, user, password);
    	
    	
    	//    	DriverManager dm = null;
//        dm.registerDriver(new OracleDriver());
//
//        Properties prop = new Properties();
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream in = classLoader.getResourceAsStream("connection.properties");
//        prop.load(in);
//
//        String url = prop.getProperty("url");
//        String user = prop.getProperty("user");
//        String password = prop.getProperty("password");
//
//        return dm.getConnection(url, user, password);
    }
}