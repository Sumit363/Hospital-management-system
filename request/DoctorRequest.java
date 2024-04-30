package com.demo.hospital_management.hospitalmanagement.request;

import com.demo.hospital_management.hospitalmanagement.enums.Specialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

	private String name;
	private String email;
	private String mobile;
	private String password;
	private Specialization specialization;
	//private String role;
}
