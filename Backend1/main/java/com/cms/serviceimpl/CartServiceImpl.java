package com.cms.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.entity.Cakes;
import com.cms.entity.Cart;
import com.cms.entity.Customer;
import com.cms.exception.CartException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.ProductException;
import com.cms.model.CakesDTO;
import com.cms.repository.CakesRepository;
import com.cms.repository.CartRepository;
import com.cms.service.CartService;
import com.cms.service.LoginLogoutCustomerService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private CakesRepository productRepo;

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerServiceImplementation;

	@Override
	public String deleteallproducts(String key) throws CustomerException, CartException, ProductException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				List<CakesDTO> listofproducts = cart.getProducts();

				if (listofproducts.isEmpty()) {

					throw new ProductException("Cart is Empty ! No Products found in the cart !");
				} else {

					List<CakesDTO> remove_products = cart.getProducts();

					listofproducts.removeAll(remove_products);

					cart.setProducts(listofproducts);

					cartRepo.save(cart);

					return "All Products are Deleted From The Cart !";
				}

			} else {
				throw new CartException("No Customer Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public List<CakesDTO> viewallproducts(String key)
			throws LoginException, CustomerException, ProductException, CartException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				List<CakesDTO> listofproducts = cart.getProducts();

				if (listofproducts.isEmpty()) {

					throw new ProductException("Cart is Empty ! No Products found in the cart !");
				} else {

					return listofproducts;

				}

			} else {
				throw new CartException("No Customer Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public Cart addproduct(Integer productId, Integer quantity, String key)
			throws ProductException, LoginException, CustomerException, CartException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cakes> optional_product = productRepo.findById(productId);

			if (optional_product.isPresent()) {

				Cakes database_product = optional_product.get();

				Integer available_quantity = database_product.getQuantity();

				if (available_quantity >= quantity) {

					Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

					if (optional_cart.isPresent()) {

						Cart cart = optional_cart.get();

						List<CakesDTO> listofproducts = cart.getProducts();

						for (CakesDTO p : listofproducts) {

							if (Objects.equals(p.getcakesId(), productId)) {

								throw new ProductException("Product Already Added To Cart !");
							}
						}

						CakesDTO productDTO = new CakesDTO();

						productDTO.setcakesId(database_product.getcakesId());
						productDTO.setname(database_product.getname());
						productDTO.setQuantity(quantity);
						productDTO.setPrice(database_product.getname());
						listofproducts.add(productDTO);

						cart.setProducts(listofproducts);

						cartRepo.save(cart);

						return cart;

					} else {
						throw new CartException("No Customer Cart Found, Please Login In !");
					}

				} else {
					throw new ProductException("Oops ! Available Product Quantity is : " + available_quantity);
				}

			} else {
				throw new ProductException("Invalid Product Id, No Product Found !");
			}

		} else {

			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public Cart deleteproduct(Integer productId, String key)
			throws LoginException, CustomerException, CartException, ProductException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

			if (optional_cart.isPresent()) {

				Cart cart = optional_cart.get();

				List<CakesDTO> list_of_products = cart.getProducts();

				if (!list_of_products.isEmpty()) {

					Boolean flag = false;

					for (int i = 0; i < list_of_products.size(); i++) {

						CakesDTO p = list_of_products.get(i);

						if (Objects.equals(p.getcakesId(), productId)) {

							list_of_products.remove(p);

							cart.setProducts(list_of_products);

							flag = true;
						}
					}

					if (Boolean.TRUE.equals(flag)) {

						cartRepo.save(cart);

						return cart;

					} else {
						throw new ProductException("No Products Found in the Cart With Product Id : " + productId);
					}
				}

				else {
					throw new ProductException("No Products Found in the Cart !");
				}

			} else {
				throw new CartException("No Cart Found, Please Login In !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public Cart udpateproductquantity(String key, Integer productId, Integer quantity)
			throws CartException, LoginException, CustomerException, ProductException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Cakes> optional_product = productRepo.findById(productId);

			if (optional_product.isPresent()) {

				Cakes product = optional_product.get();

				Integer available_quantity = product.getQuantity();

				Optional<Cart> optional_cart = cartRepo.findByCustomer(customer);

				if (optional_cart.isPresent()) {

					Cart cart = optional_cart.get();

					List<CakesDTO> listofproducts = cart.getProducts();

					if (!listofproducts.isEmpty()) {

						Boolean flag = false;

						for (CakesDTO s : listofproducts) {

							if (Objects.equals(s.getcakesId(), productId)) {

								if (available_quantity >= quantity) {

									s.setQuantity(quantity);

									flag = true;
								} else {
									throw new ProductException(
											"OOps ! Available Product Quantity is : " + available_quantity);
								}

							}

						}

						if (flag == true) {

							cart.setProducts(listofproducts);

							cartRepo.save(cart);

							return cart;
						} else {
							throw new ProductException(
									"No Products Found In the Cart By This Product Id : " + productId);
						}

					} else {
						throw new ProductException("No Products Found In The Cart !");
					}

				} else {
					throw new CartException("No Cart Found with this Customer Id : " + customer.getCustomerId());
				}

			} else {
				throw new ProductException("No Products Found By This Product Id :" + productId);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}


	}

	


