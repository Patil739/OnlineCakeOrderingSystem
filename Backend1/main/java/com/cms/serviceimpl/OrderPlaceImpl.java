package com.cms.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Admin;
import com.cms.entity.Cakes;
import com.cms.entity.Cart;
import com.cms.entity.Customer;
import com.cms.entity.OrderPlace;
import com.cms.exception.AdminException;
import com.cms.exception.CartException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.OrderException;
import com.cms.exception.ProductException;
import com.cms.exception.UserException;
import com.cms.model.CakesDTO;
import com.cms.repository.CakesRepository;
import com.cms.repository.CartRepository;
import com.cms.repository.CustomerRepository;
import com.cms.repository.OrderPlaceRepository;
import com.cms.service.LoginLogoutAdminService;
import com.cms.service.LoginLogoutCustomerService;

@Service
public class OrderPlaceImpl {
	
		@Autowired
		private OrderPlaceRepository orderRepo;

		@Autowired
		private LoginLogoutCustomerService loginLogoutCustomerServiceImplementation;

		@Autowired
		private LoginLogoutAdminService loginLogoutAdminServiceImplementation;

		@Autowired
		private CustomerRepository customerRepo;

		@Autowired
		private CartRepository cartRepo;

		@Autowired
		private CakesRepository productRepo;

		public List<OrderPlace> viewAllOrdersbyUserId(String userId, String userPassword, String userid, String key)
				throws OrderException, UserException, LoginException, CustomerException, AdminException {

			Admin validate_admin = loginLogoutAdminServiceImplementation.authenticateAdmin( userId,  userPassword, key);

			if (validate_admin != null) {

				Optional<Customer> optional_customer = customerRepo.findByMobileNumber(userid);

				if (optional_customer.isPresent()) {

					Customer customer = optional_customer.get();

					List<OrderPlace> listoforders = customer.getListOfOrders();

					if (!listoforders.isEmpty()) {

						return listoforders;

					} else {
						throw new OrderException("No Orders Are Been Placed With The Customer_Id : " + userId);
					}
				} else {
					throw new CustomerException("No Customer Registered With The User Id : " + userId);
				}

			} else {
				throw new UserException("User Authentication Failed, Please Login In !");
			}

		}

		@Override
		public Order removeOrder(Integer orderId, String key, User user)
				throws OrderException, LoginException, CustomerException, UserException {

			User validate_user = loginLogoutCustomerServiceImplementation.authenticateCustomer(user, key);

			if (validate_user != null) {

				Optional<Customer> optionalcustomer = customerRepo.findByMobileNumber(user.getId());

				if (optionalcustomer.isPresent()) {

					Optional<OrderPlace> optional_order = orderRepo.findById(orderId);

					if (optional_order.isPresent()) {

						OrderPlace order = optional_order.get();

						order.setOrderStatus("Cancelled");

						List<CakesDTO> listofproducts = optional_order.get().getProductDtoList();

						for (int i = 0; i < listofproducts.size(); i++) {

							CakesDTO productDTO = listofproducts.get(i);

							Integer deleted_quantity = productDTO.getQuantity();

							Optional<Cakes> optional_product = productRepo.findById(productDTO.getProductId());

							Cakes product = optional_product.get();

							Integer database_quantity = product.getQuantity();

							product.setQuantity(database_quantity + deleted_quantity);

							productRepo.save(product);
						}

						return orderRepo.save(order);

					} else {
						throw new OrderException("No Orders Are Found With This Order_Id : " + orderId);
					}
				}

				else {
					throw new CustomerException("No Customer Registered With The User Id : " + user.getId());
				}

			} else {
				throw new CustomerException("No Customer Found, Please Login In !");
			}
		}

		@Override
		public List<OrderPlace> viewallOrdersByDate(String key, String stringdate)
				throws OrderException, CustomerException, LoginException {

			LocalDate date = LocalDate.parse(stringdate);

			Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

			if (customer != null) {

				List<OrderPlace> listOfOrdersByLocalDate = orderRepo.findByorderDate(date);

				if (!listOfOrdersByLocalDate.isEmpty()) {

					return listOfOrdersByLocalDate;
				} else {
					throw new OrderException("No Orders Found For This Date : " + date);
				}

			} else {
				throw new CustomerException("No Customer Found, Please Login In !");
			}

		}

		@Override
		public List<OrderPlace> viewAllOrdersByLocation(String userId,String userPassword,String key, String location)
				throws OrderException, LoginException, AdminException, UserException {

			Admin validate_admin = loginLogoutAdminServiceImplementation.authenticateAdmin(userId,userPassword,key);

			if (validate_admin != null) {

				List<OrderPlace> listOfOrdersByLocation = orderRepo.findBylocation(location);

				if (!listOfOrdersByLocation.isEmpty()) {

					return listOfOrdersByLocation;

				} else {
					throw new OrderException("No Orders Found For This Location : " + location);
				}

			} else {
				throw new AdminException("No Admin Found, Please Login In as Admin!");
			}

		}

		@Override
		public OrderPlace addOrder(String key)
				throws LoginException, CustomerException, OrderException, CartException, ProductException {

			Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

			if (customer != null) {

				Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

				List<OrderPlace> listoforders = customer.getListOfOrders();

				if (!optional_cart.isEmpty()) {

					Cart cart = optional_cart.get();

					OrderPlace order = new OrderPlace();

					order.setCustomer(customer);
					order.setAddress(customer.getAddress());
					order.setLocation(customer.getAddress().getCity());
					order.setOrderDate(LocalDate.now());
					order.setOrderStatus("Order Confirmed");

					List<CakesDTO> listofcartproducts = cart.getProducts();

					if (!listofcartproducts.isEmpty()) {

						Double totalprice = 0.0;

						List<CakesDTO> listoforderedproducts = new ArrayList<>();

						for (int i = 0; i < listofcartproducts.size(); i++) {

							CakesDTO cart_product = listofcartproducts.get(i);

							Optional<Cakes> optional_product = productRepo.findById(cart_product.getProductId());

							if (optional_product.isPresent()) {

								Cakes product = optional_product.get();

								Integer available_quantity = product.getQuantity();

								if (available_quantity >= cart_product.getQuantity()) {

									Double price = cart_product.getPrice() * cart_product.getQuantity();

									totalprice = totalprice + price;

									product.setQuantity(available_quantity - cart_product.getQuantity());

									productRepo.save(product);

									listoforderedproducts.add(cart_product);
								} else {
									throw new ProductException(
											"Oops ! Available Quantity of Products : " + available_quantity);
								}

							} else {

								throw new ProductException(
										"No Product Found With This Product Id : " + cart_product.getProductId());
							}

						}

						order.setTotal(totalprice);

						order.setProductDtoList(listoforderedproducts);

						listoforders.add(order);

						customer.setListOfOrders(listoforders);

						cart.setProducts(new ArrayList<>());

						cartRepo.save(cart);

						return orderRepo.save(order);

					} else {

						throw new OrderException("Cart is Empty, Please Add Products To Place an Order !");
					}

				} else {
					throw new CartException("No Cart Found With This Customer Id : " + customer.getCustomerId());
				}

			} else {
				throw new CustomerException("No Customer Found, Please Login In !");
			}

		}

		@Override
		public List<OrderPlace> viewOrder(String key) throws LoginException, CustomerException, OrderException {

			Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

			if (customer != null) {

				List<OrderPlace> listOfOrders = customer.getListOfOrders();

				if (!listOfOrders.isEmpty()) {

					return listOfOrders;

				} else {
					throw new OrderException("No Orders Found For You !");
				}

			} else {
				throw new CustomerException("No Customer Found, Please Login In !");
			}

		}

	}

