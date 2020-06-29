package com.asafh.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asafh.beans.Category;
import com.asafh.beans.Coupon;
import com.asafh.dao.CouponsDAO;
import com.asafh.utils.TicketsSoldOutException;

public class CouponsDBDAO implements CouponsDAO {
	public Date replaseDate(java.util.Date date) {
		return new Date(date.getTime());
	}

	private static Connection connection = null;
	public static CategoriesDBDAO ctgDBDAO = new CategoriesDBDAO();

	@Override
	public void addCoupon(Coupon coupon) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "INSERT INTO `coupons_system`.`coupons` (company_id,category_id,"
					+ "title,description,start_date, end_date,amount,price,image)" + " VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, coupon.getCompanyID());
			statement.setInt(2, ctgDBDAO.getIdCategory(coupon.getCategory()));
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (replaseDate(coupon.getStartDate())));
			statement.setDate(6, (replaseDate(coupon.getEndDate())));
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());

			statement.executeUpdate();

			String sql1 = "SELECT * FROM `coupons_system`.`coupons` WHERE company_id=? AND title=?";

			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, coupon.getCompanyID());
			statement1.setString(2, coupon.getTitle());

			ResultSet resultSet = statement1.executeQuery();

			if (resultSet.next()) {
				coupon.setId(resultSet.getInt(1));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void updateCoupon(Coupon coupon) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "UPDATE `coupons_system`.`coupons` SET category_id=?,title=?,"
					+ " description=?, start_date=?,end_date=?, amount=?,price=?, image=? WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, ctgDBDAO.getIdCategory(coupon.getCategory()));
			statement.setString(2, coupon.getTitle());
			statement.setString(3, coupon.getDescription());
			statement.setDate(4, replaseDate(coupon.getStartDate()));
			statement.setDate(5, replaseDate(coupon.getEndDate()));
			statement.setInt(6, coupon.getAmount());
			statement.setDouble(7, coupon.getPrice());
			statement.setString(8, coupon.getImage());
			statement.setInt(9, coupon.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	@Override
	public void deleteCoupon(int couponID) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE coupon_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			statement.executeUpdate();

			String sql1 = "DELETE FROM `coupons_system`.`coupons` WHERE id=? ";

			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, couponID);
			statement1.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}

	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`coupons`";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int companyID = resultSet.getInt(2);
				Category category = ctgDBDAO.getCategory(resultSet.getInt(3));
				String title = resultSet.getString(4);
				String description = resultSet.getString(5);
				Date startDate = resultSet.getDate(6);
				Date endDate = resultSet.getDate(7);
				int amount = resultSet.getInt(8);
				double price = resultSet.getDouble(9);
				String image = resultSet.getString(10);

				coupons.add(new Coupon(id, companyID, category, title, description, startDate, endDate, amount, price,
						image));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;

	}

	@Override
	public Coupon getOneCoupon(int couponID) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`coupons` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				int companyID = resultSet.getInt(2);
				Category category = ctgDBDAO.getCategory(resultSet.getInt(3));
				String title = resultSet.getString(4);
				String description = resultSet.getString(5);
				Date startDate = resultSet.getDate(6);
				Date endDate = resultSet.getDate(7);
				int amount = resultSet.getInt(8);
				double price = resultSet.getDouble(9);
				String image = resultSet.getString(10);

				return new Coupon(id, companyID, category, title, description, startDate, endDate, amount, price,
						image);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}



	@Override
	public void addCouponPurchase(int customerID, int couponID) throws TicketsSoldOutException {
		
		Coupon tmpCoupon = getOneCoupon(couponID);
		if (tmpCoupon.getAmount() > 0) {

		try {

			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			
				String sql = "UPDATE `coupons_system`.`coupons` SET  amount=? WHERE id=? ";

				PreparedStatement statement = connection.prepareStatement(sql);

				statement.setInt(1, (tmpCoupon.getAmount()) - 1);

				statement.setInt(2, couponID);

				statement.executeUpdate();

				String sql1 = "INSERT INTO `coupons_system`.`customers_vs_coupons` (customer_id,coupon_id) VALUES (?,?)";

				PreparedStatement statement1 = connection.prepareStatement(sql1);

				statement1.setInt(1, customerID);
				statement1.setInt(2, couponID);
				statement1.executeUpdate();

			
		} catch (Exception e) {
			System.out.println("the problem is :" + e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	} else {
				throw new com.asafh.utils.TicketsSoldOutException();
			}
	}

	@Override
	public void deleteCouponPurchaseByCouponID(int couponID) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE coupon_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	@Override
	public void deleteCouponPurchaseByCustomerID(int customerID) {// my addition

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE customer_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE customer_id=? AND coupon_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	public List<Coupon> getArrayListCouponsPerCustomer(int customerID) {
		List<Coupon> arr = new ArrayList<Coupon>();

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`customers_vs_coupons` WHERE customer_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				String sql1 = "SELECT * FROM `coupons_system`.`coupons` WHERE id=? ";

				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(1, resultSet.getInt(2));
				ResultSet resultSet1 = statement1.executeQuery();

				if (resultSet1.next()) {
					int id = resultSet1.getInt(1);
					int companyID = resultSet1.getInt(2);
					Category category = ctgDBDAO.getCategory(resultSet1.getInt(3));
					String title = resultSet1.getString(4);
					String description = resultSet1.getString(5);
					Date startDate = resultSet1.getDate(6);
					Date endDate = resultSet1.getDate(7);
					int amount = resultSet1.getInt(8);
					double price = resultSet1.getDouble(9);
					String image = resultSet1.getString(10);

					arr.add(new Coupon(id, companyID, category, title, description, startDate, endDate, amount, price,
							image));

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
		return arr;

	}

	// delete all coupons by company (my addition)
	public void deleteAllCouponsByCompany(int companyID) {

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`coupons` WHERE company_id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				deleteCoupon(resultSet.getInt(1));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

}
