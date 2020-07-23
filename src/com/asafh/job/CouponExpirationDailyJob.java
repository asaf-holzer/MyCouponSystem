package com.asafh.job;

import java.util.Date;
import java.util.List;

import com.asafh.beans.Coupon;
import com.asafh.dao.CouponsDAO;
import com.asafh.dbdao.CouponsDBDAO;
import com.asafh.utils.BeautyTable;

public class CouponExpirationDailyJob extends Thread {
	private CouponsDAO couponsDAO;

	private boolean quit = false;

	public CouponExpirationDailyJob() {
		this.couponsDAO = new CouponsDBDAO();
	}

	@Override
	public void run() {

		while (!quit) {
			try {
				Thread.sleep(2 * 60 * 1000);
			} catch (Exception e) {
				return;
			}
			List<Coupon> coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(new Date())) {
					
					couponsDAO.deleteCoupon(coupon.getId());
				}
			}
			System.out.println("dailyJob in action...");
			BeautyTable.tableWithLinesCoupons(couponsDAO.getAllCoupons());
		}
	}

	public void stopJob() {
		this.quit = true;

	}

}
