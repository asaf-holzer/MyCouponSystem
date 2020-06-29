package com.asafh.facade;

import com.asafh.dao.CompaniesDAO;
import com.asafh.dao.CouponsDAO;
import com.asafh.dao.CustomersDAO;
import com.asafh.dbdao.CompaniesDBDAO;
import com.asafh.dbdao.CouponsDBDAO;
import com.asafh.dbdao.CustomersDBDAO;

public abstract class ClientFacade {
	
	protected CompaniesDAO companiesDAO=null;
	protected CustomersDAO customersDAO=null;
	protected CouponsDAO couponsDAO=null;
	
	public ClientFacade() {
		companiesDAO=new CompaniesDBDAO();
		customersDAO=new CustomersDBDAO();
		couponsDAO= new CouponsDBDAO();
	}
	
	public abstract boolean login(String email, String password); 

}
