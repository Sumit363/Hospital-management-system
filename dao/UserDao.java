package com.demo.hospital_management.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.hospital_management.hospitalmanagement.model.Doctor;
import com.demo.hospital_management.hospitalmanagement.model.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Long>{
	
	Users findByUsername(String userName);

}
