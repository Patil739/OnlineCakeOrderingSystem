package com.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Cart;
import com.cms.exception.CartException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.model.CakesDTO;
import com.cms.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PutMapping("/addProducts")
	public ResponseEntity<Cart> addProductToCartHandler(@Valid @RequestParam Integer productId,
			@RequestParam String key, @RequestParam Integer quantity)
			throws ProductException, LoginException, CustomerException, CartException {

		Cart updatedCart = cartService.addproduct(productId, quantity, key);

		return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteCart")
	public ResponseEntity<String> emptyCartHandler(@RequestParam String key)
			throws ProductException, CartException, LoginException, CustomerException {

		String result = cartService.deleteallproducts(key);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/allProducts")
	public ResponseEntity<List<CakesDTO>> viewAllCartProductsHandler(@RequestParam String key)
			throws LoginException, CustomerException, ProductException, CartException {

		List<CakesDTO> listOfProducts = cartService.viewallproducts(key);

		return new ResponseEntity<List<CakesDTO>>(listOfProducts, HttpStatus.OK);

	}

	@DeleteMapping("/deleteProduct")
	public ResponseEntity<Cart> deleteProductFromCartHandler(@Valid @RequestParam Integer productId,
			@RequestParam String key) throws LoginException, CustomerException, CartException, ProductException {

		Cart cart = cartService.deleteproduct(productId, key);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/updateProductQuantity")
	public ResponseEntity<Cart> udpateProductQuantityHandler(@RequestParam String key,
			@Valid @RequestParam Integer productId, @RequestParam Integer quantity)
			throws CartException, LoginException, CustomerException, ProductException {

		Cart cart = cartService.udpateproductquantity(key, productId, quantity);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

}