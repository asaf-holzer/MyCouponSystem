import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.RowFilter.ComparisonType;

import com.asafh.beans.Category;
import com.asafh.beans.Company;
import com.asafh.beans.Coupon;
import com.asafh.beans.Customer;
import com.asafh.creatTables.DBManeger;
import com.asafh.dbdao.CategoriesDBDAO;
import com.asafh.dbdao.CompaniesDBDAO;
import com.asafh.dbdao.CouponsDBDAO;
import com.asafh.dbdao.CustomerDBDAO;
import com.asafh.utils.CouponExpirationDailyJob;
import com.asafh.utils.DateWrongException;
import com.asafh.utils.JobDeleteCouponRunnable;
import com.asafh.utils.TicketsSoldOutException;
import com.asafh.utils.duplicateCategoryException;
import com.asafh.utils.duplicateCompanyException;
import com.asafh.utils.duplicateCustomerException;

public class Test {


	public static Date realDate(int year, int month, int day) {
		Date d1 = new Date(year - 1900, month - 1, day + 1);
		return d1;
	}

	public static java.sql.Date replaseDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static void printArraylist(List arr) {
		for (Object object : arr) {
			System.out.println(object);
		}
		System.out.println("**********************");
	}

	public static void main(String[] args) throws InterruptedException, TicketsSoldOutException, DateWrongException {

		System.out.println("START");
		DBManeger.createAllTables();
		Company cmp1 = new Company("coca cola", "cola@coca.com", "1234");
		Company cmp2 = new Company("pepsy", "pepsy@cola.com", "2345");
		Company cmp3 = new Company("Alfredo", "alf@redo.com", "3456");
		Company cmp4 = new Company("Sony", "sony@sony.com", "4567");
		
		

		CompaniesDBDAO cmpDbdao = new CompaniesDBDAO();
		try {
			cmpDbdao.addCompany(cmp1);
			cmpDbdao.addCompany(cmp2);
			cmpDbdao.addCompany(cmp3);
			cmpDbdao.addCompany(cmp4);
			
			System.out.println("company number #2 before update is: "+cmpDbdao.getOneCompany(2));
			System.out.println("***************************");
			
			
			cmp2.setName("pepsy-cola");
			
			cmpDbdao.updateCompany(2, cmp2);
			
			//cmpDbdao.deleteCompany(3);
			System.out.println("all of the companies");
			printArraylist(cmpDbdao.getAllCompanies());
			System.out.println("company number #4");
			System.out.println(cmpDbdao.getOneCompany(4));
			
			System.out.println("##################################");
			
			

		} catch (duplicateCompanyException e) {
			System.out.println(e.getMessage());
		}

		Customer cs1 = new Customer("Asaf", "Holzer", "asafh101@gmail.com", "12345");
		Customer cs2 = new Customer("David", "Hamelech", "david@gmail.com", "23456");
		Customer cs3 = new Customer("Moshe", "Cohen", "moshe@gmail.com", "34567");
		Customer cs4 = new Customer("Rina", "Dovev", "rina@gmail.com", "45678");
		Customer cs5 = new Customer("sara", "Rozen", "sara@gmail.com", "56789");
		Customer cs6 = new Customer("Israel", "Levi", "levi@gmail.com", "67890");

		
		
		CustomerDBDAO cstDbdao = new CustomerDBDAO();
		try {
			cstDbdao.addCustomer(cs1);
			cstDbdao.addCustomer(cs2);
			cstDbdao.addCustomer(cs3);
			cstDbdao.addCustomer(cs4);
			cstDbdao.addCustomer(cs5);
			cstDbdao.addCustomer(cs6);
			
			System.out.println("customer number #3 before update is: "+cstDbdao.getOneCustomer(3));
			System.out.println("*******************************");
			
			cs3.setEmail("moshecohen@gmail.com");
		
			cstDbdao.updateCustomer(cs3);
			//cstDbdao.deleteCustomer(5);
			System.out.println("all customers");
			printArraylist(cstDbdao.getAllCustomers());
			

		} catch (duplicateCustomerException e) {
			System.out.println(e.getMessage());
		}

		CategoriesDBDAO ctgDbdao = new CategoriesDBDAO();

		try {
			ctgDbdao.addCategory(Category.Electricity);
			ctgDbdao.addCategory(Category.Food);
			ctgDbdao.addCategory(Category.Restaurant);
			ctgDbdao.addCategory(Category.Vacation);
		} catch (duplicateCategoryException e) {
			System.out.println(e.getMessage());
		}

		Coupon cp1 = new Coupon(cmp1.getId(), Category.Food, "1+1", "buy one get one", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 100, 3.0, "https://bla bla... ");
		Coupon cp2 = new Coupon(cmp2.getId(), Category.Food, "1+2", "buy one get two", realDate(2020, 4, 25),
				realDate(2020, 5, 7), 50, 3.5, "https://bla bla... ");
		Coupon cp3 = new Coupon(cmp3.getId(), Category.Restaurant, "2+1", "the therd free", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 70, 2.0, "https://bla bla... ");
		Coupon cp4 = new Coupon(cmp4.getId(), Category.Electricity, "hat", "buy one get hat", realDate(2020, 6, 25),
				realDate(2020, 7, 7), 85, 4.5, "https://bla bla... ");

		CouponsDBDAO copDbdao = new CouponsDBDAO();

		copDbdao.addCoupon(cp1);
		copDbdao.addCoupon(cp2);
		copDbdao.addCoupon(cp3);
		copDbdao.addCoupon(cp4);
		
		//copDbdao.deleteCoupon(3);
		System.out.println("all coupons");
		printArraylist(copDbdao.getAllCoupons());
		
		copDbdao.getOneCoupon(2);

		copDbdao.addCouponPurchase(cs1.getId(), cp1.getId());
		copDbdao.addCouponPurchase(cs2.getId(), cp2.getId());
		copDbdao.addCouponPurchase(cs1.getId(), cp3.getId());
		copDbdao.addCouponPurchase(cs1.getId(), cp4.getId());
		
		
		System.out.println();
		System.out.println("all coupons belong to customer number#1");
		cs1.setCoupons(copDbdao.getArrayListCouponsPerCustomer(1));
		
		printArraylist(cs1.getCoupons());
		
		System.out.println("Customer Number #1-> "+cs1);
		
//		Thread t1 = new CouponExpirationDailyJob();
//		t1.start();

		com.asafh.utils.ConnectionPool.getInstance().closeAllConnection();

		System.out.println("END");

	}

}
