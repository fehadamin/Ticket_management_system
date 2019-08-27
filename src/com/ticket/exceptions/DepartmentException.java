package com.ticket.exceptions;

public class DepartmentException extends TicketManagerException {

	/**
	 * 
	 */
	public DepartmentException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public DepartmentException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DepartmentException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public DepartmentException(Throwable cause) {
		super(cause);
	}
}