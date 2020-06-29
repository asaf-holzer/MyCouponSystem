package com.asafh.creatTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManeger {
	
	public static final String CREATE_TABLE_COMPANIES="CREATE TABLE `coupons_system`.`companies`"
			+ " (  `id` INT NOT NULL AUTO_INCREMENT,  `name` VARCHAR(45) NOT NULL, "
			+ " `email` VARCHAR(45) NOT NULL,  `password` VARCHAR(45) NOT NULL,"
			+ "  PRIMARY KEY (`id`));";
	public static final String CREATE_TABLE_CUSTOMERS="CREATE TABLE `coupons_system`.`customers`"
			+ " (  `id` INT NOT NULL AUTO_INCREMENT," + "  `first_name` VARCHAR(45) NOT NULL, "
			+ " `last_name` VARCHAR(45) NOT NULL," + "  `email` VARCHAR(45) NOT NULL, "
			+ " `password` VARCHAR(45) NOT NULL," + "  PRIMARY KEY (`id`));";
	public static final String CREATE_TABLE_COUPONS= "CREATE TABLE `coupons_system`.`coupons` "
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
	public static final String CREATE_TABLE_CATEGORIES="CREATE TABLE `coupons_system`.`categories` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `name` VARCHAR(45) NOT NULL," + 
			"  PRIMARY KEY (`id`));";
	public static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS= "CREATE TABLE `coupons_system`.`customers_vs_coupons`"
			+ " (  `customer_id` INT NOT NULL,"
			+ "  `coupon_id` INT NOT NULL,"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,"
			+ "  CONSTRAINT `customer_id` "
			+ "   FOREIGN KEY (`customer_id`) "
			+ "   REFERENCES `coupons_system`.`customers` (`id`) "
			+ "   ON DELETE NO ACTION "
			+ "   ON UPDATE NO ACTION, "
			+ " CONSTRAINT `coupon_id`  "
			+ "  FOREIGN KEY (`coupon_id`)  "
			+ "  REFERENCES `coupons_system`.`coupons` (`id`)  "
			+ "  ON DELETE NO ACTION "
			+ "   ON UPDATE NO ACTION);";
	
	public static final String DROP_TABLE_COMPANIES="DROP TABLE `coupons_system`.`companies`"; 
	public static final String DROP_TABLE_CUSTOMERS="DROP TABLE `coupons_system`.`customers`";
	public static final String DROP_TABLE_COUPONS="DROP TABLE `coupons_system`.`coupons`";
	public static final String DROP_TABLE_CATEGORIES="DROP TABLE `coupons_system`.`categories`"; 
	public static final String DROP_TABLE_CUSTOMERS_VS_COUPONS="DROP TABLE `coupons_system`.`customers_vs_coupons`";
	
	public static void runTables(String input) {
		Connection connection = null;

		try {
			try {
				connection=com.asafh.utils. ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

			String sql = input;

			Statement statement = connection.createStatement();
			statement.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	public static void dropAndCreateAllTables() {
		
		runTables(DROP_TABLE_CUSTOMERS_VS_COUPONS);
		runTables(DROP_TABLE_CUSTOMERS);
		runTables(DROP_TABLE_COUPONS);
		runTables(DROP_TABLE_COMPANIES);
		runTables(DROP_TABLE_CATEGORIES);
		
		runTables(CREATE_TABLE_COMPANIES);
		runTables(CREATE_TABLE_CUSTOMERS);
		runTables(CREATE_TABLE_CATEGORIES);
		runTables(CREATE_TABLE_COUPONS);		
		runTables(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
	}		
			
}
