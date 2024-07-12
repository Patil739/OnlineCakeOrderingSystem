package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.apache.catalina.User;

import com.cms.model.CakesDTO;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id" , length = 30)
    private Long id;
    
    @Column(name="CustomerID" , length = 30)
    private Long customerId;
    
    @Column(name="CreatedAt" , length = 30)
    private LocalDateTime createdAt;
    
    @Column(name="UpdateAt" , length = 30)
    private LocalDateTime updatedAt;
    
    @Column(name="Status" , length = 30)
    private String status;
    
    @Column(name="TotalPrice" , length = 30)
    //private double totalPrice;kept in to order
    //basicprice
    //quantity
    
   // @Column(name="Currency" , length = 30)
   // private String currency;

    @ManyToOne
    @JoinColumn(name = "UserId", insertable = false, updatable = false)
    private User user;
    //cake
    //customer

	public List<CakesDTO> getProducts() {
		return null;
	}

	public void setProducts(List<CakesDTO> listofproducts) {
		// TODO Auto-generated method stub
		
	}

	public void setCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
