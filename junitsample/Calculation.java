package com.demo.hospital_management.hospitalmanagement.junitsample;

import org.springframework.stereotype.Component;

@Component
public class Calculation {
	
	public int findSum(int a, int b) {
		return a+b;
	}
	
	public int findDiff(int a, int b) {
		return a-b;
	}
	
	public int findMul(int a, int b) {
		return a*b;
	}
	
	public int findDiv(int a, int b) {
		return a/b;
	}

}
