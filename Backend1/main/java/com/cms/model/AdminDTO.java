package com.cms.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AdminDTO {	
	@NotNull
    private Long adminID;
	@NotNull
	private String username;
	@NotNull
    private String password;
}
