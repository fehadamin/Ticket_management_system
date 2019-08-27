package com.ticket.exceptions;

public class ProductException extends TicketManagerException {

	
	
	public ProductException() {
		
	}
	/**
	 * 
	 * @param message
	 */
	public ProductException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ProductException(String message,Throwable cause) {
		super(message,cause);
	}
	/**
	 * 
	 * @param cause
	 */
	public ProductException(Throwable cause) {
		super(cause);
	}
}
