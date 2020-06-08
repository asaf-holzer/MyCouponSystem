package com.asafh.creatTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Customers_table {

	public static final String CREATE_CATEGORY_TABLE_STATEMENT = "CREATE TABLE `coupons_system`.`customers`"
			+ " (  `id` INT NOT NULL AUTO_INCREMENT," + "  `first_name` VARCHAR(45) NOT NULL, "
			+ " `last_name` VARCHAR(45) NOT NULL," + "  `email` VARCHAR(45) NOT NULL, "
			+ " `password` VARCHAR(45) NOT NULL," + "  PRIMARY KEY (`id`));";
	public static final String DROP_CATEGORY_TABLE_STATEMENT = "DROP TABLE `coupons_system`.`customers`";

	public static void initDBDAO(){
		Connection connection = null;

		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String sql = CREATE_CATEGORY_TABLE_STATEMENT;

			Statement statement = connection.createStatement();
			statement.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}
	
	public static void dropTable() {
		Connection connection = null;
		
		try {
			try {
				connection=com.asafh.utils. ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			String sql = DROP_CATEGORY_TABLE_STATEMENT;
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
			
		}
	}
}
