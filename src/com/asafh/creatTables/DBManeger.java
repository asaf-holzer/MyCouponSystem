package com.asafh.creatTables;

public class DBManeger {

	public static void createAllTables() {
		Categories_table.initDBDAO();
		Companies_table.initDBDAO();
		Coupons_table.initDBDAO();
		Customers_table.initDBDAO();
		Customers_vs_coupons_table.initDBDAO();

	}

	public static void dropAllTables() {
		Customers_vs_coupons_table.dropTable();
		Customers_table.dropTable();
		Coupons_table.dropTable();
		Companies_table.dropTable();
		Categories_table.dropTable();

	}

	public static void createCategoriesTables() {
		Categories_table.initDBDAO();
	}

	public static void createCompaniesTables() {

		Companies_table.initDBDAO();
	}

	public static void createCouponsTables() {

		Coupons_table.initDBDAO();
	}

	public static void createCustomersTables() {

		Customers_table.initDBDAO();
	}

	public static void createCustomers_vs_couponsTables() {

		Customers_vs_coupons_table.initDBDAO();
	}
	
}
