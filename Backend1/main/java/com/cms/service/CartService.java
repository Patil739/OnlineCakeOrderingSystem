package com.cms.service;

import java.util.List;
import com.cms.entity.Cart;
import com.cms.exception.CartException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.model.CakesDTO;


public interface CartService {
	public String deleteallproducts(String key) throws CustomerException, CartException, ProductException;
			
	public List<CakesDTO> viewallproducts(String key) throws LoginException, CustomerException, ProductException, CartException;
			
	public Cart addproduct(Integer productId, Integer quantity, String key) throws ProductException, LoginException, CustomerException, CartException;
			
	public Cart deleteproduct(Integer productId, String key) throws LoginException, CustomerException, CartException, ProductException;
			
	public Cart udpateproductquantity(String key, Integer productId, Integer quantity) throws CartException, LoginException, CustomerException, ProductException;
			
	}


