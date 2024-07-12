package com.cms.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDto {
	@NotNull
    private Long customerID;
	@Size(min = 5,message = "Firstname should have minimum 5 characters")
    private String name;
	
    @Email
    private String email;
    private String phone;
    private String address;
}
