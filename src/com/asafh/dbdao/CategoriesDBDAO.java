package com.asafh.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asafh.beans.Category;

import com.asafh.dao.CategoriesDAO;
import com.asafh.utils.duplicateCategoryException;

public class CategoriesDBDAO implements CategoriesDAO {
	public static int countCategory;
	

	@Override
	public boolean isCtegoryExist(Category category) {
		Connection connection = null;
		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}

			String sql = "SELECT * FROM `coupons_system`.`categories` " + "WHERE name=?";
			PreparedStatement statement1 = connection.prepareStatement(sql);
			statement1.setString(1, category.name());

			ResultSet resultSet = statement1.executeQuery();

			boolean isCategoryExists = false;

			if (resultSet.next()) {
				isCategoryExists = true;

			}

			return isCategoryExists;
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

		return false;

	}

	

	@Override
	public void addCategory(Category category) throws duplicateCategoryException {
		if (!isCtegoryExist(category)) {

			Connection connection = null;
			try {
				try {
					connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();

				} catch (InterruptedException e) {

					System.out.println(e.getMessage());
				}

				String sql = "INSERT INTO `coupons_system`.`categories` (name) VALUES (?)";

				PreparedStatement statement = connection.prepareStatement(sql);

				statement.setString(1, category.toString());

				statement.executeUpdate();

				countCategory++;

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
				connection = null;
			}
		} else {
			throw new com.asafh.utils.duplicateCategoryException();
		}

	}

	public int getIdCategory(Category category) {
		int idCategory = 0;
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`categories` WHERE name=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, category.toString());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				idCategory = resultSet.getInt(1);

				return idCategory;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return 0;

	}

	public  Category getCategory(int categoryID) {
		String category2 = null;
		Connection connection = null;
		try {

			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			String sql = "SELECT * FROM `coupons_system`.`categories` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, categoryID);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				category2=resultSet.getString(2);				
				
				return Category.valueOf(category2);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;

	}


	
	@Override
	public void deleteCategory(int categoryID) {
		Connection connection = null;

		try {
			try {
				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String sql = "DELETE FROM `coupons_system`.`categories` WHERE id=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, categoryID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
			connection = null;

		}
	}

	
}
