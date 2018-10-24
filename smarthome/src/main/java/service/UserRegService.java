package service;

import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import exception.auth.AlreadyExistingMemberException;
import model.User;
import request.RegistRequest;
import response.RegistResponse;
public class UserRegService {
	private UserDao userDao;
	
	public UserRegService(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Transactional
	public RegistResponse regist(RegistRequest req){
		User user = userDao.selectById(req.getUser_id());
		if(user != null){
			throw new AlreadyExistingMemberException("dup id - " + req.getUser_id());
		}
		User newUser = new User(
				req.getUser_id(), req.getUser_name(), req.getUser_password());
		userDao.insert(newUser);
		return new RegistResponse(newUser.getUser_id(), newUser.getUser_name());
	}
	
}
