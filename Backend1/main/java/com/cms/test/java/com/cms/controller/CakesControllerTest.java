package com.cms.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.cms.entity.Cakes;
import com.cms.entity.Cart;
import com.cms.service.CakeService;
import com.cms.service.CartService;
import com.cms.util.Converter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@WebMvcTest(CakesController.class)
class CakesControllerTest{

	@Autowired
	private MockMvc mockMvc;
	
	Cakes instructor;
	
	@MockBean
	CakeService instructorService;
	@MockBean
	Converter converter;
	
	@MockBean
	CartService userService;
	
	String jwtToken="";
   private Cart user;
	
	
	@BeforeEach
	void setUp()
	{
		user = new Cart();
		user.setUserName("john");
		user.setPassword("pass123");
		
	}  

	
	
	String tokenCreation() throws ServletException
	{
		jwtToken=Jwts.builder().setSubject(user.getUserName()).claim("roles", "user").
				setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		return jwtToken;
	}
	
	@Test
	void testSaveInstructor() throws Exception
	{
		
		
		String accessToken = tokenCreation();
		System.out.println(accessToken);
		
		String jsonString = new JSONObject()
				.put("userId", 101)
				.put("firstName","john")
				.put("lastName","9832809569")
				.put("email","john@email.com")
				.put("userName","john")
				.put("password","pass123")
				.put("role","user")
				.toString();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/createInstructor").
				contentType(MediaType.APPLICATION_JSON).content(jsonString).
				header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)).
				andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	

}
