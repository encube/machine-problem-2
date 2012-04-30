package com.orangeandbronze.schoolRegistration;

public class ScheduleConflictException extends RuntimeException {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 8429985894771666013L;

	public ScheduleConflictException(){
		super();
	}
	
	public ScheduleConflictException(String msg){
		super(msg);
	}
}
