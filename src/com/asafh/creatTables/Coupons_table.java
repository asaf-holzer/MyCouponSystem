package com.asafh.creatTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Coupons_table {

	public static final String CREATE_CATEGORY_TABLE_STATEMENT = "CREATE TABLE `coupons_system`.`coupons` "
			+ "(  `id` INT NOT NULL AUTO_INCREMENT, " + " `company_id` INT NOT NULL, " + " `category_id` INT NOT NULL,"
			+ "  `title` VARCHAR(45) NOT NULL," + "  `description` VARCHAR(45) NOT NULL,"
			+ "  `start_date` DATE NOT NULL," + "  `end_date` DATE NOT NULL," + "  `amount` INT NOT NULL,"
			+ "  `price` DOUBLE NOT NULL," + "  `image` VARCHAR(45) NOT NULL," + "  PRIMARY KEY (`id`),"
			+ "  INDEX `company_id_idx`" + " (`company_id` ASC) VISIBLE," + "  INDEX `category_id_idx`"
			+ " (`category_id` ASC) VISIBLE," + "  CONSTRAINT `company_id`" + "    FOREIGN KEY (`company_id`) "
			+ "   REFERENCES `coupons_system`.`companies` (`id`)" + "    ON DELETE NO ACTION "
			+ "   ON UPDATE NO ACTION," + "  CONSTRAINT `category_id`  " + "  FOREIGN KEY (`category_id`)  "
			+ "  REFERENCES `coupons_system`.`categories` (`id`) " + "   ON DELETE NO ACTION "
			+ "   ON UPDATE NO ACTION)";
	public static final String DROP_CATEGORY_TABLE_STATEMENT = "DROP TABLE `coupons_system`.`coupons`";

	public static void initDBDAO() {
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
