package com.zomato.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zomato.service.JWTTokenUtil;
import com.zomato.service.ZomatoService;

@Component
public class JWTTokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	ZomatoService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException,IOException{
		//verify Token Header is presented or not.
		//If token presented we will send Token Required.
		//If token presented, get UserNmame from token
		//if token valid security layer will verify token user NAme is Presented in DB or Not
		//allow request to controller level
		
		String token= request.getHeader("Authorization");
		System.out.println("Header:Authorization"+token);
		
		String userNameFromToken=null;
		
		if(token!=null) {
			userNameFromToken = this.jwtTokenUtil.getUserNameFromToken(token.trim());
			
		}else {
			System.out.println("invalid Header Value!!");
		}
		
		if(userNameFromToken!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			//DB check
			//Security Layer will verify userName is presented in Db or not
			
			UserDetails userDetails = this.userService.loadUserByUsername(userNameFromToken);
			//validate JWT token :Time 3mins & userName
			boolean isValidToken  = this.jwtTokenUtil.isValidtoken(userDetails.getUsername(),token);
			
			if(isValidToken) {
				//update SecurityContext data for that user
				//are going to pass user Information
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				System.out.println("Token is Invalid..Please come with Valid token");
			}
			//allow request to Controller level
		}
		//Next Filter forwarding
		filterChain.doFilter(request, response);
	}

}