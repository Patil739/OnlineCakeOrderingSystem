package com.cms.service;

import com.cms.entity.Admin;
import com.cms.entity.CurrentAdminSession;
import com.cms.entity.User;

public interface LoginLogoutAdminService {

		public CurrentAdminSession loginAdmin(User user);
		public String logoutAdmin(String key);

		public User authenticateAdmin(User user, String key);

		public Admin validateAdmin(String key);
		public Admin authenticateAdmin(String userId, String userPassword, String key);

	}

