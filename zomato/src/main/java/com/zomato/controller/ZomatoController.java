package com.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.zomato.dto.UserLogin;
import com.zomato.dto.UserLoginResponse;
import com.zomato.dto.UserRegister;
import com.zomato.service.JWTTokenUtil;
import com.zomato.service.ZomatoService;

@RestController
public class ZomatoController {

	@Autowired
	ZomatoService service;
	
	@Autowired
	JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/create/user")
	public String registerUser(@RequestBody UserRegister request){
		String result = service.registerUser(request);
		return result;
     }
	
	//This is for login Operation :Integrate Security Layer+JWT token
	
	@PostMapping("/login/user")
	public UserLoginResponse loginUser(@RequestBody UserLogin request){
		//TODO :we need to pass credentials to the Spring Security Layer
		this.doAuthentication(request.getEmailId(),request.getPassword());
		
		String token= this.jwtTokenUtil.genrateToken(request.getEmailId());
		
		return new UserLoginResponse(request.getEmailId(),token);
		
		
     }
	
	    @GetMapping("/get/profile")
		public String getProfile() {
		return "Welcome Kartik Please find your profile details.";
   	}
	    @GetMapping("/get/orders")
	    public String getOrders() {
	    	return "Welcome kartik, Please find your Orders";
	    }
		
	private void doAuthentication(String emailId,String password) {
		//passing userName and password to Authentication Manager
		
		UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(emailId, password);
		
		try {
			authenticationManager.authenticate(authentication);
		}
		catch(BadCredentialsException e){
			throw new RuntimeException("Invalid userName and Password.");
		}
	}
	 @GetMapping("/get/token")
	  public String getToken() {
		  return jwtTokenUtil.genrateToken("nareshit@gmail");
			
		}
	   @GetMapping("/validate/token")
	   public boolean ValidateToken(@RequestHeader String token) {
		return jwtTokenUtil.isValidtoken("nareshit@gmail.com",token);
	}
}