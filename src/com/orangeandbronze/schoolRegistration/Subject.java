package com.orangeandbronze.schoolRegistration;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private final String courseCode;
	private final String descriptiveTitle;
	private final SubjectType subjectType;
	private int units = 3;
	
	private List<Subject> prerequisite =  new ArrayList<Subject>();
	
	public Subject(String courseCode, String descriptiveTitle, SubjectType subjectType){
		this.courseCode = courseCode;
		this.descriptiveTitle = descriptiveTitle;
		this.subjectType = subjectType;
	}

	public String getCourseCode() {
		return this.courseCode;
	}
	
	public String getDescriptiveTile() {
		return this.descriptiveTitle;
	}

	public SubjectType getSubjectType() {
		return this.subjectType;
	}
	
	public void setUnit(int unit){
		this.units = unit;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((courseCode == null) ? 0 : courseCode.hashCode());
		result = prime
				* result
				+ ((descriptiveTitle == null) ? 0 : descriptiveTitle.hashCode());
		result = prime * result
				+ ((prerequisite == null) ? 0 : prerequisite.hashCode());
		result = prime * result
				+ ((subjectType == null) ? 0 : subjectType.hashCode());
		result = prime * result + units;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		if (descriptiveTitle == null) {
			if (other.descriptiveTitle != null)
				return false;
		} else if (!descriptiveTitle.equals(other.descriptiveTitle))
			return false;
		if (prerequisite == null) {
			if (other.prerequisite != null)
				return false;
		} else if (!prerequisite.equals(other.prerequisite))
			return false;
		if (subjectType != other.subjectType)
			return false;
		if (units != other.units)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return courseCode + " " + descriptiveTitle;
	}

	public void addPrerequisite(Subject prerequisiteSubject) {
		prerequisite.add(prerequisiteSubject);
	}

	public boolean containsPrerequisite(Subject prerequisiteSubject) {
		return prerequisite.contains(prerequisiteSubject);
	}

	public int getUnits() {
		return this.units;
	}

	public boolean prerequisitesHasBeenTakenBy(Student student) {
		if(this.prerequisite.isEmpty()){
			return true;
		}
		
		if(student.noEnrollmentsYet()){
			return false;
		}

		// TODO indirect checking
		int prerequisitesTaken = 0;
		for(Subject sub : this.prerequisite){
			if(student.hasTakenSubject(sub)){
				prerequisitesTaken++;
			}
		}
		if(prerequisitesTaken == this.prerequisite.size()){
			return true;
		}
		
		return false;
	}

	public double getSubjectFee() {
		return this.subjectType.getFeePerSection();
	}
	
	
}
