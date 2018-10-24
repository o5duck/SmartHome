package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.RegistRequest;
import response.RegistResponse;
import service.UserRegService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	private UserRegService userRegService;
	
	public void setUserRegisterService(UserRegService userRegService){
		this.userRegService = userRegService;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public RegistResponse regist(@RequestBody RegistRequest request){
		return userRegService.regist(request);
	}
}