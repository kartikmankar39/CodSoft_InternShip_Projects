package com.zomato.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTTokenUtil {

	//5 Min
	private static final long expMilliSeconds=5*60000;
	private final String SECRET_KEY="jksdhkjhJKHHKJGGFRUUJDVNBKS"
			+ "WOTUGDLSMXBZHDRJVNMakdhhdtejjdjehuruuAJAKJFHRNNGKCBMWEIYTIDKFGJFdjdjccnXLSJFYUEUW";
	
	//retrieve username from JWT token
	public String getUserNameFromToken(String token) {
		String tokenUserName = 
				Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		return tokenUserName;
	}
	//generate token for user
	public String genrateToken(String usernName) {
		Map<String,Object>claims=new HashMap<>();
		return doGeneratToken(claims, usernName);
	}
	
	//while creating the token-
	//Sign the JWT using the HS512 algorithm and secret key.
	private String doGeneratToken(Map<String,Object>claims,String subject) {
		String token=
				Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()
						+expMilliSeconds))
				.signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
		System.out.println("from doGenerateToken"+token);
		return token;
	}
	
	
	public boolean isTokenExpired(String token) {
		Date expirationTime =
				Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		
		
		//9:15 = token exp time
		//9.15 - Current time
		
		//9.15 is before to 9.10?
		return expirationTime.before(new Date());
	}
	
	//Validation of Token
	public boolean isValidtoken(String userName,String token) {
		//Extract User from Token
		final String tokenUserName= getUserNameFromToken(token);
		
		//Compare Token UserName with incoming UserName:userName
		boolean isExpired = isTokenExpired(token);
		
		return userName.equals(tokenUserName)&&!isExpired;
	}
}
