package com.asafh.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asafh.beans.Company;


public class CompaniesDBDAO implements com.asafh.dao.CompaniesDAO {

	public boolean isCompanyExists(String email, String password) {
		Connection connection = null;
		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}

			String sql = "SELECT * FROM `coupons_system`.`companies` "
					+ "WHERE email=? AND password=?";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setString(1, email);
			statement1.setString(2, password);
			ResultSet resultSet = statement1.executeQuery();


			boolean isCompanyExists = false;
			
				if (resultSet.next()) {
					isCompanyExists = true;
					
				}
			

			return isCompanyExists;
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

		return false;

	}

	public void addCompany(Company company)  {		

		Connection connection = null;
		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}

			String sql = "INSERT INTO `coupons_system`.`companies` (name,email,password) VALUES (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());

			statement.executeUpdate();

			String sql1 = "SELECT * FROM `coupons_system`.`companies` WHERE name= ? AND email=? AND password=?  ";

			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setString(1, company.getName());
			statement1.setString(2, company.getEmail());
			statement1.setString(3, company.getPassword());
			ResultSet resultSet = statement1.executeQuery();

			if (resultSet.next()) {
				company.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

}

	public void updateCompany(int companyID, Company company) {
		Connection connection = null;

		
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			
			String sql = "UPDATE `coupons_system`.`companies` SET name=?,email=?, password=? WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	public void deleteCompany(int companyID) {
		Connection connection = null;

		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String sql = "DELETE FROM `coupons_system`.`companies` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`companies`";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);

				companies.add(new Company(id, name, email, password));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return companies;
	}

	public Company getOneCompany(int companyID) {
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`companies` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();

	
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String email = resultSet.getString(3);
					String password = resultSet.getString(4);

					return new Company(id, name, email, password);
				}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}
	
	//additions
	public Company getOneCompanyByEmailAndPassword(String email, String password) {
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`companies` WHERE email=? AND password=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

	
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String emailResult = resultSet.getString(3);
					String passwordResult = resultSet.getString(4);

					return new Company(id, name, emailResult, passwordResult);
				}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}
	
	
	public boolean isCompanyExistsByNameOrEmail(String name, String email) {//my addition for admin facade
		Connection connection = null;
		
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();


			String sql = "SELECT * FROM `coupons_system`.`companies` "
					+ "WHERE name=? OR email=?";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setString(1, name);
			statement1.setString(2, email);
			ResultSet resultSet = statement1.executeQuery();


			boolean isCompanyExists = false;
			
				if (resultSet.next()) {
					isCompanyExists = true;
					
				}
			

			return isCompanyExists;
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

		return false;

	}

	public boolean isCompanyExistsByCompanyID(int companyID) {// my addition for exeption
		Connection connection = null;
		
		try {
			connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();


		String sql = "SELECT * FROM `coupons_system`.`companies` "
				+ "WHERE id=?";
		PreparedStatement statement1 = connection.prepareStatement(sql);
		statement1.setInt(1, companyID);
		ResultSet resultSet = statement1.executeQuery();

		boolean isCompanyExists = false;
		
			if (resultSet.next()) {
				isCompanyExists = true;				
			}
		
		return isCompanyExists;
	} catch (Exception e) {
		System.out.println(e.getMessage());

	} finally {
		com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
		connection = null;
	}

	return false;

	}
	
	

}
