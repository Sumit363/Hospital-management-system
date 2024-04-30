package com.demo.hospital_management.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.hospital_management.hospitalmanagement.model.Doctor;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Long>{
	
	Doctor findByDoctorId(Long id);
	
	@Query(nativeQuery = true, value="select * from doctor where specialization = :specialization")
	List<Doctor> findByDoctorSpecializtion(String specialization);

}
