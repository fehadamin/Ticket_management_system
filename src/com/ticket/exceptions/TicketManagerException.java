package com.ticket.exceptions;

public class TicketManagerException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	
	
	public TicketManagerException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public TicketManagerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public TicketManagerException(String message,Throwable cause) {
		super(message,cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param cause
	 */
	public TicketManagerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}

	
	
	


