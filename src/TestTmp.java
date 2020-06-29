import java.util.Date;
import java.util.List;

import com.asafh.Login.ClientType;
import com.asafh.Login.LoginManager;
import com.asafh.beans.Category;
import com.asafh.beans.Company;
import com.asafh.beans.Coupon;
import com.asafh.creatTables.DBManeger;
import com.asafh.dbdao.CategoriesDBDAO;
import com.asafh.dbdao.CompaniesDBDAO;
import com.asafh.dbdao.CouponsDBDAO;
import com.asafh.facade.ClientFacade;
import com.asafh.utils.ConnectionPool;
import com.asafh.utils.DateWrongException;
import com.asafh.utils.duplicateCategoryException;

public class TestTmp {
	public static Date realDate(int year, int month, int day) {
		Date d1 = new Date(year - 1900, month - 1, day + 1);
		return d1;
	}

	public static void printArraylist(List arr) {
		for (Object object : arr) {
			System.out.println(object);
		}
		System.out.println("**********************");
	}

	public static void main(String[] args) throws InterruptedException, DateWrongException, duplicateCategoryException {

		System.out.println("START");
		DBManeger.dropAndCreateAllTables();;
		
		System.out.println("END");
	}

}
