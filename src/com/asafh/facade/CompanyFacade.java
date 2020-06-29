package com.asafh.facade;

import java.util.ArrayList;
import java.util.List;

import com.asafh.beans.Category;
import com.asafh.beans.Company;
import com.asafh.beans.Coupon;
import com.asafh.utils.CouponException;

public class CompanyFacade extends ClientFacade {

	private int companyID;

	public CompanyFacade() {
		super();

	}

	@Override
	public boolean login(String email, String password) {

		return companiesDAO.isCompanyExists(email, password);
	}

	public CompanyFacade(int companyID) {
		this.companyID = companyID;
	}

	public void addCoupon(Coupon coupon) throws CouponException {
		List<Coupon> coupons = couponsDAO.getAllCoupons();

		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == coupon.getCompanyID() && coup.getTitle().equals(coupon.getTitle())) {
				throw new CouponException("sorry... you can't add coupon with the same title");
			}
		}

		couponsDAO.addCoupon(coupon);
	}

	public void updateCoupon(int couponID, Coupon coupon) throws CouponException {// (int couyponID ) -> my
		Coupon coup=couponsDAO.getOneCoupon(couponID);																					// addition
		if (coupon.getId() != couponID || coupon.getCompanyID() != couponsDAO.getOneCoupon(couponID).getCompanyID()) {
			throw new CouponException("sorry... you can't update the coupon_id or company");
		}
		if(coupon.getCategory()!=null) {
			coup.setCategory(coupon.getCategory());
		}
		if(coupon.getTitle()!=null) {
			coup.setTitle(coupon.getTitle());
		}
		if(coup.getDescription()!=null) {
			coup.setDestraction(coupon.getDescription());
		}
		if(coupon.getStartDate()!=null) {
			coup.setStartDate(coupon.getStartDate());
		}
		if(coupon.getEndDate()!=null) {
			coup.setEndDate(coupon.getEndDate());
		}
		if(coupon.getAmount()>0) {
			coup.setAmount(coupon.getAmount());
		}
		if(coupon.getPrice()>=0) {
			coup.setPrice(coupon.getPrice());
		}
		couponsDAO.updateCoupon(coup);

	}

	public void deleteCoupon(int couponID) {
		couponsDAO.deleteCoupon(couponID);
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = couponsDAO.getAllCoupons();
		List<Coupon> companyCoupons = new ArrayList<Coupon>();
		if (coupons != null) {

			for (Coupon coupon : coupons) {
				if (coupon.getCompanyID() == companyID) {
					companyCoupons.add(coupon);
				}
			}
			return companyCoupons;
		}
		return null;
	}

	public List<Coupon> getCompanyCouponsByCategory(Category category) {
		List<Coupon> coupons = couponsDAO.getAllCoupons();
		List<Coupon> companyCouponsByCategory = new ArrayList<Coupon>();
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (coupon.getCompanyID() == companyID && coupon.getCategory().equals(category) ) {
					companyCouponsByCategory.add(coupon);
				}
			}
			return companyCouponsByCategory;
		}
		return null;
	}
	

	public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
		List<Coupon> coupons = couponsDAO.getAllCoupons();
		List<Coupon> companyCouponsByCategory = new ArrayList<Coupon>();
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (coupon.getCompanyID() == companyID && coupon.getPrice()<=(maxPrice) ) {
					companyCouponsByCategory.add(coupon);
				}
			}
			return companyCouponsByCategory;
		}
		return null;
	}

	public Company getCompanyDetails() {
		Company company= companiesDAO.getOneCompany(companyID);
		List<Coupon> coupons = getCompanyCoupons();
		company.setCoupons(coupons);
		return company;
	}

}
