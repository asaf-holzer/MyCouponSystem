package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Customer;

public interface CustomersDAO {

	boolean isCustomerExists(String email, String password);
	boolean isCustomerExistsByEmail(String email);//my extension
	void addCustomer(Customer customer);
	void updateCustomer(Customer Customer);
	void deleteCustomer(int customrID);
	List<Customer> getAllCustomers();
	Customer getOneCustomer(int customerID);
	
	
	
	
}
