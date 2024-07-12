package com.cms.service;

import java.util.List;
import java.util.Optional;

import com.cms.entity.Cakes;
import com.cms.exception.AdminException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.exception.UserException;
import com.cms.model.CakesDTO;

public interface CakeService {

	public List<Cakes> viewallProducts() throws ProductException;
	public Cakes insertProduct(String userid,String userpassword , String key, Cakes cake) throws LoginException, AdminException, UserException;
	public Cakes updateProduct(String userId,String userPassword, String key, Cakes cake) throws AdminException, LoginException, ProductException, UserException;

	public Cakes viewCake(Integer cakesId);

	public List<Cakes> viewCakeByCategory(String category_Name);

	public String removecake(String userId,String userPassword, String key, Integer cakesId);
	String removeProduct(String userId, String userPassword, String key, Integer product_Id)
			throws AdminException, LoginException, ProductException, UserException;
	Cakes viewCakes(Integer productId) throws ProductException;
	List<Cakes> viewProductByCategory(String category_Name) throws ProductException;
	}


