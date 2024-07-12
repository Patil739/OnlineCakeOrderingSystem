package com.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Cakes;
import com.cms.exception.AdminException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.exception.UserException;
import com.cms.model.CakesDTO;
import com.cms.service.CakeService;
import com.cms.util.Converter;



@RestController
@RequestMapping("/cakes")
public class CakesController {
	@Autowired
	private CakeService Cakes;

	@GetMapping("/viewProducts")
	public ResponseEntity<List<Cakes>> viewAllProductsHandler() throws ProductException {

		List<Cakes> listofproducts = Cakes.viewallProducts();

		return new ResponseEntity<List<Cakes>>(listofproducts, HttpStatus.OK);
	}

	// Admin Role
	@PostMapping("/addProduct")
	public ResponseEntity<Cakes> addProductHandler(@RequestParam String key, @Valid @RequestBody Cakes product,
			@Valid @RequestParam String userid, @Valid @RequestParam String userpassword)
			throws CustomerException, LoginException, AdminException, UserException {

		Cakes added_product = Cakes.insertProduct(userid, userpassword, key, product);

		return new ResponseEntity<Cakes>(added_product, HttpStatus.ACCEPTED);
	}

	// Admin Role
	@PutMapping("/updateProduct")
	public ResponseEntity<Cakes> updateProductHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestBody Cakes product)
			throws LoginException, ProductException, AdminException, UserException {

		Cakes updated_product = Cakes.updateProduct(userId, userPassword, key, product);

		return new ResponseEntity<Cakes>(updated_product, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewProduct")
	public ResponseEntity<Cakes> viewProductHandler(@Valid @RequestParam Integer productId) throws ProductException {

		Cakes product = Cakes.viewCake(productId);

		return new ResponseEntity<Cakes>(product, HttpStatus.OK);
	}

	@GetMapping("/viewProductByCategory")
	public ResponseEntity<List<Cakes>> viewProductByCategoryHandler(@Valid @RequestParam String category_Name)
			throws ProductException {

		List<Cakes> listofproducts = Cakes.viewCakeByCategory(category_Name);

		return new ResponseEntity<List<Cakes>>(listofproducts, HttpStatus.OK);

	}

	// Admin Role
	@PostMapping("/deleteProduct")
	public ResponseEntity<String> removeProductHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestParam Integer product_Id)
			throws CustomerException, LoginException, ProductException, AdminException, UserException {

		String result = Cakes.removecake(userId, userPassword, key, product_Id);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

}