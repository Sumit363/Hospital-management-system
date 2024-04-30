package com.demo.hospital_management.hospitalmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	@Id
	@Column(name="bid", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="treatment_name", nullable=false)
	private String treatmentName;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="bill_date")
	private Date billDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	private Patient patient;
}
