package com.orangeandbronze.schoolRegistration;

public class SectionAlreadyHaveTeacherException extends RuntimeException {
	
	/**
	 * generate serialVersionUID
	 */
	private static final long serialVersionUID = 7212031224078830601L;

	public SectionAlreadyHaveTeacherException(){
		super();
	}
	
	public SectionAlreadyHaveTeacherException(String msg){
		super(msg);
	}

}
