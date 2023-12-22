package com.zomato.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zomato.service.ZomatoService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	//few Predefined Beans I Have to define
	
	@Autowired
	JWTTokenAuthenticationFilter jwtTokenAuthenticationFilter;
	
	@Autowired
	ZomatoService userService;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration builder)throws Exception{
		return builder.getAuthenticationManager();
	}
	//Password should be encoded/encrypted always
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		System.out.println("This securityFilter method of rules");
		
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/create/user","/login/user")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(this.jwtTokenAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}

}
