package com.demo.hospital_management.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hospital_management.hospitalmanagement.request.JwtRequest;
import com.demo.hospital_management.hospitalmanagement.response.JwtResponse;
import com.demo.hospital_management.hospitalmanagement.service.JwtUserDetailsService;
import com.demo.hospital_management.hospitalmanagement.util.JwtUtil;



@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginAndCreateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}catch (Exception e) {
			throw new Exception("Invalid credentials",e);
		}
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String jwtToken = jwtUtil.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwtToken));
	}
}
