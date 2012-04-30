package com.orangeandbronze.schoolRegistration;

public class DuplicateSubjectException extends RuntimeException {
	
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -7256003590965894041L;

	public DuplicateSubjectException(){
		super();
	}
	
	public DuplicateSubjectException(String msg){
		super(msg);
	}
}
