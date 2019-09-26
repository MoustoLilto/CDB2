package com.excilys.computer.database.dao;

import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariDataSource;

public class Connect {
	
	private static Logger logger = LogManager.getLogger(Connect.class);
	private static HikariDataSource ds;
	private static Connection connection = null;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("database");
		String url = bundle.getString("url");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		String driver = bundle.getString("driver");
		
		ds = new HikariDataSource();
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDriverClassName(driver);
		logger.info("INFORMATIONS DE CONNEXIONS RECUPEREES!");
	}
	
	private Connect() {}
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = ds.getConnection();
				logger.info("Connection établie!");
			} catch (SQLException e1) {
				logger.fatal("Erreur de connexion a la BDD! erreur:" + e1);
			}
		}
		return connection;
	}
	
	public static void closeConnection() {    
        if (connection != null) {
        	try {
        		connection.close();
        		logger.info("Connection Fermé!");
	        } catch (SQLException e1) {
	        	logger.fatal("Erreur de fermeture de la connexion a la BDD! erreur:" + e1);
	        } finally {
	        	connection = null;
	        }
        }
	}
}
