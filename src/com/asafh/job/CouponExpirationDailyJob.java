package com.asafh.job;

import java.util.Date;
import java.util.List;

import com.asafh.beans.Coupon;
import com.asafh.dao.CouponsDAO;
import com.asafh.dbdao.CouponsDBDAO;

public class CouponExpirationDailyJob extends Thread {
	private CouponsDAO couponsDAO;

	private boolean quit = false;

	public CouponExpirationDailyJob() {
		this.couponsDAO = new CouponsDBDAO();
	}

	@Override
	public void run() {

		while (!quit) {
			List<Coupon> coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(new Date())) {
					
					couponsDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(1 * 60 * 1000);
			} catch (Exception e) {
				return;
			}
		}
	}

	public void stopJob() {
		this.quit = true;

	}

}
