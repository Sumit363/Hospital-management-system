package com.demo.hospital_management.hospitalmanagement.response;

import com.demo.hospital_management.hospitalmanagement.model.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	private DoctorResponse data;
	private String message;
}
