package com.orangeandbronze.schoolRegistration;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private final int idNumber;
	
	private List<Section> sectionTaken =  new ArrayList<Section>();
	
	public Teacher(int idNumber){
		this.idNumber = idNumber;
	}

	public int getIdNumber() {
		return this.idNumber;
	}


	@Override
	public String toString() {
		return "" + idNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumber;
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
		Teacher other = (Teacher) obj;
		if (idNumber != other.idNumber)
			return false;
		return true;
	}

	// blockage is present in this function to prevent teacher from being added with a section that already have a teacher.
	public void add(Section section) {
		if(this.equals(section.getTeacher())){
			if(!sectionTaken.contains(section))
				sectionTaken.add(section);
		}
		else{
			throw new SectionAlreadyHaveTeacherException(section + " already have a teacher.");
		}
	}
	

	public boolean containsSchedule(Schedule schedule) {
		if(sectionTaken.isEmpty())
			return false;
		for(Section sec : sectionTaken){
			if(schedule.equals(sec.getSchedule()))
				return true;
		}
		return false;
	}


	public boolean containSection(Section section) {
		return sectionTaken.contains(section);
	}


}
// wahahaha
//master nananananananan
//novo
