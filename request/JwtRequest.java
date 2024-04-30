package com.demo.hospital_management.hospitalmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
	private String username;// in our case we are using email as username
	private String password;
}
