package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.LoginRequest;
import response.LoginResponse;
import service.UserLoginService;

@RequestMapping("/login")
@RestController
public class LoginController {
	private UserLoginService userLoginService;
	
	public void setUserLoginService(UserLoginService userLoginService){
		this.userLoginService = userLoginService;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public LoginResponse login(@RequestBody LoginRequest request){
		return userLoginService.login(request);
	}
}
