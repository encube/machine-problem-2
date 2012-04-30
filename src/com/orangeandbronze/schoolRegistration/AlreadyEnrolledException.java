package com.orangeandbronze.schoolRegistration;

public class AlreadyEnrolledException extends RuntimeException {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 2236286726494829995L;

	public AlreadyEnrolledException(){
		super();
	}
	
	public AlreadyEnrolledException(String msg){
		super(msg);
	}
}
