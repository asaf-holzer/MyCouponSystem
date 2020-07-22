//package com.asafh.utils;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//
//import com.asafh.dao.CouponsDAO;
//import com.asafh.dbdao.CouponsDBDAO;
//
//public class CouponExpirationDailyJob extends Thread {
//
//	public CouponsDAO couponsDAO =new CouponsDBDAO() ;
//	public static  java.sql.Date replaseDate(Date date) {
//		return new java.sql.Date(date.getTime());
//	}
//	
//	@Override
//	public void run() {
//
//		try {
//			Thread.sleep(1000*60*2);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		Connection connection = null;
//		try {
//
//			try {
//				connection = com.asafh.utils.ConnectionPool.getInstance().getConnection();
//			} catch (InterruptedException e) {
//
//				System.out.println(e.getMessage());
//			}
//			String sql = "SELECT * FROM `coupons_system`.`coupons` WHERE end_date<? ";
//			// replaseDate(new Date())
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setDate(1, replaseDate(new Date()));
//			ResultSet resultSet = statement.executeQuery();
//
//			while (resultSet.next()) {
//				int couponID = resultSet.getInt(1);
////				String sql1 = "DELETE FROM `coupons_system`.`customers_vs_coupons` WHERE coupon_id=? ";
////
////				PreparedStatement statement1 = connection.prepareStatement(sql1);
////				statement1.setInt(1, couponID);
////				statement1.executeUpdate();
////
////				String sql2 = "DELETE FROM `coupons_system`.`coupons` WHERE id=? ";
////
////				PreparedStatement statement2 = connection.prepareStatement(sql2);
////				statement2.setInt(1, couponID);
////				statement2.executeUpdate();
//				couponsDAO.deleteCoupon(couponID);
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		} finally {
//			com.asafh.utils.ConnectionPool.getInstance().returnConnection(connection);
//			connection = null;
//		}
//
//		super.run();
//	}
//
//}
