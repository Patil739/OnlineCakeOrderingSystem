package com.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Admin;
import com.cms.entity.Cakes;
import com.cms.exception.AdminException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.exception.ResourceNotFoundException;
import com.cms.exception.UserException;
import com.cms.model.CakesDTO;
import com.cms.repository.CakesRepository;
import com.cms.service.CakeService;
import com.cms.service.LoginLogoutAdminService;
import com.cms.util.Converter;



@Service
public class CakesServiceImpl implements CakeService{
	@Autowired
	private CakesRepository productRepo;

	@Autowired
	private LoginLogoutAdminService loginLogoutAdminServiceImplementation;

	@Override
	public List<Cakes> viewallProducts() throws ProductException {

		List<Cakes> listofproducts = productRepo.findAll();

		if (listofproducts.isEmpty()) {
			throw new ProductException("No Products Found in the Database !");
		} else {
			return listofproducts;
		}

	}

	@Override
	public Cakes viewCakes(Integer productId) throws ProductException {

		Optional<Cakes> optional_product = productRepo.findById(productId);

		if (optional_product.isPresent()) {

			Cakes product = optional_product.get();

			return product;

		} else {
			throw new ProductException("No Product Found by this Product Id : " + productId);
		}

	}

	@Override
	public List<Cakes> viewProductByCategory(String category_Name) throws ProductException {

		List<Cakes> listofproducts = productRepo.viewByProductCategoryName(category_Name);

		if (listofproducts.isEmpty()) {
			throw new ProductException("No Products Found Under This Category : " + category_Name);
		} else {

			return listofproducts;

		}
	}

	@Override
	public Cakes insertProduct(String userId, String userPassword, String key, Cakes product)
			throws LoginException, AdminException, UserException {

		Admin validated_admin = loginLogoutAdminServiceImplementation.authenticateAdmin(userId, userPassword, key);

		if (validated_admin != null) {

			Cakes added_product = productRepo.save(product);

			return added_product;

		} else {
			throw new AdminException("Invalid Admin Key, Please Login In as Admin!");
		}

	}

	@Override
	public Cakes updateProduct(String userId, String userPassword, String key, Cakes product)
			throws AdminException, LoginException, ProductException, UserException {

		Admin validated_admin = loginLogoutAdminServiceImplementation.authenticateAdmin(userId, userPassword, key);

		if (validated_admin != null) {

			Optional<Cakes> optional_product = productRepo.findById(product.getcakesId());

			if (optional_product.isPresent()) {

				return productRepo.save(product);

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}
		} else {
			throw new AdminException("Invalid Admin Key, Please Login In as Admin !");
		}

	}

	@Override
	public String removeProduct(String userId, String userPassword, String key, Integer product_Id)
			throws AdminException, LoginException, ProductException, UserException {

		Admin validated_admin = loginLogoutAdminServiceImplementation.authenticateAdmin(userId, userPassword, key);

		if (validated_admin != null) {

			Optional<Cakes> optional_product = productRepo.findById(product_Id);

			if (optional_product.isPresent()) {

				productRepo.deleteById(product_Id);

				return " Product Deleted Successfully !";

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}
		} else {
			throw new AdminException("Invalid Admin Key, Please Login In !");
		}

	}

	@Override
	public Cakes viewCake(Integer cakesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cakes> viewCakeByCategory(String category_Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removecake(String userId, String userPassword, String key, Integer cakesId) {
		// TODO Auto-generated method stub
		return null;
	}

}
	

