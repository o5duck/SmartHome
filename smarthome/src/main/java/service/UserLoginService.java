package service;

import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import dao.UserDevDao;
import exception.auth.PasswordConfirmPasswordNotMatchingException;
import exception.auth.UserNotFoundException;
import model.User;
import model.UserDev;
import request.LoginRequest;
import response.LoginResponse;

public class UserLoginService {
	private UserDao userDao;
	private UserDevDao userDevDao;
	
	public UserLoginService(UserDao userDao, UserDevDao userDevDao){
		this.userDao = userDao;
		this.userDevDao = userDevDao;
	}
	
	@Transactional
	public LoginResponse login(LoginRequest req){
		User user = userDao.selectById(req.getUser_id());
		if(user == null){
			throw new UserNotFoundException("user not found");
		}else{
			if(user.getUser_password().equals(req.getUser_password())){
				UserDev userDev = userDevDao.selectById(req.getUser_id());
				if(userDev != null)
					return new LoginResponse(user.getUser_name(), user.getUser_id(), userDev.getMaster_dev_id(), userDev.getSub_dev_id());
				else
					return new LoginResponse(user.getUser_name(), user.getUser_id(), null, null);
			}else{
				throw new PasswordConfirmPasswordNotMatchingException("password is not match with confirmedpassword");
			}
		}
	}
}
