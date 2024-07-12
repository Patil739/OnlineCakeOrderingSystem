package com.cms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class CakesDTO {
   
    @NotNull
    @Size(min = 5,message = "name should have minimum 5 characters")
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private String flavor;
    @NotNull
    private String size;
    @NotNull
    private Integer quantity;
	public Integer getQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setQuantity(Integer quantity2) {
		// TODO Auto-generated method stub
		
	}
	public void setcakesId(Integer getcakesId) {
		// TODO Auto-generated method stub
		
	}
	public void setname(Object getname) {
		// TODO Auto-generated method stub
		
	}
	public void setPrice(Object getname) {
		// TODO Auto-generated method stub
		
	}
	public Object getcakesId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	}

