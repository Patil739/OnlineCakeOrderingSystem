package com.cms.service;

import java.util.List;

import javax.validation.Valid;

import com.cms.entity.Customer;
import com.cms.entity.OrderPlace;
import com.cms.entity.User;
import com.cms.exception.AdminException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.OrderException;
import com.cms.exception.UserException;

public interface OrderPlaceService {
	public OrderPlace addOrder(String key);
	public OrderPlace removeOrder(Integer order_Id, String key, @Valid User user) throws OrderException, LoginException, CustomerException, UserException;
    public List<OrderPlace> viewOrder(String key);
	public List<OrderPlace> viewallOrdersByDate(String key, String stringdate);
	public List<OrderPlace> viewAllOrdersByLocation(String key, String location, @Valid String userId, @Valid String userPassword);
	List<OrderPlace> viewAllOrdersbyUserId(Customer customer, String key);
	public List<OrderPlace> viewAllOrdersbyUserId(@Valid String userId, @Valid String userPassword, String key,
			@Valid String customer_userId) throws OrderException, UserException, LoginException, CustomerException, AdminException;

}
