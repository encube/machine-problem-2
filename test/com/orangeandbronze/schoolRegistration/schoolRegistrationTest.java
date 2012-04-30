package com.orangeandbronze.schoolRegistration;

import static org.junit.Assert.*;
import static com.orangeandbronze.schoolRegistration.Scholarship.*;
import static com.orangeandbronze.schoolRegistration.YearLevel.*;
import static com.orangeandbronze.schoolRegistration.SubjectType.*;
import static com.orangeandbronze.schoolRegistration.Days.*;
import static com.orangeandbronze.schoolRegistration.Hours.*;

import org.junit.Test;

public class schoolRegistrationTest {

	@Test
	public void createStudent() {
		Student student = new Student(120001, JUNIOR, REGULAR);
		int idNumber = student.getIdNumber();
		assertEquals(120001, idNumber);
		YearLevel yearLevel = student.getYearLevel();
		assertEquals(JUNIOR, yearLevel);
		Scholarship scholarship = student.getScholarship();
		assertEquals(REGULAR, scholarship);
	}

	@Test
	public void createTeacher(){
		Teacher teacher = new Teacher(120001);
		int idNumber = teacher.getIdNumber();
		assertEquals(120001, idNumber);
	}
	
	@Test
	public void createSubject(){
		Subject subject = new Subject("CSC101", "introduction to programming", UNDERGRADUATE);
		String name = subject.getCourseCode();
		assertEquals("CSC101", name);
		SubjectType subjectType = subject.getSubjectType();
		assertEquals(UNDERGRADUATE, subjectType);
		assertTrue(3==subject.getUnits());
	}
	
	@Test
	public void createSchedule(){
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Days days = schedule.getDays();
		assertEquals(MON_THUR, days);
		Hours hours = schedule.getHours();
		assertEquals(FIRST_PERIOD, hours);
	}
	
	
	@Test
	public void createEnrollment(){
		Student student = new Student(120932, JUNIOR, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		Student studentFromGetter = enrollment.getStudent();
		assertEquals(student, studentFromGetter);
	}
	
	
	@Test
	public void addPrerequisiteToASubject(){
		Subject subject = new Subject("CSC124", "introduction to programming", UNDERGRADUATE);
		Subject prerequisiteSubject = new Subject("CSC101", "introduction to programming", UNDERGRADUATE);
		
		subject.addPrerequisite(prerequisiteSubject);
		
		assertTrue(subject.containsPrerequisite(prerequisiteSubject));
	}
	
	@Test
	public void countEnrolleesInSection() {
		Teacher teacher = new Teacher(123453);
		Student student = new Student(120039, FRESHMAN, FULL_SCHOLAR);
		
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC100", "introduction to programming", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		
		Enrollment enrollment = new Enrollment(student);
		
		enrollment.enlist(section);
		
		assertTrue("enrollee count was " + section.countEnrollees(), 1 == section.countEnrollees());
	}
	
//just printing toStrings	
	@Test
	public void  subjectToString(){
		Subject subject = new Subject("CSC101", "introduction to programming", UNDERGRADUATE);
		assertEquals("CSC101 introduction to programming", subject.toString());
	}
	
	@Test
	public void  scheduleToString(){
		Schedule schedule = new Schedule(TUE_FRI, THIRD_PERIOD);
		assertEquals("TUE_FRI THIRD_PERIOD", schedule.toString());
	}
	
	@Test
	public void  sectionToString(){
		Teacher teacher = new Teacher(132453);
		Subject subject = new Subject("CSC199", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(TUE_FRI, THIRD_PERIOD);
		Section section = Section.createSection(teacher, schedule, subject);
		assertEquals(teacher.toString() + " " + subject.toString() + " " +schedule.toString(), section.toString());
	}
//	just printing toStrings ended
	
	
	
// 1. Student enroll to a section
	@Test
	public void enrollStudentInSection() {
		Student student = new Student(120394, JUNIOR, FULL_SCHOLAR);
		Enrollment enrollment = new Enrollment(student);
		
		Teacher teacher = new Teacher(109298);
		Subject subject = new Subject("CSC145", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(MON_THUR, FOURTH_PERIOD);
		Section section = Section.createSection(teacher, schedule, subject);
		
		enrollment.enlist(section);
		
		assertTrue(section.contains(enrollment));
		assertTrue(enrollment.containsSection(section));
	}
	
// extension 1.a. system detects schedule conflict.	
	@Test(expected=SectionCollideException.class)
	public void preventingScheduleConflictsDuringEnrollment() {
		Student student = new Student(129485, JUNIOR, REGULAR);
		
		Enrollment enrollment = new Enrollment(student);
		
		Teacher teacher = new Teacher(98636);
		Subject subject = new Subject("CSC145", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(MON_THUR, FOURTH_PERIOD);
		Section section = Section.createSection(teacher, schedule, subject);
		
		Teacher teacher2 = new Teacher(119273);
		Subject subject2 = new Subject("CSC142", "introduction to programming", UNDERGRADUATE);
		Schedule schedule2 = new Schedule(MON_THUR, FOURTH_PERIOD);
		Section section2 = Section.createSection(teacher2, schedule2, subject2);
		
		enrollment.enlist(section);
		enrollment.enlist(section2);
	}

// extension 1.b. system detects prerequisite not yet taken.
	@Test(expected=PrerequisitesNotCompliedException.class)
	public void preventEnrollmentWhenPrerequisitesAreNotTaken() {
		Teacher teacher = new Teacher(98635);
		
		Subject subject = new Subject("CSC124", "introduction to programming", UNDERGRADUATE);
		Subject prerequisiteSubject = new Subject("CSC101", "introduction to programming", UNDERGRADUATE);
		subject.addPrerequisite(prerequisiteSubject);
		
		Schedule schedule = new Schedule(MON_THUR, FIFTH_PERIOD);
		
		Section section = Section.createSection(teacher, schedule, subject);
		
		Student student = new Student(110094, SOPHOMORE, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		enrollment.enlist(section);
	}
	
//business rule 3 not followed
	@Test(expected=AlreadyEnrolledException.class)
	public void enrollingInSameSectionTwice(){
		Teacher teacher = new Teacher(87635);
		
		Subject subject = new Subject("CSC124", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(MON_THUR, FIFTH_PERIOD);
		
		Section section = Section.createSection(teacher, schedule, subject);
		
		Student student = new Student(110983, SOPHOMORE, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		enrollment.enlist(section);
		enrollment.enlist(section);
	}

//business 5 not followed.
	@Test(expected=MaximumLoadException.class)
	public void enrollingAStudentWithMaximumLoad(){
		Teacher teacher = new Teacher(68293);
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC151", "database management", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		
		Schedule schedule2 = new Schedule(MON_THUR, SECOND_PERIOD);
		Subject subject2 = new Subject("CSC121", "algorithm", UNDERGRADUATE);
		Section section2 = Section.createSection(teacher, schedule2, subject2);
		
		Schedule schedule3 = new Schedule(MON_THUR, THIRD_PERIOD);
		Subject subject3 = new Subject("CSC141", "automata and computability", UNDERGRADUATE);
		Section section3 = Section.createSection(teacher, schedule3, subject3);
		
		Schedule schedule4 = new Schedule(MON_THUR, FOURTH_PERIOD);
		Subject subject4 = new Subject("CSC102", "advance programming", UNDERGRADUATE);
		Section section4 = Section.createSection(teacher, schedule4, subject4);
		
		Schedule schedule5 = new Schedule(MON_THUR, FIFTH_PERIOD);
		Subject subject5 = new Subject("CSC171", "artificial intellegence", UNDERGRADUATE);
		Section section5 = Section.createSection(teacher, schedule5, subject5);
		
		Schedule schedule6 = new Schedule(MON_THUR, SIXTH_PERIOD);
		Subject subject6 = new Subject("CSC181", "introduction to software engineering", UNDERGRADUATE);
		Section section6 = Section.createSection(teacher, schedule6, subject6);
		
		Schedule schedule7 = new Schedule(TUE_FRI, FIRST_PERIOD);
		Subject subject7 = new Subject("CSC153", "compilers", UNDERGRADUATE);
		Section section7 = Section.createSection(teacher, schedule7, subject7);
		
		Student student = new Student(120948, FRESHMAN, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		enrollment.enlist(section7);
		enrollment.enlist(section6);
		enrollment.enlist(section5);
		enrollment.enlist(section4);
		enrollment.enlist(section3);
		enrollment.enlist(section2);
		enrollment.enlist(section);
		
	}
	
//if enrolling a subject twice
	@Test(expected=SectionCollideException.class)
	public void enrollingDuplicateSubject(){
		Teacher teacher = new Teacher(78987);
		Subject subject = new Subject("CSC151", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(MON_THUR, SIXTH_PERIOD);
		Schedule schedule2 = new Schedule(MON_THUR, FIRST_PERIOD);
		
		Section section = Section.createSection(teacher, schedule, subject);
		Section section2 = Section.createSection(teacher, schedule2, subject);
		
		Student student = new Student(112938, SOPHOMORE, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		enrollment.enlist(section);
		enrollment.enlist(section2);
	}
	
	
	
//2. create new section
	@Test
	public void createSection(){
		Teacher teacher = new Teacher(98787);
		Subject subject = new Subject("CSC101", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(TUE_FRI, SECOND_PERIOD);
		Section section = Section.createSection(teacher, schedule, subject);
		
		assertTrue(teacher.containSection(section));
		
		Teacher teacherFromGetter = section.getTeacher();
		assertEquals(teacher, teacherFromGetter);
		Subject subjectFromGetter =  section.getSubject();
		assertEquals(subject, subjectFromGetter);
		Schedule scheduleFromGetter = section.getSchedule();
		assertEquals(schedule, scheduleFromGetter);
		
	}
	
//business rule bullet 2 is not followed
	@Test(expected=MaximumSectionCapacityException.class)
	public void enrollToAFullSectionWithACapacityOfTwo() {
		Teacher teacher = new Teacher(123422);
		
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC100", "introduction to programming", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		section.setSectionCapacity(2);
		
		Student student = new Student(129485, FRESHMAN, FULL_SCHOLAR);
		Enrollment enrollment = new Enrollment(student);
		
		Student student2 = new Student(123456, FRESHMAN, FULL_SCHOLAR);
		Enrollment enrollment2 = new Enrollment(student2);
		
		Student student3 = new Student(129867, FRESHMAN, FULL_SCHOLAR);
		Enrollment enrollment3 = new Enrollment(student3);
		
		enrollment.enlist(section);
		enrollment2.enlist(section);
		enrollment3.enlist(section);
	}
	
//business rule bullet 5 is not followed
	@Test(expected=ScheduleConflictException.class)
	public void preventScheduleConflictForTeacher(){
		Teacher teacher = new Teacher(120111);
		Subject subject = new Subject("CSC145", "introduction to programming", UNDERGRADUATE);
		Schedule schedule = new Schedule(MON_THUR, FOURTH_PERIOD);
		Section section = Section.createSection(teacher, schedule, subject);
		
		Subject subject2 = new Subject("CSC145", "introduction to programming", UNDERGRADUATE);
		Schedule schedule2 = new Schedule(MON_THUR, FOURTH_PERIOD);
		Section section2 = Section.createSection(teacher, schedule2, subject2);

	}
	
	
	
//3 Tuition Fee Assessment
	@Test
	public void assessEnrollment(){
		Teacher teacher = new Teacher(119284);
		Student student = new Student(91234, SENIOR, FULL_SCHOLAR);
		Enrollment enrollment = new Enrollment(student);
		
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC121", "introduction to programming", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		
		enrollment.enlist(section);
		double TuitionFee = enrollment.assess();
		
		assertTrue(2000==TuitionFee);
	}
	
	@Test
	public void assessManyEnrollment(){
		Teacher teacher = new Teacher(119284);
		Student student = new Student(91234, SENIOR, REGULAR);
		Enrollment enrollment = new Enrollment(student);
		
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC121", "introduction to programming", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		Section section2 = Section.createSection(teacher, new Schedule(Days.WED_SAT, Hours.SECOND_PERIOD), new Subject("CS23", "Automata and Language Theory", UNDERGRADUATE));
		Section section3 = Section.createSection(teacher, new Schedule(Days.WED_SAT, Hours.FIRST_PERIOD), new Subject("CS42.1", "Artificial Intelligence", UNDERGRADUATE));
		Section section4 = Section.createSection(teacher, new Schedule(Days.WED_SAT, Hours.THIRD_PERIOD), new Subject("CS24", "Models of Computation", GRADUATE));
				
		
		enrollment.enlist(section);
		enrollment.enlist(section2);
		enrollment.enlist(section3);
		enrollment.enlist(section4);
		double TuitionFee = enrollment.assess();
		
		assertTrue(12000==TuitionFee);
	}
	
//fail in number 1
	@Test(expected=UnsatisfiedRequiredUnitRangeException.class)
	public void assessEnrollmentUnsatisfiedUnitRequirement(){
		Teacher teacher = new Teacher(90983);
		Student student = new Student(120485, FRESHMAN, FULL_SCHOLAR);
		Enrollment enrollment = new Enrollment(student);
		
		Schedule schedule = new Schedule(MON_THUR, FIRST_PERIOD);
		Subject subject = new Subject("CSC121", "introduction to programming", UNDERGRADUATE);
		Section section = Section.createSection(teacher, schedule, subject);
		
		enrollment.enlist(section);
		double TuitionFee = enrollment.assess();
	}
	
}