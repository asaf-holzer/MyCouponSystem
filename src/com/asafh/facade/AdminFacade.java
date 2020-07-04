package com.asafh.facade;

import java.util.List;

import com.asafh.beans.Company;
import com.asafh.beans.Customer;
import com.asafh.utils.CompanyException;
import com.asafh.utils.CustomerException;
import com.asafh.utils.updateCompanyException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws CompanyException {

		if (companiesDAO.isCompanyExistsByNameOrEmail(company.getName(), company.getEmail())) {
			throw new CompanyException("the name or email is allredy exist");
		}
		companiesDAO.addCompany(company);

	}
// another option to addCompany method	
//	
//	public void addCompany(Company company) throws duplicateCompanyException {
//		List<Company> companies = companiesDAO.getAllCompanies();
//		for (Company comp : companies) {
//			if (company.getName().equals(comp.getName())) {
//				throw new duplicateCompanyException("there is alrady company with this name...");
//			} else if (company.getEmail().equals(comp.getEmail())) {
//				throw new duplicateCompanyException("there is alrady company with this email...");
//			}
//		}
//		companiesDAO.addCompany(company);
//
//	}

	public void updateCompany(int companyID, Company company) throws updateCompanyException {// (int companyID) my
																								// addition
		Company comp = companiesDAO.getOneCompany(companyID);
		if (company.getId() != comp.getId() || !company.getName().equals(comp.getName())) {
			throw new updateCompanyException("you cant update id or name");
		}
		if (company.getEmail() != null) {
			comp.setEmail(company.getEmail());
		}
		if (company.getPassword() != null) {
			comp.setPassword(company.getPassword());
		}
		companiesDAO.updateCompany(companyID, comp);
	}

	public void deleteCompany(int companyID) throws CompanyException {
		if (!companiesDAO.isCompanyExistsByCompanyID(companyID)) {
			throw new CompanyException("the company is not exist by this id");
		}
		couponsDAO.deleteAllCouponsByCompany(companyID);
		companiesDAO.deleteCompany(companyID);
	}

	public List<Company> getAllCompanies() {
		List<Company> Companies = companiesDAO.getAllCompanies();
		for (Company company : Companies) {
			company.setCoupons(couponsDAO.getArrayListCouponsByCompany(company.getId()));
		}
		return Companies;

	}

	public Company getOneCompany(int companyID) throws CompanyException {
		if (!companiesDAO.isCompanyExistsByCompanyID(companyID)) {
			throw new CompanyException("the company is not exist by this id");
		}
		Company comp = companiesDAO.getOneCompany(companyID);
		comp.setCoupons(couponsDAO.getArrayListCouponsByCompany(companyID));

		return comp;

	}

	public void addCustomer(Customer customer) throws CustomerException {
		if (customersDAO.isCustomerExistsByEmail(customer.getEmail())) {
			throw new CustomerException("the email is already exist");
		}
		customersDAO.addCustomer(customer);

	}

	public void updateCustomer(int customerID, Customer customer) throws CustomerException, CompanyException {// (int
																												// customerID)
																												// my
																												// addition

		Customer cs = customersDAO.getOneCustomer(customerID);
		if (customer.getId() != cs.getId()) {
			throw new CustomerException("you cant change the id number");
		} else if (!customersDAO.isCustomerExistsByCustomerID(customerID)) {
			throw new CompanyException("the customer is not exist by this id");
		}
		customersDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerID) {
		couponsDAO.deleteCouponPurchaseByCustomerID(customerID);
		customersDAO.deleteCustomer(customerID);
	}

	public List<Customer> getAllCustomers() {
		return customersDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) throws CompanyException {
		if (!customersDAO.isCustomerExistsByCustomerID(customerID)) {
			throw new CompanyException("the customer is not exist by this id");
		}
		Customer customer = customersDAO.getOneCustomer(customerID);
		customer.setCoupons(couponsDAO.getArrayListCouponsByCustomer(customerID));
		return customer;
	}

}
