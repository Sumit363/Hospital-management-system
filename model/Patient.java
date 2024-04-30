package com.demo.hospital_management.hospitalmanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	@Id
	@Column(name="pid", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="pname", nullable=false)
	private String patientName;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="admitted_reason")
	private String admittedReason;
	
	@Column(name="admitted_date")
	private Date admittedDate;
	
	@Column(name="discharged_date")
	private Date dischargedDate;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="wid")
	private Ward ward;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="did")
	private Doctor doctor;
	
	//@OneToMany(mappedBy = "patient", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//private Set<Bill> bill = new HashSet<>();

}
