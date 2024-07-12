package com.cms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cms.entity.CurrentAdminSession;
import com.cms.entity.User;
import com.cms.exception.AdminException;
import com.cms.exception.LoginException;
import com.cms.repository.AdminRepository;
import com.cms.repository.CurrentAdminSessionRepository;
import com.cms.service.LoginLogoutAdminService;

public class LoginLogoutAdminServiceImplementation implements LoginLogoutAdminService {
	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepo;

	@Override
	public String logoutAdmin(String key) throws LogoutException {

		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_currentAdminSession.isPresent()) {

			currentAdminSessionRepo.delete(optional_currentAdminSession.get());

			return "Logged Out Successfully !";

		} else {
			throw new LogoutException("No User Logged In !");

		}
	}

	@Override
	public Admin authenticateAdmin(String userId, String userPassword, String key) throws UserException, AdminException, LoginException {

		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_currentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_currentAdminSession.get();

			Optional<Admin> optional_admin = adminRepo.findByMobileNumber(userId);

			if (optional_admin.isPresent()) {

				Admin admin = optional_admin.get();

				if (admin.getMobileNumber().equals(userId) && admin.getPassword().equals(userPassword)) {

					return admin;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new AdminException(
						"No Registered Admin Found with this Admin Id : " + currentAdminSession.getAdminId());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public Admin validateAdmin(String key) throws AdminException, LoginException {

		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_currentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_currentAdminSession.get();

			Optional<Admin> optional_admin = adminRepo.findById(currentAdminSession.getAdminId());

			if (optional_admin.isPresent()) {

				Admin admin = optional_admin.get();

				return admin;

			} else {
				throw new AdminException(
						"No Registered Admin Found with this Admin Id : " + currentAdminSession.getAdminId());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException {

		if ("Admin".equals(user.getRole())) {

			Optional<Admin> optionalAdmin = adminRepo.findByMobileNumber(user.getId());

			if (optionalAdmin.isPresent()) {

				Admin admin = optionalAdmin.get();

				Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo
						.findByAdminId(admin.getAdminId());

				if (optional_currentAdminSession.isEmpty()) {

					CurrentAdminSession currentAdminSession = new CurrentAdminSession();

					String key = RandomString.make(6);

					currentAdminSession.setAdminId(admin.getAdminId());
					currentAdminSession.setLocalDateTime(LocalDateTime.now());
					currentAdminSession.setKey(key);

					return currentAdminSessionRepo.save(currentAdminSession);
				} else {
					throw new LoginException("User Already Logged In With This Admin Id : " + admin.getAdminId());
				}

			} else {
				throw new AdminException("No Registered Customer Found With This User_Id : " + user.getId());
			}

		} else {
			throw new LoginException("Please, Select Admin as Role to Login !");
		}

	}

	@Override
	public CurrentAdminSession loginAdmin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticateAdmin(User user, String key) {
		// TODO Auto-generated method stub
		return null;
	}


}
