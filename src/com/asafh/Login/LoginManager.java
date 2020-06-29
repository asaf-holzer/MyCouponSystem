package com.asafh.Login;

import com.asafh.facade.AdminFacade;
import com.asafh.facade.ClientFacade;
import com.asafh.facade.CompanyFacade;
import com.asafh.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public ClientFacade Login(String email, String password, ClientType clientType) {
		if (clientType.equals(ClientType.Administrator)) {
			AdminFacade af = new AdminFacade();
			if (af.login(email, password)) {
				return af;
			}
		} else if (clientType.equals(ClientType.Company)) {
			CompanyFacade cof =new CompanyFacade();
			if (cof.login(email, password)) {
				return cof;
			}
			return new CompanyFacade();
		} else if (clientType.equals(ClientType.Customer)) {
			return new CustomerFacade();
		}

		return null;
	}

}
