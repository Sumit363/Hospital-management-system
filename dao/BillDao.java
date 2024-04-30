package com.demo.hospital_management.hospitalmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hospital_management.hospitalmanagement.model.Bill;

@Repository
public interface BillDao extends JpaRepository<Bill, Long> {

}
