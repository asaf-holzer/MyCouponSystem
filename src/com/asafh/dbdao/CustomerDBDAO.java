package com.asafh.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.asafh.beans.Customer;
import com.asafh.dao.CustomerDAO;
import com.asafh.utils.duplicateCustomerException;

public class CustomerDBDAO implements CustomerDAO {

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

	public void addCustomer(Customer customer) throws duplicateCustomerException {

		if (isCustomerExists(customer.getEmail(), customer.getPassword()) == false) {

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
		} else {
			throw new com.asafh.utils.duplicateCustomerException();
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
			statement.setString(3, customer.getPassword());
			statement.setInt(4, customer.getId());
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
				String lastname = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);

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

}
