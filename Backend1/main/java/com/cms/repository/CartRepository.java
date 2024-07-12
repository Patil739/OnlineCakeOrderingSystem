package com.cms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.Cart;
import com.cms.entity.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByCustomer(Customer  customer);
}
