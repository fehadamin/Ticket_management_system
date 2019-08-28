package com.ticket.entity;

public class Ticket {
	private int ticketId;
	private String ticketKey;
	private int ticketTypeId;
	private int productId;
	private String summary;
	private int component;
	private String assignee;
	private String reporter;
	private String priority;
	private String status;
	private String resolution;
	private String dueDate;
	private String created;
	private String updated;
	

	
	public int getTicketId() {
		return ticketId;
	}



	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}



	public String getTicketKey() {
		return ticketKey;
	}



	public void setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
	}



	public int getTicketTypeId() {
		return ticketTypeId;
	}



	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public int getComponent() {
		return component;
	}



	public void setComponent(int component) {
		this.component = component;
	}



	public String getAssignee() {
		return assignee;
	}



	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}



	public String getReporter() {
		return reporter;
	}



	public void setReporter(String reporter) {
		this.reporter = reporter;
	}



	public String getPriority() {
		return priority;
	}



	public void setPriority(String priority) {
		this.priority = priority;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getResolution() {
		return resolution;
	}



	public void setResolution(String resolution) {
		this.resolution = resolution;
	}



	public String getDueDate() {
		return dueDate;
	}



	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public String getCreated() {
		return created;
	}



	public void setCreated(String created) {
		this.created = created;
	}



	public String getUpdated() {
		return updated;
	}



	public void setUpdated(String updated) {
		this.updated = updated;
	}



	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketKey=" + ticketKey + ", ticketTypeId=" + ticketTypeId
				+ ", productId=" + productId + ", summary=" + summary + ", component=" + component + ", assignee="
				+ assignee + ", reporter=" + reporter + ", priority=" + priority + ", status=" + status
				+ ", resolution=" + resolution + ", dueDate=" + dueDate + ", created=" + created + ", updated="
				+ updated + "]";
	}

}
