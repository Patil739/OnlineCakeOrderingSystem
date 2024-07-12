package com.cms.service;


import java.util.Optional;
import com.cms.entity.Admin;
import com.cms.exception.AdminException;
import com.cms.exception.LoginException;
import com.cms.exception.UserException;


public interface AdminService {
public Admin addAdmin(String userId,String userPassword, String key, Admin admin)throws AdminException, UserException, LoginException ;
	public Admin viewAdminById(String key,String userId, String userPassword, Integer adminId) throws AdminException;
	public String deleteAdmin(Integer adminId, String userId, String userPassword, String key) throws AdminException, UserException, LoginException; 
}