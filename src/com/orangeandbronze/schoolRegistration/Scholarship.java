package com.orangeandbronze.schoolRegistration;

public enum Scholarship {
	FULL_SCHOLAR(100), HALF_SCHOLAR(50), REGULAR(0);
	
	private final int percentage;
	
	Scholarship(int percentage) {
		this.percentage = percentage;
	}
	
	int getScholarship() {
		return percentage;
	}
}
