package com.orangeandbronze.schoolRegistration;

public class MaximumLoadException extends RuntimeException {
	
	/**
	 * generate serialVersionUID
	 */
	private static final long serialVersionUID = -6487453554355375555L;

	public MaximumLoadException(){
		super();
	}
	
	public MaximumLoadException(String msg){
		super(msg);
	}

}
