package com.demo.hospital_management.hospitalmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.hospital_management.hospitalmanagement.enums.Specialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@Column(name = "uid", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;
}
