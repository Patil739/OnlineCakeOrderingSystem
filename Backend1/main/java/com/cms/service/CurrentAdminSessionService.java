package com.cms.service;

import com.cms.entity.Admin;
import com.cms.entity.CurrentAdminSession;

public interface CurrentAdminSessionService {

	public CurrentAdminSession getCurrentAdminSession(String key);
	public Admin getAdminDetails(String key);

	public Integer getAdminId(String key);

}