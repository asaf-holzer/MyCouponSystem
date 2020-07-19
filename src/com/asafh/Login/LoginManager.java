package com.asafh.Login;

import com.asafh.facade.AdminFacade;
import com.asafh.facade.ClientFacade;
import com.asafh.facade.CompanyFacade;
import com.asafh.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private ClientFacade clientFacade;

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
		switch (clientType) {
		case Administrator:
			clientFacade = new AdminFacade();
			if (clientFacade.login(email, password)) {
				return clientFacade;
			} else {
				return null;
			}
		case Company:
			clientFacade = new CompanyFacade();
			if (clientFacade.login(email, password)) {
				int companyID = ((CompanyFacade) clientFacade).getCompanyIdByEmailAndPassword(email, password);
				((CompanyFacade) clientFacade).setCompanyID(companyID);
				return clientFacade;
			} else {
				return null;
			}
		case Customer:
			clientFacade = new CustomerFacade();
			if (clientFacade.login(email, password)) {
				int customerID = ((CustomerFacade) clientFacade).getCustomerIdByEmailAndPassword(email, password);
				((CustomerFacade) clientFacade).setCustomerID(customerID);
				return clientFacade;
			} else {
				return null;
			}
		default:
			return null;

		}

	}

}
