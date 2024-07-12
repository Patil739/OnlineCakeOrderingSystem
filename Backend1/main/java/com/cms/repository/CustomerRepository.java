package com.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerRepository, Long> {
    CustomerRepository findByCustomername(String name);

	public Optional<Customer> findByMobileNumber(String mobileNumber);

	Customer save(Customer customer);
}