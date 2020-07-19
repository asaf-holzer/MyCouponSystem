package com.asafh.dao;


import java.util.List;

import com.asafh.beans.Customer;

public interface CustomersDAO {

	boolean isCustomerExists(String email, String password);
	boolean isCustomerExistsByEmail(String email);//my addition for admin facade
	boolean isCustomerExistsByCustomerID(int customerID);//my addition for exception
	void addCustomer(Customer customer);
	void updateCustomer(Customer Customer);
	void deleteCustomer(int customrID);
	List<Customer> getAllCustomers();
	Customer getOneCustomer(int customerID);
	 Customer getOneCustomerByEmailAndPassword(String email, String password);
	
	
	
	
}
