package com.demo.hospital_management.hospitalmanagement.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.hospital_management.hospitalmanagement.model.Doctor;
import com.demo.hospital_management.hospitalmanagement.model.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {

	@Query(nativeQuery = true, value="select * from patient where did = :did")
	List<Patient> findByDId(Long did);
	
}
