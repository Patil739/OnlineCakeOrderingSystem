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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminId", length = 20)
    private Long adminID;

    @Column(name = "UserName", length = 30)
    private String username;

    @Column(name = "Password", length = 30)
    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("admin")
    private Set<OrderPlace> orders;

	public String getadminID() {
		// TODO Auto-generated method stub
		return null;
	}
}
