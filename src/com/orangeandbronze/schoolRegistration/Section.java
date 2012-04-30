package com.orangeandbronze.schoolRegistration;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private Teacher teacher;
	private Subject subject;
	private Schedule schedule;
	private int MAXIMUM_NUMBER_OF_ENROLLEES = 40;
	
	private List<Enrollment> enrollees =  new ArrayList<Enrollment>();
	
	private Section(Teacher teacher, Subject subject, Schedule schedule){
		this.teacher = teacher;
		this.subject = subject;
		this.schedule = schedule;
	}
	
	// TODO use Builder Design Pattern instead of Static Factory. Violates SRP.
	public static Section createSection(Teacher teacher, Schedule schedule, Subject subject) {
		if(teacher.containsSchedule(schedule)){
			throw new ScheduleConflictException("teacher " + teacher + " is unavailable at " + schedule);
		}
		else{
			Section createdSection = new Section(teacher, subject, schedule);
			teacher.add(createdSection);
			return createdSection;
		}
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

	public double getSubjectFee() {
		return this.subject.getSubjectFee();
	}	
	
	@Override
	public String toString() {
		return teacher + " " + subject + " " +schedule;
	}

	public void add(Enrollment enrollment) {
		enrollees.add(enrollment);
	}

	public boolean contains(Enrollment enrollment) {
		return enrollees.contains(enrollment);
	}

	public int countEnrollees() {
		return enrollees.size();
	}
	
	public int sectionCapacity(){
		return MAXIMUM_NUMBER_OF_ENROLLEES;
	}

	// TODO put responsibility to student or enrollment
	public boolean inspectQualificationOf(Student student) {
		return subject.prerequisitesHasBeenTakenBy(student);
	}

	public int getUnits() {
		return subject.getUnits();
	}

	public boolean notYetFull() {
		if(this.sectionCapacity()>this.countEnrollees())
			return true;
		return false;
	}

	public boolean collide(Section section){
		if(schedule.equals(section.getSchedule()))
			return true;
		if(subject.equals(section.getSubject()))
			return true;
		return false;
	}

	public void setSectionCapacity(int i) {
		this.MAXIMUM_NUMBER_OF_ENROLLEES = i;
		
	}
}
