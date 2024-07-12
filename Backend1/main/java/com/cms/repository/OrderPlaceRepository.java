package com.cms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.OrderPlace;

@Repository
public interface OrderPlaceRepository extends JpaRepository<OrderPlace, Long> {
    Optional<OrderPlace> findById(Long id);

//	@Query("from Order o where o.OrderDate=:date")
	public List<OrderPlace> findByorderDate(LocalDate date);

//	@Query("from Order o where o.Location=:location")
	public List<OrderPlace> findBylocation(String location);
}