package com.ticket.exceptions;

public class TicketException extends TicketManagerException {

private static final long serialVersionUID = 1L;
	
	
	
	public TicketException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public TicketException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public TicketException(String message,Throwable cause) {
		super(message,cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param cause
	 */
	public TicketException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}

