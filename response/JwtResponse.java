package com.demo.hospital_management.hospitalmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private final String token;
}
