package com.cms.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.entity.Cakes;
import com.cms.model.CakesDTO;
@Repository
public interface CakesRepository extends JpaRepository<Cakes, Integer>{
    Optional<Cakes> findByName(String name);

	List<Cakes> viewByProductCategoryName(String category_Name);
}
