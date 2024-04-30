package com.demo.hospital_management.hospitalmanagement.response;


import java.util.HashSet;
import java.util.Set;

import com.demo.hospital_management.hospitalmanagement.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
	private String name;
	private String email;
	private String specialization;
	private Set<Patient> patient = new HashSet<>();
}
