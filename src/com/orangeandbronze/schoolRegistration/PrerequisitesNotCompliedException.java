package com.orangeandbronze.schoolRegistration;

public class PrerequisitesNotCompliedException extends RuntimeException {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -297963004407577790L;

	public PrerequisitesNotCompliedException(){
		super();
	}
	
	public PrerequisitesNotCompliedException(String msg){
		super(msg);
	}
}
