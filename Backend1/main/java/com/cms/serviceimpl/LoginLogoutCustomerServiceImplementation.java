package com.cms.serviceimpl;

import com.cms.entity.CurrentCustomerSession;
import com.cms.entity.User;
import com.cms.service.LoginLogoutCustomerService;

public class LoginLogoutCustomerServiceImplementation implements LoginLogoutCustomerService{
	public CurrentCustomerSession loginCustomer(User user);

	public String logoutCustomer(String key) ;
	
	public User authenticateCustomer(User user, String key);

	public Customer validateCustomer(String key);

	@Override
	public CurrentCustomerSession loginCustomer(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticateCustomer(User user, String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
