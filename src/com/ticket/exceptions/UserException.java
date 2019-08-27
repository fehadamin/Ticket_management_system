package com.ticket.exceptions;

public class UserException extends TicketManagerException  {
	

	public UserException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UserException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
	}

}
