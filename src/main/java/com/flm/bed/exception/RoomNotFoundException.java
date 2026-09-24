package com.flm.bed.exception;

public class RoomNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2432135793732781578L;
	
	public RoomNotFoundException(String message) {
		super(message);
	}
}
