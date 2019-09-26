package com.excilys.computer.database.cli;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computer.database.dao.ComputerDAO;
import com.excilys.computer.database.modeles.Computer;

public class Main {
	private static Logger logger = LogManager.getLogger(Main.class);
	static ComputerDAO computerdao = ComputerDAO.getInstance();

	public static void main(String[] args) {
		logger.info("Debut Main");
		
		List<Computer> computers = computerdao.getAllComputer();
		for (Computer computer : computers) {
			System.out.println(computer);
		}
		
		logger.info("Fin Main");
	}

}
