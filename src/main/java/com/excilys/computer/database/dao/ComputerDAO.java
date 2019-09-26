package com.excilys.computer.database.dao;

import java.util.*;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computer.database.modeles.Company;
import com.excilys.computer.database.modeles.Computer;

public class ComputerDAO {
	final static Logger logger = LogManager.getLogger(ComputerDAO.class);
	private static ComputerDAO computerdao = null;
	
	private ComputerDAO() {
	}
	
	public static ComputerDAO getInstance() {
		if (computerdao == null) {
			computerdao = new ComputerDAO();
		}
		return computerdao;
	}
	
	public List<Computer> getAllComputer() {
		List<Computer> computers = new ArrayList<Computer>();
		String query = RequetesComputerSQL.ALL.toString();
			
		try (Statement stmt = Connect.getConnection().createStatement()){
			ResultSet results = stmt.executeQuery(query);
						
			while (results.next()) {
            	Computer computer = new Computer(); 										//Creation du tuple
            	
            	computer.setId(results.getLong(1));											//Ajout des attributs au tuple
            	computer.setName(results.getString(2));
            	if (results.getDate(3) == null) {
            		computer.setIntroduced(null);
            	}
            	else {
            		computer.setIntroduced(results.getDate(3).toLocalDate());
            	}
            	if (results.getDate(4) == null) {
            		computer.setDiscontinued(null);
            	}
            	else {
            		computer.setDiscontinued(results.getDate(4).toLocalDate());
            	}
            	long companyID = results.getLong(5);
	        	String companyName = results.getString(6);
	        	Company company = null;
	        	if (companyID != 0) {
	        		company = new Company(companyID, companyName);
	        	}
	        	computer.setCompany(company);

            	computers.add(computer); 													//Ajout du tuple a la liste
            }
		} catch (SQLException e1) {
			logger.error("Error getting the list of computers! erreur:" + e1);
		} finally {
			Connect.closeConnection();
		}
		return computers;
	}
}
