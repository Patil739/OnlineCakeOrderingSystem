package com.cms.service;

import java.util.List;

import javax.validation.Valid;

import com.cms.entity.Customer;
import com.cms.entity.User;
import com.cms.exception.CustomerException;

public interface CustomerServices {
	public Customer addCustomer(Customer customer) throws CustomerException;
	public Customer updateCustomer(String key, Customer customer) ;
	public String removeCustomer(String key, @Valid User user) ;
    public Customer viewCustomer(String key, Integer customer_Id);
	public List<Customer> viewAllCustomers(String key) ;

}
