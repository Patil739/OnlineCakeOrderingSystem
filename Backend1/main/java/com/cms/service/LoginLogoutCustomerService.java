package com.cms.service;

import com.cms.entity.CurrentCustomerSession;
import com.cms.entity.Customer;
import com.cms.entity.User;

public interface LoginLogoutCustomerService {
	public CurrentCustomerSession loginCustomer(User user);
	public String logoutCustomer(String key) ;
	
	public User authenticateCustomer(User user, String key);
	public Customer validateCustomer(String key);
}
