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
	

	/* setter and getter methods for dueDate*/
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/* setter and getter methods for ticketId*/
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/* setter and getter methods for ticketKey*/
	public String getTicketKey() {
		return ticketKey;
	}
	public void setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
	}
	/* setter and getter methods for component*/
	public int getComponent() {
		return component;
	}
	public void setComponent(int component) {
		this.component = component;
	}
	/* setter and getter methods for ticketTypeId*/
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	/* setter and getter methods for productId*/
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/* setter and getter methods for summary*/
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/* setter and getter methods for assignee*/
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	/* setter and getter methods for reporter*/
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	/* setter and getter methods for priority*/
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/* setter and getter methods for getStatus*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/* setter and getter methods for resolution*/
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	/* setter and getter methods for created*/
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	/* setter and getter methods for updated*/
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
