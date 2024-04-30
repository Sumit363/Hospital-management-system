package com.demo.hospital_management.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.hospital_management.hospitalmanagement.dao.DoctorDao;
import com.demo.hospital_management.hospitalmanagement.dao.PatientDao;
import com.demo.hospital_management.hospitalmanagement.dao.UserDao;
import com.demo.hospital_management.hospitalmanagement.enums.ERole;
import com.demo.hospital_management.hospitalmanagement.exception.ApplicationException;
import com.demo.hospital_management.hospitalmanagement.model.Doctor;
import com.demo.hospital_management.hospitalmanagement.model.Patient;
import com.demo.hospital_management.hospitalmanagement.model.Users;
import com.demo.hospital_management.hospitalmanagement.request.DoctorRequest;
import com.demo.hospital_management.hospitalmanagement.response.DoctorResponse;

@Service
public class DoctorService {

	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	PatientDao patientDao;

	public DoctorResponse addDoctor(DoctorRequest doctorRequest) {

		Doctor doctor = new Doctor();
		Users users = new Users();
		

		doctor.setName(doctorRequest.getName());
		doctor.setEmail(doctorRequest.getEmail());
		doctor.setMobile(doctorRequest.getMobile());
		doctor.setSpecialization(doctorRequest.getSpecialization());
		doctor.setPassword(doctorRequest.getPassword());
		
//		users.setUsername(doctorRequest.getEmail());
//		users.setPassword(doctorRequest.getPassword());
//		users.setRole(ERole.DOCTOR.toString());
//
//		users = userdao.save(users);
		doctor = doctorDao.save(doctor);

		DoctorResponse doctorResponse = new DoctorResponse();
		doctorResponse.setName(doctor.getName());
		doctorResponse.setEmail(doctor.getEmail());
		doctorResponse.setSpecialization(doctor.getSpecialization().name());
		return doctorResponse;

	}

	public DoctorResponse getDoctor(Long id) {
		DoctorResponse doctorResponse = new DoctorResponse();
		Doctor doctor = doctorDao.findByDoctorId(id);
		if(doctor==null)
			throw new ApplicationException("Doctor with id "+id+" is not found");
		doctorResponse.setName(doctor.getName());
		doctorResponse.setEmail(doctor.getEmail());
		doctorResponse.setSpecialization(doctor.getSpecialization().name());
//		List<Patient> patientList =  patientDao.findByDId(doctor.getDoctorId());
//		Set<Patient> patSet = new HashSet<>();
//		patSet.add(patientList.get(0));
//		if(patSet.isEmpty() || patSet.size()<=0) {
//			doctorResponse.setPatient(null);
//		}else {
//			doctorResponse.setPatient(patSet);
//		}
		return doctorResponse;

	}

	public List<Doctor> getDoctorList() {
		List<Doctor> doctorList = doctorDao.findAll();
//		for(Doctor doc : doctorList) {
//			Set<Patient> patientList =  patientDao.findByDoctorId(doc.getDoctorId());
//			if(patientList.isEmpty() || patientList.size()<=0) {
//				doc.setPatient(null);
//			}else {
//				doc.setPatient(patientList);
//			}
//		}
		
		return doctorList;
	}
	
	public List<Doctor> getDoctorListWithPagination(Integer pageNumber, Integer pageSize) {
		Page<Doctor> doctorPage = doctorDao.findAll(PageRequest.of(pageNumber, pageSize,Sort.by("name").ascending()));
		List<Doctor> doctorList = new ArrayList<>();
		for(Doctor doctor:doctorPage) {
			doctorList.add(doctor);
		}
		return doctorList;
	}

	public void deleteDoctor(Long id) {
		doctorDao.deleteById(id);
	}

	public Doctor updateDoctor(Long id, DoctorRequest doctorRequest) {// doctorRequest - contains new values
		Doctor doctor = doctorDao.findByDoctorId(id);// old values fetched from database table
		if (doctorRequest.getName() != "") {
			doctor.setName(doctorRequest.getName());
		}
		if (doctorRequest.getEmail() != "") {
			doctor.setEmail(doctorRequest.getEmail());
		}
		if (doctorRequest.getMobile() != "") {
			doctor.setMobile(doctorRequest.getMobile());
		}
		if (doctorRequest.getSpecialization().name() != "") {
			doctor.setSpecialization(doctorRequest.getSpecialization());
		}

		doctor = doctorDao.save(doctor);

		return doctor;
	}
}
