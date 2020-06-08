package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Customer;
import com.asafh.utils.duplicateCustomerException;

public interface CustomerDAO {

	boolean isCustomerExists(String email, String password);
	void addCustomer(Customer customer) throws duplicateCustomerException;
	void updateCustomer(Customer Customer);
	void deleteCustomer(int customrID);
	List<Customer> getAllCustomers();
	Customer getOneCustomer(int customerID);
	
	
	
	
}
