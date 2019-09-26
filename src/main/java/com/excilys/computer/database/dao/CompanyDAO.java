package com.excilys.computer.database.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompanyDAO {
	final static Logger logger = LogManager.getLogger(ComputerDAO.class);
	private static CompanyDAO companydao = null;
	
	private CompanyDAO() {
	}
	
	public static CompanyDAO getInstance() {
		if (companydao == null) {
			companydao = new CompanyDAO();
		}
		return companydao;
	}
}
