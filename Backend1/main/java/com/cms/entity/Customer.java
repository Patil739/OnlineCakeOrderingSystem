package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CustomerID",length=20)
    private Long customerID;

    @Column(name="Name",length=20)
    private String name;


    @Column(name="Email",length=20)
    private String email;

    @Column(name="Phone",length=20)
    private String phone;

    @Column(name="Address",length=20)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private Set<Payment> payments;

	public List<OrderPlace> getListOfOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCustomerId() {
		// TODO Auto-generated method stub
		return null;
	}
}
