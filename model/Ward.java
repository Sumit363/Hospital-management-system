package com.demo.hospital_management.hospitalmanagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ward")
@AllArgsConstructor
@NoArgsConstructor
public class Ward {

	@Id
	@Column(name="wid", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="ward_no", nullable=false)
	private String wardNo;
	
	@Column(name="ward_type")
	private String wardType;
	
	@OneToOne(mappedBy = "ward", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Patient patient;
	
}
