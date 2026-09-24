package com.flm.bed.exception;

public class BedNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9050821344521889715L;
	
	public BedNotFoundException(String message) {
		super(message);
	}
}
