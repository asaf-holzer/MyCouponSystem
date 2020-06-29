//package com.asafh.creatTables;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Customers_vs_coupons_table {
//
//	public static final String CREATE_CATEGORY_TABLE_STATEMENT = "CREATE TABLE `coupons_system`.`customers_vs_coupons`"
//			+ " (  `customer_id` INT NOT NULL,"
//			+ "  `coupon_id` INT NOT NULL,"
//			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),"
//			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,"
//			+ "  CONSTRAINT `customer_id` "
//			+ "   FOREIGN KEY (`customer_id`) "
//			+ "   REFERENCES `coupons_system`.`customers` (`id`) "
//			+ "   ON DELETE NO ACTION "
//			+ "   ON UPDATE NO ACTION, "
//			+ " CONSTRAINT `coupon_id`  "
//			+ "  FOREIGN KEY (`coupon_id`)  "
//			+ "  REFERENCES `coupons_system`.`coupons` (`id`)  "
//			+ "  ON DELETE NO ACTION "
//			+ "   ON UPDATE NO ACTION);";
//	public static final String DROP_CATEGORY_TABLE_STATEMENT = "DROP TABLE `coupons_system`.`customers_vs_coupons`";
//
//
//	public static void initDBDAO()  {
//		Connection connection = null;
//
//		try {
//			try {
//				connection=com.asafh.utils. ConnectionPool.getInstance().getConnection();
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
//
//			String sql =CREATE_CATEGORY_TABLE_STATEMENT;
//
//			Statement statement = connection.createStatement();
//			statement.execute(sql);
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//
//		} finally {
//			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
//			connection = null;
//
//		}
//	}
//	
//	public static void dropTable() {
//		Connection connection = null;
//		
//		try {
//			try {
//				connection=com.asafh.utils. ConnectionPool.getInstance().getConnection();
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
//			
//			String sql = DROP_CATEGORY_TABLE_STATEMENT;
//			
//			Statement statement = connection.createStatement();
//			statement.execute(sql);
//			
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			
//		} finally {
//			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
//			connection = null;
//			
//		}
//	}
//}
