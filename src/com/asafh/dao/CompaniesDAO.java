package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Company;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String password);
	void addCompany(Company company) ;
	void updateCompany(int copmpamyID, Company company);
	void deleteCompany(int companyID);
	List<Company> getAllCompanies();
	Company getOneCompany(int companyID);
	//additions
	boolean isCompanyExistsByNameOrEmail(String name, String email) ;//my addition for admin facade
	boolean isCompanyExistsByCompanyID(int companyID);// my addition for exeption
	Company getOneCompanyByEmailAndPassword(String email, String password);//my addition for company facade
	
	
	
}
