package com.cms.controller;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Admin;
import com.cms.exception.AdminException;
import com.cms.exception.CustomerException;
import com.cms.exception.UserException;
import com.cms.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public ResponseEntity<Admin> addAdminHandler(@Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam String key, @Valid @RequestBody Admin admin)
			throws AdminException, CustomerException, LoginException, UserException, com.cms.exception.LoginException {

		Admin savedadmin = adminService.addAdmin(userId, userPassword, key, admin);

		return new ResponseEntity<>(savedadmin, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAdminHandler(@RequestParam String key, @Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam Integer adminId)
			throws AdminException, CustomerException, LoginException, UserException, com.cms.exception.LoginException {

		String result = adminService.deleteAdmin(adminId, key, userId, userPassword);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/view")
	public ResponseEntity<Admin> viewAdminByIdHandler(@RequestParam String key, @Valid @RequestParam String userId,
			@Valid @RequestParam String userPassword, @RequestParam Integer adminId)
			throws AdminException, CustomerException, LoginException {

		Admin savedadmin = adminService.viewAdminById(key, userId, userPassword, adminId);

		return new ResponseEntity<>(savedadmin, HttpStatus.ACCEPTED);
	}

}
