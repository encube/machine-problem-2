package com.orangeandbronze.schoolRegistration;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
	private final Student student;
	private final double MISCELLANEOUS = 2000;

	private List<Section> sectionTaken =  new ArrayList<Section>();
	
	public Enrollment(Student student){
		this.student = student;
	}

	
	public Student getStudent() {
		return this.student;
	}

	// TODO nested IFs are BAD.
	public void enlist(Section section) { 
		if(section.inspectQualificationOf(this.student)){
			if(!section.contains(this)){
				if(this.willOverloadWith(section)){
					if (!this.isCollidingWith(section)){
						if(section.notYetFull()){
								sectionTaken.add(section);
								section.add(this);
						}
						else{
							throw new MaximumSectionCapacityException(section + " is Full. " + section.sectionCapacity() + " enrollees");
						}
					}
					else{
						throw new SectionCollideException(section + " has Collision. The section is may be conflicting with other section in " + section.getSchedule() + " or the section's subject " + section.getSubject() + "is already taken in current enrollment.");
					}
				}
				else{
					throw new MaximumLoadException(this.student + " is overload");
				}
			}
			else{
				throw new AlreadyEnrolledException(this.student + " is already enrolled in " + section);
			}
		}
		else{
			throw new PrerequisitesNotCompliedException(student + " haven't accomplished the " + section.getSubject() + " prerquisites");
		}
	}

	
	private boolean willOverloadWith(Section section) {
		if((this.getTotalUnits()+section.getUnits()) <= this.student.getMaximumLoad())
			return true;
		return false;
	}


	public boolean isCollidingWith(Section section) {
		for(Section sec : sectionTaken){
			if(sec.collide(section))
				return true;
		}
		return false;
	}
	
	
	public boolean containsSection(Section section) {
		return sectionTaken.contains(section);
	}


	public boolean containsSubject(Subject sub) {
		for(Section sec : sectionTaken){
			Subject subject = sec.getSubject();
			if(subject.equals(sub))
				return true;
		}
		return false;
	}

	// TODO USE BigDecimal!!!!
	public double assess() {
		if(this.isNotLessThanMinimumLoad()){
			double priviledge = this.student.getScholarshipToPayPercentage();
			double totalSubjectFee =  0;
			for(Section section : sectionTaken)
				totalSubjectFee += section.getSubjectFee();
			
			return (totalSubjectFee-(totalSubjectFee*priviledge)) + MISCELLANEOUS;
		}
		else{
			throw new UnsatisfiedRequiredUnitRangeException(this.getTotalUnits() + " total units. enrollment is underload/overload situation");
		}
	}

	private boolean isNotLessThanMinimumLoad() {
		if(this.getTotalUnits() >= this.student.getMinimumLoad())
			return true;
		return false;
	}


	private int getTotalUnits() {
		int TotalUnits = 0;
		for(Section section : sectionTaken)
			TotalUnits += section.getUnits();
		return TotalUnits;
	}
	
}
