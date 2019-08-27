package com.ticket.exceptions;

public class TicketTypeException extends TicketManagerException {

private static final long serialVersionUID = 1L;
	
	
	
	public TicketTypeException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public TicketTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public TicketTypeException(String message,Throwable cause) {
		super(message,cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param cause
	 */
	public TicketTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}

