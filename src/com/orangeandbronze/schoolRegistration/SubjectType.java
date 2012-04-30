package com.orangeandbronze.schoolRegistration;

public enum SubjectType {
	UNDERGRADUATE(2000), GRADUATE(4000);
	
	private double feePerSection;
	
	SubjectType(double feePerSection){
		this.feePerSection = feePerSection;
	}
	
	double getFeePerSection(){
		return this.feePerSection;
	}
}
