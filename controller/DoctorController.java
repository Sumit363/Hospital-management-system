package com.demo.hospital_management.hospitalmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hospital_management.hospitalmanagement.dao.DoctorDao;
import com.demo.hospital_management.hospitalmanagement.model.Doctor;
import com.demo.hospital_management.hospitalmanagement.request.DoctorRequest;
import com.demo.hospital_management.hospitalmanagement.response.BaseResponse;
import com.demo.hospital_management.hospitalmanagement.response.DoctorResponse;
import com.demo.hospital_management.hospitalmanagement.service.DoctorService;
/*
 * REQUESTS - POST, GET, PUT, DELETE, PATCH
 * POST - CREATE, UPDATE, READ, DELETE
 * GET - READ/FETCH
 * DELETE-DELETING
 * PUT-UPDATING WHOLE RECORD
 * PATCH- UPDATING PARTIAL RECORD
 * @RequestBody - binds the incoming HTTP request body to the class parameter
 * @c - {id} - the value will be passed in url
 * 
 * 
 * Pagination - traversing the pages
 * page number and page size
 * page number starts from 0,1,.....
 * page size starts 0,1,2 ,.....(how many records we want to show in that page)
 * page no - 5
 * page size - 20
 * 
 * 0 - 0-19
 * 1 -  20-39
 * 2 - 40-59
 * 3 - 60-79
 * 4 - 80-99
 * 
 */
@RestController // combination of @Controller and @ResponseBody
@RequestMapping(value="/hospital/v1/api") // it maps web requests
public class DoctorController {
	
	Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	DoctorDao doctorDao;
	
	
	@PostMapping(value="/save") // OTHER WAY @RequestMapping(value="/save", method = RequestMethod.POST)
	public BaseResponse addDoctor(@RequestBody DoctorRequest doctorRequest) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			DoctorResponse doctorResponse = doctorService.addDoctor(doctorRequest);
			baseResponse.setData(doctorResponse);
			baseResponse.setMessage("Doctor record created succesfully!!!");
			return baseResponse;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			baseResponse.setMessage("Unable to save doctor record!!"+e.getMessage());
			return baseResponse;
		}
		
	}
	
	@GetMapping(value="/doctor/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable("id") Long id) {
		try {
			DoctorResponse doctor = doctorService.getDoctor(id);
			return ResponseEntity.ok().body(doctor);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value="/doctor/specialization/{specialization}")
	public ResponseEntity<?> getDoctorBySpecialization(@PathVariable("specialization") String specialization) {
		logger.info("getDoctorBySpecialization API has started...");
		try {
			List<Doctor> doctor = doctorDao.findByDoctorSpecializtion(specialization);
			logger.info("Doctor record/s fetched successfully");
			return ResponseEntity.ok().body(doctor);
		}catch(Exception e) {
			logger.error("Doctor record not found");
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value="/doctor")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		List<Doctor> doctorList = doctorService.getDoctorList();
		return ResponseEntity.ok().body(doctorList);		
	}
	
	@GetMapping(value="/doctor/withpage")
	public ResponseEntity<List<Doctor>> getAllDoctorWithPagination(@RequestParam("page_number")
	Integer pageNumber, @RequestParam("page_size") Integer pageSize) {
		List<Doctor> doctorList = doctorService.getDoctorListWithPagination(pageNumber,pageSize);
		return ResponseEntity.ok().body(doctorList);		
	}
	
	@DeleteMapping(value="/delete/doctor/{id}")
	public ResponseEntity<String> deleteDoctorById(@PathVariable("id") Long id) {
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok().body("Doctor with id "+id+" deleted successfully");
		
	}
	
	
}
