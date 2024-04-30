package com.demo.hospital_management.hospitalmanagement.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	// define the expiration time in ms
	private static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;

	// take the secret value from application.properties and assign it to jwtsecrect
	// variable
	@Value("${secret}")
	private String jwtSecret;

	// generate the jwt token
	public String generateJwtToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	// validate the username from token and username from userdetails
	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		String tokenUsername = getUsernameFromToken(token);
		// we need to get expiration time so first we are getting claims and parsing it
		// and getting expiration from claims
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());

		if (tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired) {
			return true;
		} else {
			return false;
		}
	}

	// get username from jwt token
	public String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();// subject contains username
	}
}
