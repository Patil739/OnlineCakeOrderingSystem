package com.cms.model;

import java.util.Date;

import javax.persistence.Column;

public class OrderPlaceDTO {
        private Long orderID;
        private Long customerID;
	    private Long cakeID;
	    private Date orderDate;
	    private Date deliveryDate;
	    private String orderStatus;
	    private Double totalAmount;
}
