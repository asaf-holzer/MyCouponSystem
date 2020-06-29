package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Company;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String password);
	boolean isCompanyExistsByNameOrEmail(String name, String email) ;//my addition
	void addCompany(Company company) ;
	void updateCompany(int copmpamyID, Company company);
	void deleteCompany(int companyID);
	List<Company> getAllCompanies();
	Company getOneCompany(int companyID);
	
	
	
}
