package com.orangeandbronze.schoolRegistration;

public enum YearLevel {
	FRESHMAN(15, 18), SOPHOMORE(18, 24), JUNIOR(18, 24), SENIOR(0, 21);
	
	private final int minimumLoad;
	private final int maximumLoad;
	
	YearLevel(int minimumLoad, int maximumLoad) {
		this.minimumLoad = minimumLoad;
		this.maximumLoad = maximumLoad;
	}
	
	int getMinimumLoad() {
		return minimumLoad;
	}
	
	int getMaximumLoad() {
		return maximumLoad;
	}
}
