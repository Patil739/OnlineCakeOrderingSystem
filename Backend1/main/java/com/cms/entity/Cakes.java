package com.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cakes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Cakesid",length= 20)
    private Long cakesId;
    
    @Column(name="Name",length= 20)
    private String name;
    
    @Column(name="Price",length= 20)
    private Double price;
    
    @Column(name="Flavor",length= 20)
    private String flavor;
    
    @Column(name="Size",length= 20)
    private String size;//weight
    
    @Column(name="Quantity",length= 20)
    private Integer quantity;

    @ManyToMany(mappedBy = "cakes")
    @JsonIgnoreProperties("cakes")//connect with cart and cart connect with order
    private Set<OrderPlace> orders;

	public void setQuantity(int i) {
		
	}

	public Integer getcakesId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getname() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
}
