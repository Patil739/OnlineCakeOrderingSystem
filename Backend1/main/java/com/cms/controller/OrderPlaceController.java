package com.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.OrderPlace;
import com.cms.entity.User;
import com.cms.exception.AdminException;
import com.cms.exception.CartException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.OrderException;
import com.cms.exception.ProductException;
import com.cms.exception.UserException;
import com.cms.service.OrderPlaceService;

@RestController
@RequestMapping("/order")
public class OrderPlaceController {
	
		@Autowired
		private OrderPlaceService orderService;

		@PostMapping("/addOrder")
		public ResponseEntity<OrderPlace> addOrderHandler(@RequestParam String key)
				throws LoginException, CustomerException, OrderException, CartException, ProductException {

			OrderPlace order = orderService.addOrder(key);

			return new ResponseEntity<OrderPlace>(order, HttpStatus.ACCEPTED);
		}

		@DeleteMapping("/deleteOrder")
		public ResponseEntity<OrderPlace> removeOrderHandler(@Valid @RequestParam Integer order_Id, @RequestParam String key,
				@Valid @RequestBody User user) throws OrderException, LoginException, CustomerException, UserException {

			OrderPlace order = orderService.removeOrder(order_Id, key, user);

			return new ResponseEntity<OrderPlace>(order, HttpStatus.OK);

		}

		@GetMapping("/viewOrder")
		public ResponseEntity<List<OrderPlace>> viewOrderHandler(@RequestParam String key)
				throws LoginException, CustomerException, OrderException {

			List<OrderPlace> listOfOrders = orderService.viewOrder(key);

			return new ResponseEntity<List<OrderPlace>>(listOfOrders, HttpStatus.OK);
		}

		@PostMapping("/allOrders")
		public ResponseEntity<List<OrderPlace>> viewallOrdersByDateHandler(@RequestParam String key,
				@Valid @RequestParam String stringdate) throws OrderException, CustomerException, LoginException {

			// Please Make Sure Date should be in this format "YYYY-MM-DD"

			List<OrderPlace> listOfOrders = orderService.viewallOrdersByDate(key, stringdate);

			return new ResponseEntity<List<OrderPlace>>(listOfOrders, HttpStatus.OK);

		}

		@PostMapping("/viewLocationOrders")
		public ResponseEntity<List<OrderPlace>> viewAllOrdersByLocationHandler(@RequestParam String key,
				@Valid @RequestParam String location, @Valid @RequestParam String userId,@Valid @RequestParam String userPassword )
				throws OrderException, LoginException, CustomerException, AdminException, UserException {

			List<OrderPlace> listoforders = orderService.viewAllOrdersByLocation(key, location, userId,userPassword);

			return new ResponseEntity<List<OrderPlace>>(listoforders, HttpStatus.OK);

		}

		@PostMapping("/viewUserOrder")
		public ResponseEntity<List<OrderPlace>> viewAllOrdersbyUserIdHandler(@RequestParam String key,
				@Valid @RequestParam String userId,@Valid @RequestParam String userPassword , @Valid @RequestParam String customer_userId)
				throws OrderException, UserException, LoginException, CustomerException, AdminException {

			List<OrderPlace> listoforders = orderService.viewAllOrdersbyUserId(userId,userPassword, key, customer_userId);

			return new ResponseEntity<List<OrderPlace>>(listoforders, HttpStatus.OK);

		}

	}