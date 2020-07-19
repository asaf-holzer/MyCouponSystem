package com.asafh.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asafh.beans.Customer;
import com.asafh.dao.CustomersDAO;

public class CustomersDBDAO implements CustomersDAO {

	public boolean isCustomerExists(String email, String password) {
		Connection connection = null;
		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}

			String sql = "SELECT * FROM `coupons_system`.`customers`WHERE email=? AND password=?";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setString(1, email);
			statement1.setString(2, password);
			ResultSet resultSet = statement1.executeQuery();

			boolean iscustomerExists = false;

			if (resultSet.next()) {
				iscustomerExists = true;

			}

			return iscustomerExists;
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

		return false;

	}

	public boolean isCustomerExistsByEmail(String email) {// my addition for admin facade
		Connection connection = null;

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`customers`WHERE email=? ";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setString(1, email);

			ResultSet resultSet = statement1.executeQuery();

			boolean iscustomerExists = false;

			if (resultSet.next()) {
				iscustomerExists = true;

			}

			return iscustomerExists;
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

		return false;

	}

	public boolean isCustomerExistsByCustomerID(int customerID) {// my addition for exception
		Connection connection = null;

		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupons_system`.`customers` WHERE id=? ";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setInt(1, customerID);

			ResultSet resultSet = statement1.executeQuery();

			boolean iscustomerExists = false;

			if (resultSet.next()) {
				iscustomerExists = true;
			}

			return iscustomerExists;
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return false;
	}

	public void addCustomer(Customer customer) {

		Connection connection = null;
		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}

			String sql = "INSERT INTO `coupons_system`.`customers` (first_name, last_name, email,password) VALUES (?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());

			statement.executeUpdate();

			String sql1 = "SELECT * FROM `coupons_system`.`customers` WHERE email=? AND password=?";

			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setString(1, customer.getEmail());
			statement1.setString(2, customer.getPassword());
			ResultSet resultSet = statement1.executeQuery();

			if (resultSet.next()) {
				customer.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void updateCustomer(Customer customer) {

		Connection connection = null;

		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String sql = "UPDATE `coupons_system`.`customers` SET first_name=?,last_name=?,email=?, password=? WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	@Override
	public void deleteCustomer(int customerID) {
		Connection connection = null;

		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String sql = "DELETE FROM `coupons_system`.`customers` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`customers`";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);

				customers.add(new Customer(id, firstname, lastname, email, password));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customers;

	}

	@Override
	public Customer getOneCustomer(int customerID) {
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`customers` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);

				return new Customer(id, firstname, lastname, email, password);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}
	
	//my addition for customer facade
	public Customer getOneCustomerByEmailAndPassword(String email, String password) {
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`customers` WHERE email=? AND password=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String emailResult = resultSet.getString(4);
				String passwordResult = resultSet.getString(5);

				return new Customer(id, firstname, lastname, emailResult, passwordResult);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}

}
