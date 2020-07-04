package com.asafh.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.asafh.beans.Category;
import com.asafh.beans.Coupon;
import com.asafh.beans.Customer;
import com.asafh.utils.CouponException;
import com.asafh.utils.CustomerException;
import com.asafh.utils.TicketsSoldOutException;

public class CustomerFacade extends ClientFacade{
	
	private int customerID;

	public CustomerFacade() {
		super();
	}

	public boolean login(String email, String password) {
		return customersDAO.isCustomerExists(email, password);
	}
	
	public void purchaseCoupon(Coupon coupon) throws CustomerException, CouponException {
		List<Coupon> couponsPurchase= couponsDAO.getArrayListCouponsByCustomer(customerID);
		for (Coupon coup : couponsPurchase) {
			if(coupon.getId()==coup.getId()) {
				throw new CustomerException("you bought this coupon allredy...");
			}
		}
		if (coupon.getAmount()<=0) {
			throw new CouponException("the coupon is out of stock");
		}else if (coupon.getEndDate().before(new Date())) {
			throw new CouponException("the coupon has expired");
		}else {
			try {
				couponsDAO.addCouponPurchase(customerID, coupon.getId());
			} catch (TicketsSoldOutException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
	public List<Coupon> getCustomerCoupons(){
		
		return couponsDAO.getArrayListCouponsByCustomer(customerID);
	}
	
	public List<Coupon> getCustomerCouponsByCategory(Category category){
		List<Coupon> coupons= couponsDAO.getArrayListCouponsByCustomer(customerID);
		List<Coupon> couponsByCategory= new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
			couponsByCategory.add(coupon);	
			}
		}
		return couponsByCategory;
	}
	
	public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice){
		List<Coupon> coupons= couponsDAO.getArrayListCouponsByCustomer(customerID);
		List<Coupon> couponsByMaxPrice= new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice()<=maxPrice) {
				couponsByMaxPrice.add(coupon);	
			}
		}
		return couponsByMaxPrice;
	
	}
	
	public Customer getCustomerDetails() {
		Customer customer= customersDAO.getOneCustomer(customerID);
		List<Coupon> coupons= getCustomerCoupons();
		customer.setCoupons(coupons);
		return customer;
	}
	
}
