import java.util.Date;
import java.util.List;

import com.asafh.Login.ClientType;
import com.asafh.Login.LoginManager;
import com.asafh.PrintUtils.PrintLines;
import com.asafh.PrintUtils.PrintTitles;
import com.asafh.beans.Category;
import com.asafh.beans.Company;
import com.asafh.beans.Coupon;
import com.asafh.beans.Customer;
import com.asafh.creatTables.DBManeger;
import com.asafh.dao.CategoriesDAO;
import com.asafh.dao.CompaniesDAO;
import com.asafh.dao.CouponsDAO;
import com.asafh.dao.CustomersDAO;
import com.asafh.dbdao.CategoriesDBDAO;
import com.asafh.dbdao.CompaniesDBDAO;
import com.asafh.dbdao.CouponsDBDAO;
import com.asafh.dbdao.CustomersDBDAO;
import com.asafh.facade.AdminFacade;
import com.asafh.utils.BeautyTable;
import com.asafh.utils.CompanyException;
import com.asafh.utils.ConnectionPool;
import com.asafh.utils.CustomerException;
import com.asafh.utils.DateWrongException;
import com.asafh.utils.TicketsSoldOutException;
import com.asafh.utils.duplicateCategoryException;
import com.asafh.utils.updateCompanyException;

public class Test {

	public static Date realDate(int year, int month, int day) {
		Date d1 = new Date(year - 1900, month - 1, day + 1);
		return d1;
	}

	public static java.sql.Date replaseDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static void printArraylist(List arr) {
		System.out.println(
				"****************************************************************************************************");
		for (Object object : arr) {
			System.out.println(object);
		}
		System.out.println(
				"****************************************************************************************************");
	}

	public static void TestAll() throws DateWrongException, duplicateCategoryException, TicketsSoldOutException, CompanyException {
		PrintTitles.asafsP();
		DBManeger.dropAndCreateAllTables();

		CompaniesDAO cmpDbdao = new CompaniesDBDAO();
		CustomersDAO cstDbdao = new CustomersDBDAO();
		CouponsDAO copDbdao = new CouponsDBDAO();
		CategoriesDAO ctgDbdao = new CategoriesDBDAO();

		// create companies
		PrintLines.PrintLines();
		System.out.println("create the companies...");
		Company cmp1 = new Company("coca cola", "cola@coca.com", "1234");
		Company cmp2 = new Company("pepsy", "pepsy@cola.com", "2345");
		Company cmp3 = new Company("Alfredo", "alf@redo.com", "3456");
		Company cmp4 = new Company("Sony", "sony@sony.com", "4567");

		// insert companies
		System.out.println("add companies to the table...");
		cmpDbdao.addCompany(cmp1);
		cmpDbdao.addCompany(cmp2);
		cmpDbdao.addCompany(cmp3);
		cmpDbdao.addCompany(cmp4);

		// insert\create categories
		PrintLines.PrintLines();
		System.out.println("create and add categories to the table...");
		ctgDbdao.addCategory(Category.Electricity);
		ctgDbdao.addCategory(Category.Food);
		ctgDbdao.addCategory(Category.Restaurant);
		ctgDbdao.addCategory(Category.Vacation);

		// create coupons
		PrintLines.PrintLines();
		System.out.println("create the coupons...");
		Coupon cp1 = new Coupon(cmp1.getId(), Category.Food, "1+1", "buy one get one", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 100, 3.0, "https://bla bla... ");
		Coupon cp2 = new Coupon(cmp2.getId(), Category.Food, "1+2", "buy one get two", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 50, 3.5, "https://bla bla... ");
		Coupon cp3 = new Coupon(cmp3.getId(), Category.Restaurant, "2+1", "the therd free", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 70, 2.0, "https://bla bla... ");
		Coupon cp4 = new Coupon(cmp4.getId(), Category.Electricity, "hat", "buy one get hat", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 85, 4.5, "https://bla bla... ");

		// insert coupons
		System.out.println("add coupons to the table...");
		copDbdao.addCoupon(cp1);
		copDbdao.addCoupon(cp2);
		copDbdao.addCoupon(cp3);
		copDbdao.addCoupon(cp4);

		// create customers
		PrintLines.PrintLines();
		System.out.println("create the customers...");
		Customer cs1 = new Customer("Asaf", "Holzer", "asafh101@gmail.com", "12345");
		Customer cs2 = new Customer("David", "Hamelech", "david@gmail.com", "23456");
		Customer cs3 = new Customer("Moshe", "Cohen", "moshe@gmail.com", "34567");
		Customer cs4 = new Customer("Rina", "Dovev", "rina@gmail.com", "45678");
		Customer cs5 = new Customer("sara", "Rozen", "sara@gmail.com", "56789");
		Customer cs6 = new Customer("Israel", "Levi", "levi@gmail.com", "67890");

		// insert customers
		System.out.println("add customers to the table...");
		cstDbdao.addCustomer(cs1);
		cstDbdao.addCustomer(cs2);
		cstDbdao.addCustomer(cs3);
		cstDbdao.addCustomer(cs4);
		cstDbdao.addCustomer(cs5);
		cstDbdao.addCustomer(cs6);

//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		
//		    ____                                               _              
//		   / ___|   ___    _ __ ___    _ __     __ _   _ __   (_)   ___   ___ 
//		  | |      / _ \  | '_ ` _ \  | '_ \   / _` | | '_ \  | |  / _ \ / __|
//		  | |___  | (_) | | | | | | | | |_) | | (_| | | | | | | | |  __/ \__ \
//		   \____|  \___/  |_| |_| |_| | .__/   \__,_| |_| |_| |_|  \___| |___/
//		                              |_|                                     
//		
//		
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		

		PrintTitles.companies();

		// print the companies
		PrintLines.PrintLines();
		System.out.println("the companies list");
		BeautyTable.tableWithLinesCompanies(cmpDbdao.getAllCompanies());
		
		// try the isCompanyExists boolean method
		PrintLines.PrintLines();
		System.out.println("try the isCompanyExists boolean method");
		PrintLines.printOneLine();
		System.out.println("Company with email: alf@redo.com and password: 3456 , is exist?");
		System.out.println(cmpDbdao.isCompanyExists("alf@redo.com", "3456"));
		System.out.println("Company with email: mish@mish.com and password: 3456 , is exist?");
		System.out.println(cmpDbdao.isCompanyExists("mish@mish.com", "3456"));
		System.out.println("Company with email: alf@redo.com and password: 1234567890 , is exist?");
		System.out.println(cmpDbdao.isCompanyExists("alf@redo.com", "1234567890"));
		System.out.println("Company with email: alf@redo.com and password: 4567 , is exist?");
		System.out.println(cmpDbdao.isCompanyExists("alf@redo.com", "4567"));

		// try the isCompanyExistsByNameOrEmail boolean method
		PrintLines.PrintLines();
		System.out.println("try the isCompanyExistsByNameOrEmail boolean method");
		PrintLines.printOneLine();
		System.out.println("Company with name: Alfredo and email: mish@mish.com, is exist?");
		System.out.println(cmpDbdao.isCompanyExistsByNameOrEmail("Alfredo", " mish@mish.com"));
		System.out.println("Company with name: Moshe and email: levi@gmail.com, is exist?");
		System.out.println(cmpDbdao.isCompanyExistsByNameOrEmail("Moshe", "levi@gmail.com"));

		// try the update method
		PrintLines.PrintLines();
		System.out.println("try the update- companies method");
		PrintLines.printOneLine();
		System.out.println("the companies list before update");
		BeautyTable.tableWithLinesCompanies(cmpDbdao.getAllCompanies());
		
		System.out.println("company 1 try to update id...");
		cmp1.setId(9);
		cmpDbdao.updateCompany(1, cmp1);
		System.out.println("company 2 try to update name...");
		cmp2.setName("pepsy-cola");
		cmpDbdao.updateCompany(2, cmp2);
		System.out.println("company 3 try to update email...");
		cmp3.setEmail("alf@alf.com");
		cmpDbdao.updateCompany(3, cmp3);
		System.out.println("company 4 try to update password...");
		cmp4.setPassword("1234567890");
		cmpDbdao.updateCompany(4, cmp4);
		System.out.println("the companies list after update");
		BeautyTable.tableWithLinesCompanies(cmpDbdao.getAllCompanies());
		
		// try the delete method
		PrintLines.PrintLines();
		System.out.println("try the delete method");
		PrintLines.printOneLine();

		System.out.println("try to delete company 3...");
		cmpDbdao.deleteCompany(3);
		System.err.println("Attention!!!  the company didn't delete because it is foring key. wait to facade ");
		System.out.println("the companies list after delete");
		//printArraylist(cmpDbdao.getAllCompanies());
		BeautyTable.tableWithLinesCompanies(cmpDbdao.getAllCompanies());
		
		// try getOneCompany method
		PrintLines.PrintLines();
		System.out.println("try the getOneCompany method");
		PrintLines.printOneLine();
		System.out.println("get company 4");
		BeautyTable.tableWithLinesOneCompany(cmpDbdao.getOneCompany(4));


//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		
//		                     ____                                               
//	                        / ___|   ___    _   _   _ __     ___    _ __    ___ 
//	                       | |      / _ \  | | | | | '_ \   / _ \  | '_ \  / __|
//		                   | |___  | (_) | | |_| | | |_) | | (_) | | | | | \__ \
//		                    \____|  \___/   \__,_| | .__/   \___/  |_| |_| |___/
//		                                           |_|                          
//		
//		
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		
		
		PrintTitles.coupons();

		// print the coupons
		PrintLines.PrintLines();
		System.out.println("the coupons list");
		BeautyTable.tableWithLinesCoupons(copDbdao.getAllCoupons());
		
		// try the update method
		PrintLines.PrintLines();
		System.out.println("try the update- coupons method");
		PrintLines.printOneLine();

		cp1.setCompanyID(9);
		cp1.setCategory(Category.Electricity);
		cp2.setTitle("3+1");
		cp2.setDestraction("aaaaaaaaaaaaaaa");
		cp3.setStartDate(realDate(2000, 10, 24));
		cp3.setEndDate(realDate(1998, 12, 5));
		cp4.setAmount(999);
		cp4.setPrice(55.5);
		cp1.setImage("picture");

		System.out.println("copon 1 try to update company_id , category , image");
		System.out.println("copon 2 try to update title , description");
		System.out.println("copon 3 try to update startDate , endDate");
		System.out.println("copon 4 try to update amount , price");

		copDbdao.updateCoupon(cp1);
		copDbdao.updateCoupon(cp2);
		copDbdao.updateCoupon(cp3);
		copDbdao.updateCoupon(cp4);

		System.out.println("the coupons list after update");
		BeautyTable.tableWithLinesCoupons(copDbdao.getAllCoupons());
		
		// try the delete method
		PrintLines.PrintLines();
		System.out.println("try the delete- coupons method");
		PrintLines.printOneLine();

		System.out.println("try to delete coupon #3");
		copDbdao.deleteCoupon(3);

		System.out.println("the coupons list after delete");
		BeautyTable.tableWithLinesCoupons(copDbdao.getAllCoupons());
		
		// try the getOneCoupon method
		PrintLines.PrintLines();
		System.out.println("try to get One Coupon #4");
		PrintLines.printOneLine();
		BeautyTable.tableWithLinesOneCoupon(copDbdao.getOneCoupon(4));
		PrintTitles.couponPurchase();

		// try the addCouponPurchase method
		PrintLines.PrintLines();
		System.out.println("try the add Coupon Purchase");
		PrintLines.printOneLine();

		copDbdao.addCouponPurchase(cs1.getId(), cp1.getId());
		copDbdao.addCouponPurchase(cs2.getId(), cp2.getId());
		// copDbdao.addCouponPurchase(cs3.getId(), cp3.getId());
		copDbdao.addCouponPurchase(cs4.getId(), cp4.getId());

		System.out.println("the coupons after add Coupon Purchase");
		System.out.println("look the change at the amount column");
		BeautyTable.tableWithLinesCoupons(copDbdao.getAllCoupons());
		
		// try the deleteCouponPurchase method
		PrintLines.PrintLines();
		System.out.println("try the delete Coupon Purchase");
		PrintLines.printOneLine();

		copDbdao.deleteCouponPurchase(cs2.getId(), cp2.getId());
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		
//		    ____                 _                                     
//		   / ___|  _   _   ___  | |_    ___    _ __ ___     ___   _ __ 
//		  | |     | | | | / __| | __|  / _ \  | '_ ` _ \   / _ \ | '__|
//		  | |___  | |_| | \__ \ | |_  | (_) | | | | | | | |  __/ | |   
//		   \____|  \__,_| |___/  \__|  \___/  |_| |_| |_|  \___| |_|   
//
//		
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		
		//print the customer table
		PrintTitles.customer();
		System.out.println("print the customer table");
		BeautyTable.tableWithLinesCustomers(cstDbdao.getAllCustomers());
		

		// try the isCustomerExists boolean method
		PrintLines.PrintLines();
		System.out.println("try the isCustomerExists boolean method");
		PrintLines.printOneLine();
		System.out.println("Customer with email: asafh101@gmail.com and password: 12345 , is exist?");
		System.out.println(cstDbdao.isCustomerExists("asafh101@gmail.com", "12345"));
		System.out.println("Customer with email: mish@mish.com and password: 23456 , is exist?");
		System.out.println(cstDbdao.isCustomerExists("mish@mish.com", "23456"));
		System.out.println("Customer with email: moshe@gmail.com and password: 1234567890 , is exist?");
		System.out.println(cstDbdao.isCustomerExists("moshe@gmail.com", "1234567890"));
		System.out.println("Customer with email: alf@redo.com and password: 9876 , is exist?");
		System.out.println(cstDbdao.isCustomerExists("alf@redo.com", "9876"));

		//try the updateCustomer method
		PrintLines.PrintLines();
		System.out.println("try the update- customers method");
		PrintLines.printOneLine();
		System.out.println("the customer list before update");
		BeautyTable.tableWithLinesCustomers(cstDbdao.getAllCustomers());;
		
		System.out.println("customer 1 try to update id...");
		cs1.setId(9);
		cstDbdao.updateCustomer(cs1);
		System.out.println("customer 2 try to update first name...");
		cs2.setFirstName("Haim");
		cstDbdao.updateCustomer(cs2);
		System.out.println("customer 3 try to update last name...");
		cs3.setLastName("Navon");
		cstDbdao.updateCustomer(cs3);
		System.out.println("customer 4 try to update email...");
		cs4.setEmail("rinaD@gmail.com");
		cstDbdao.updateCustomer(cs4);
		System.out.println("customer 5 try to update password...");
		cs5.setPassword("6543221");
		cstDbdao.updateCustomer(cs5);
		System.out.println("the customers after updating:");
		BeautyTable.tableWithLinesCustomers(cstDbdao.getAllCustomers());

		
		
		//try the deleteCustomer method
		PrintLines.PrintLines();
		System.out.println("try the deleteCustomer method");
		PrintLines.printOneLine();
		System.out.println("tryto delete customer #4");
		cstDbdao.deleteCustomer(4);
		System.err.println("Attention!!!  the customer didn't delete because it is foring key. wait to facade ");
		System.out.println("the customers after delete:");
		BeautyTable.tableWithLinesCustomers(cstDbdao.getAllCustomers());
		
		//try the getOneCustomer
		PrintLines.PrintLines();
		System.out.println("try the getOneCustomer method");
		PrintLines.printOneLine();
		System.out.println("try to get customer #2");
		BeautyTable.tableWithLinesOneCustomer(cstDbdao.getOneCustomer(2));
		
		

		
//		
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************
//		                       _____                              _        
//		                      |  ___|   __ _    ___    __ _    __| |   ___ 
//		                      | |_     / _` |  / __|  / _` |  / _` |  / _ \
//		                      |  _|   | (_| | | (__  | (_| | | (_| | |  __/
//		                      |_|      \__,_|  \___|  \__,_|  \__,_|  \___|
//
//		********************************************************************************************
//		********************************************************************************************
//		********************************************************************************************		                                               
		// now try the facade layer
		// first of all drop the tables and restart

		PrintLines.PrintLines();
		PrintTitles.clientFacade();
		PrintLines.PrintLines();
		System.out.println(
				"-----------------------------------------add && update companies------------------------------------");
		PrintLines.PrintLines();
		DBManeger.dropAndCreateAllTables();

		// insert\create categories
		PrintLines.PrintLines();
		System.out.println("add categories");
		CategoriesDBDAO catgDbdao = new CategoriesDBDAO();
		catgDbdao.addCategory(Category.Electricity);
		catgDbdao.addCategory(Category.Food);
		catgDbdao.addCategory(Category.Restaurant);
		catgDbdao.addCategory(Category.Vacation);

		// create companies
		Company comp1 = new Company("coca cola", "cola@coca.com", "1234");
		Company comp2 = new Company("pepsy", "pepsy@cola.com", "2345");
		Company comp3 = new Company("Alfredo", "alf@redo.com", "3456");
		Company comp4 = new Company("Sony", "sony@sony.com", "4567");
		Company comp5 = new Company("Sony", "sany@sany.com", "4567");
		Company comp6 = new Company("Sanany", "sony@sony.com", "4567");

		AdminFacade manager = (AdminFacade) LoginManager.getInstance().Login("admin@admin.com", "admin",
				ClientType.Administrator);

		// insert new companies
		try {
			manager.addCompany(comp1);
			manager.addCompany(comp2);
			manager.addCompany(comp3);
			manager.addCompany(comp4);

		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("the companies added by Administrator");
		//printArraylist(manager.getAllCompanies());
		BeautyTable.tableWithLinesCompanies(cmpDbdao.getAllCompanies());
		PrintLines.PrintLines();

		System.out.println("try to add company with the same name");
		try {
			manager.addCompany(comp5);

		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}
		PrintLines.printOneLine();

		System.out.println("try to add company with the same email");
		try {
			manager.addCompany(comp6);
		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}

		// try the update company method
		PrintLines.PrintLines();
		System.out.println("try the update company method");
		PrintLines.printOneLine();		
		System.out.println("the origin company details: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(1));
		System.out.println("try to update id company");
		comp1.setId(2);
		try {
			manager.updateCompany(1, comp1);

		} catch (updateCompanyException e) {
			System.err.println(e.getMessage());
		}
		System.out.println();
		System.out.println("the company details after updating: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(1));
		PrintLines.printOneLine();

		System.out.println("the origin company details: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(2));
		System.out.println("try to update name company");
		comp2.setName("pepsi-cola");
		try {
			manager.updateCompany(2, comp2);
		} catch (updateCompanyException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the company details after updating: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(2));
		
		PrintLines.printOneLine();

		System.out.println("the origin company details: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(3));
		
		System.out.println("try to update email company");
		comp3.setEmail("alf@alfredo.com");
		try {
			manager.updateCompany(3, comp3);
		} catch (updateCompanyException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the company details after updating: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(3));
		PrintLines.printOneLine();

		System.out.println("the origin company details: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(4));
		System.out.println("try to update password company");
		comp4.setPassword("45678");
		try {
			manager.updateCompany(4, comp4);
		} catch (updateCompanyException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the company details after updating: ");
		BeautyTable.tableWithLinesOneCompany(manager.getOneCompany(4));
		PrintLines.printOneLine();

		PrintLines.PrintLines();
		System.out.println(
				"-----------------------------------------add && update customers------------------------------------");
		
		// create customers
		Customer cst1 = new Customer("Asaf", "Holzer", "asafh101@gmail.com", "12345");
		Customer cst2 = new Customer("David", "Hamelech", "david@gmail.com", "23456");
		Customer cst3 = new Customer("Moshe", "Cohen", "moshe@gmail.com", "34567");
		Customer cst4 = new Customer("Rina", "Dovev", "rina@gmail.com", "45678");
		Customer cst5 = new Customer("sara", "Rozen", "sara@gmail.com", "56789");
		Customer cst6 = new Customer("Israel", "Levi", "sara@gmail.com", "67890");

		// insert customers
		PrintLines.PrintLines();
		System.out.println("add customers");
		try {
			manager.addCustomer(cst1);
			manager.addCustomer(cst2);
			manager.addCustomer(cst3);
			manager.addCustomer(cst4);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			manager.addCustomer(cst5);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		try {
			System.out.println("try to add customer with email exist...");
			manager.addCustomer(cst6);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("the custoners added by Administrator:");
		//printArraylist(manager.getAllCustomers());
		BeautyTable.tableWithLinesCustomers(manager.getAllCustomers());
		PrintLines.PrintLines();
		
		//try the update customers
		PrintLines.PrintLines();
		System.out.println("try the update customers");
		PrintLines.printOneLine();
		System.out.println("the origin customer details: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(1));
		System.out.println("try to update id");
		cst1.setId(2);
		try {
			manager.updateCustomer(1, cst1);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the customer details after updating: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(1));
		PrintLines.printOneLine();
		System.out.println("the origin customer details: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(2));
		System.out.println("try to update first name");
		cst2.setFirstName("aaaa");
		try {
			manager.updateCustomer(2, cst2);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the customer details after updating: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(2));
		PrintLines.printOneLine();
		System.out.println("the origin customer details: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(3));
		System.out.println("try to update last name");
		cst3.setLastName("bbbb");
		try {
			manager.updateCustomer(3, cst3);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the customer details after updating: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(3));
		PrintLines.printOneLine();
		System.out.println("the origin customer details: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(4));
		System.out.println("try to update email");
		cst4.setEmail("asaf@hollzer.com");
		try {
			manager.updateCustomer(4, cst4);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the customer details after updating: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(4));
		PrintLines.printOneLine();
		System.out.println("the origin customer details: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(5));
		System.out.println("try to update password");
		cst5.setPassword("92745");
		try {
			manager.updateCustomer(5, cst5);
		} catch (CustomerException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("the customer details after updating: ");
		BeautyTable.tableWithLinesOneCustomer(manager.getOneCustomer(5));
//		CompanyFacade company=(CompanyFacade) LoginManager.getInstance().Login("admin@admin.com", "admin",
//				ClientType.Administrator);
		
		
		// create coupons
		Coupon coup1 = new Coupon(comp1.getId(), Category.Food, "1+1", "buy one get one", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 100, 3.0, "https://bla bla... ");
		Coupon coup2 = new Coupon(comp2.getId(), Category.Food, "1+2", "buy one get two", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 50, 3.5, "https://bla bla... ");
		Coupon coup3 = new Coupon(comp3.getId(), Category.Restaurant, "2+1", "the therd free", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 70, 2.0, "https://bla bla... ");
		Coupon coup4 = new Coupon(comp4.getId(), Category.Electricity, "hat", "buy one get hat", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 85, 4.5, "https://bla bla... ");
	}

	public static void main(String[] args) throws InterruptedException, TicketsSoldOutException, DateWrongException,
			CustomerException, duplicateCategoryException {

		try {
			TestAll();
		} catch (DateWrongException | duplicateCategoryException | TicketsSoldOutException | CompanyException e) {
			
			System.out.println(e.getMessage());
		}
//		
//		
//		AdminFacade af= new AdminFacade();
//		
//		
//			
//			try {
//				af.addCompany(cmp5);
//				
//			} catch (duplicateCompanyException e2) {
//				// TODO Auto-generated catch block
//				System.out.println(e2.getMessage());
//			}
//			
//			try {
//				af.addCompany(cmp6);
//			} catch (duplicateCompanyException e2) {
//				// TODO Auto-generated catch block
//				System.out.println(e2.getMessage());
//			}
//			
//			System.out.println("company number #2 before update is: "+cmpDbdao.getOneCompany(2));
//			System.out.println("***************************");
//			
//			
//			cmp2.setPassword("123456");
//			
//			try {
//				af.updateCompany(2, cmp2);
//			} catch (updateCompanyException e1) {
//				// TODO Auto-generated catch block
//				System.out.println(e1.getMessage());
//			}
//			cmp3.setName("alfredo&shut");
//			try {
//				af.updateCompany(3, cmp3);
//			} catch (updateCompanyException e1) {
//				// TODO Auto-generated catch block
//				System.out.println(e1.getMessage());
//			}
//			
//			
//			System.out.println("all of the companies");
//			printArraylist(cmpDbdao.getAllCompanies());
//			System.out.println("company number #4");
//			System.out.println(cmpDbdao.getOneCompany(4));
//			
//			System.out.println("##################################");
//			
//			
//
//
//		
//		
//		
//		
//		cstDbdao.addCustomer(cs1);
//		cstDbdao.addCustomer(cs2);
//		cstDbdao.addCustomer(cs3);
//		cstDbdao.addCustomer(cs4);
//		cstDbdao.addCustomer(cs5);
//		cstDbdao.addCustomer(cs6);
//		
//		System.out.println("customer number #3 before update is: "+cstDbdao.getOneCustomer(3));
//		System.out.println("*******************************");
//		
//		cs3.setEmail("moshecohen@gmail.com");
//
//		cstDbdao.updateCustomer(cs3);
//		//cstDbdao.deleteCustomer(5);
//		System.out.println("all customers");
//		printArraylist(cstDbdao.getAllCustomers());
//
//		CategoriesDBDAO ctgDbdao = new CategoriesDBDAO();
//
//		try {
//			ctgDbdao.addCategory(Category.Electricity);
//			ctgDbdao.addCategory(Category.Food);
//			ctgDbdao.addCategory(Category.Restaurant);
//			ctgDbdao.addCategory(Category.Vacation);
//		} catch (duplicateCategoryException e) {
//			System.out.println(e.getMessage());
//		}
//
//		
//		
//
//		copDbdao.addCoupon(cp1);
//		copDbdao.addCoupon(cp2);
//		copDbdao.addCoupon(cp3);
//		copDbdao.addCoupon(cp4);
//		
//		copDbdao.addCouponPurchase(cs1.getId(), cp1.getId());
//		copDbdao.addCouponPurchase(cs2.getId(), cp2.getId());
//		copDbdao.addCouponPurchase(cs1.getId(), cp3.getId());
//		copDbdao.addCouponPurchase(cs1.getId(), cp4.getId());
//		
//		
//		af.deleteCompany(2);
//		//cmpDbdao.deleteCompany(2);
//		//copDbdao.deleteAllCouponsByCompany(2);
//		//copDbdao.deleteCoupon(3);
//		System.out.println("all companies");
//		printArraylist(cmpDbdao.getAllCompanies());
//		System.out.println("all coupons");
//		printArraylist(copDbdao.getAllCoupons());
//		
//		copDbdao.getOneCoupon(2);
//
//		
//		
////		System.out.println();
////		System.out.println("all coupons belong to customer number#1");
////		cs1.setCoupons(copDbdao.getArrayListCouponsPerCustomer(1));
////		
////		printArraylist(cs1.getCoupons());
////		
////		System.out.println("Customer Number #1-> "+cs1);
////		
//////		Thread t1 = new CouponExpirationDailyJob();
//////		t1.start();
////
		ConnectionPool.getInstance().closeAllConnection();

		System.out.println("END");
	}
}
