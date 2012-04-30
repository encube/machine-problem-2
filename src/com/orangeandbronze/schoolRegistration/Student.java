package com.orangeandbronze.schoolRegistration;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private final int idNumber;
	private YearLevel yearLevel;
	private Scholarship scholarship;
	private static int PERCENT = 100;
	
	private List<Enrollment> previousEnrollment =  new ArrayList<Enrollment>();
	
	public Student(int idNumber, YearLevel yearLevel, Scholarship scholarship){
		this.idNumber = idNumber;
		this.yearLevel = yearLevel;
		this.scholarship = scholarship;
	}
	
	@Override
	public String toString() {
		return idNumber + " " + yearLevel + " " + scholarship;
	}
	
	public int getIdNumber() {
		return this.idNumber;
	}
	
	public YearLevel getYearLevel() {
		return this.yearLevel;
	}

	public Scholarship getScholarship() {
		return this.scholarship;
	}
	
	public boolean hasTakenSubject(Subject subject){
		for(Enrollment e : this.previousEnrollment){
			if(e.containsSubject(subject))
				return true;
		}
		return false;
	}
	
	public boolean noEnrollmentsYet(){
		return this.previousEnrollment.isEmpty();
	}
	
	public double getScholarshipToPayPercentage(){
		return this.scholarship.getScholarship()/PERCENT;
	}

	public int getMinimumLoad() {
		return this.yearLevel.getMinimumLoad();
	}	
	
	public int getMaximumLoad() {
		return this.yearLevel.getMaximumLoad();
	}

}

// HAPPY BIRTHDAY! WOOF! :3
// Nyan :3
