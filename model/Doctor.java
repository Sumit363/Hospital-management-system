package com.demo.hospital_management.hospitalmanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.demo.hospital_management.hospitalmanagement.enums.Specialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

	@Id
	@Column(name = "did", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long doctorId;

	@Column(name = "dname", nullable = false)
	private String name;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "specialization")
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	@Column(name = "password")
	private String password;

	

//	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	private Set<Patient> patient;
	

}
