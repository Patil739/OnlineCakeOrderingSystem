package com.cms.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import lombok.Data;
@Data
public class CartDTO {
	
    private Long id;
    private Long customerID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private double totalPrice;
    private String currency;
}
