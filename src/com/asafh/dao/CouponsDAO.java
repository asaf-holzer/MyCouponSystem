package com.asafh.dao;

import java.util.List;

import com.asafh.beans.Coupon;
import com.asafh.utils.TicketsSoldOutException;

public interface CouponsDAO {

	void addCoupon(Coupon coupon);
	void updateCoupon(Coupon coupon);
	void deleteCoupon(int couponID);
	List<Coupon> getAllCoupons();
	Coupon getOneCoupon(int couponID);
	void addCouponPurchase(int customerID, int couponID) throws TicketsSoldOutException;
	void deleteCouponPurchase(int customerID, int couponID);
	void deleteCouponPurchaseByCouponID(int couponID);//my addition
	void deleteAllCouponsByCompany(int companyID);// my addition
	void deleteCouponPurchaseByCustomerID(int customerID);//my addition
	List<Coupon> getArrayListCouponsByCustomer(int customerID) ;
	List<Coupon> getArrayListCouponsByCompany(int companyID) ; 
}
