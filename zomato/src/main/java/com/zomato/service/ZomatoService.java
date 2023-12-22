package com.zomato.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zomato.dto.UserLogin;
import com.zomato.dto.UserRegister;
import com.zomato.entity.UserEntity;
import com.zomato.repository.ZomatoUserRepository;

@Service
public class ZomatoService implements UserDetailsService {

	@Autowired
	ZomatoUserRepository repository;
	
	//for encrypt passwords
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String registerUser(UserRegister request) {
		//Convert to Entity Object
		UserEntity entity = new UserEntity();
		entity.setEmailId(request.getEmailId());
		entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		entity.setFullName(request.getFullName());
		entity.setContactNumber(request.getContactNumber());
		
		repository.save(entity);
		
		return "User Created Successfully,Please Login Now.";
	}

	public String loginUser(UserLogin request) {
		Optional<UserEntity> entity= repository.findByEmailId(request.getEmailId());
		
		System.out.println(entity);
		
		if(entity!=null) {
		return "user login is success ,Welcome";
		}else {
			return"Invalid credintials,Please try Again!";
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		
		UserEntity user = repository.findByEmailId(username).orElseThrow(()->
		new RuntimeException("User Not Found"));
		
		return user;
	}

	
}
