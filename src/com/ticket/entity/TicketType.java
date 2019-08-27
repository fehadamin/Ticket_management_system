package com.ticket.entity;

public class TicketType {
	
	private int ticketTypeId;
	private String ticketName;

	/* setter and getter methods for ticketTypeId*/
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	/* setter and getter methods for ticketName*/
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	@Override
	public String toString() {
		return "TicketType [ticketTypeId=" + ticketTypeId + ", ticketName=" + ticketName + ", getTicketTypeId()="
				+ getTicketTypeId() + ", getTicketName()=" + getTicketName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
