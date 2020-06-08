package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Company;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String password);
	void addCompany(Company company)throws Exception ;
	void updateCompany(int copmpamyID, Company company);
	void deleteCompany(int companyID);
	List<Company> getAllCompanies();
	Company getOneCompany(int companyID);
	
	
	
}
