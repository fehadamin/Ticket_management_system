<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.ticket.entity.*,java.util.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Department> departments = (ArrayList) request.getAttribute("departments");
	List<Product> products = (ArrayList) request.getAttribute("products");
	List<TicketType> tickettypes = (ArrayList) request.getAttribute("tickettypes");

	Ticket ticket = (Ticket) request.getAttribute("ticket");
	List<User> users = (ArrayList) request.getAttribute("users");
%>


<form action="employee-ticket-edit-store.htm" method="POST"
	onclick=" return ticket_validation();">
	<div class="box">

		<h1>Edit Ticket</h1>
		<div class="form-group">
			<label>ticket key <span class="alert">*</span></label> <input
				type="text" class="form-control" required name="project"
				value="<%=ticket.getTicketKey()%>" id="project"
				placeholder="Enter ticket key">
		</div>

		<div class="form-group">
			<label>Ticket Type <span class="alert">*</span></label> <select
				class="form-control" name="tickettype" required>
				<option value="0">Select ...</option>
				<%
					for (Department d : departments) {
				%>
				<option value="<%=d.getDepartmentId()%>"
					<%if (ticket.getTicketTypeId() == d.getDepartmentId())
					out.println("selected");%>><%=d.getDepartmentName()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group">
			<label>Product <span class="alert">*</span></label> <select
				name="product" class="form-control" required>
				<option value="0">Select ...</option>
				<%
					for (Product d : products) {
				%>
				<option value="<%=d.getProductId()%>"
					<%if (ticket.getProductId() == d.getProductId())
					out.println("selected");%>><%=d.getProductName()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group">

			<label>Component <span class="alert">*</span></label> <select
				name="component" class="form-control" required>
				<option value="0">Select ...</option>
				<%
					for (Product d : products) {
						if (d.getParent() != 0) {
				%>
				<option value="<%=d.getProductId()%>"
					<%if (ticket.getComponent() == d.getProductId())
						out.println("selected");%>><%=d.getProductName()%></option>
				<%
					}
					}
				%>
			</select>
		</div>


		<div class="form-group">
			<label>Summary <span class="alert">*</span></label>
			<textarea style="width: 100%; height: 40px;" class="form-control"
				name="summary"><%=ticket.getSummary()%></textarea>
		</div>

		<div class="form-group">
			<label>Due date *</label> <input type="text" class="form-control"
				name="dueDate" id="dueDate" value="<%=ticket.getDueDate()%>"
				required />
		</div>

		<div class="form-group">
			<label>priority <span class="alert">*</span></label> <select
				class="form-control" name="priority" required>
				<option value="Blocker"
					<%if (ticket.getPriority().equals("Blocker"))
				out.println("selected");%>>Blocker</option>
				<option value="Critical"
					<%if (ticket.getPriority().equals("Critical"))
				out.println("selected");%>>Critical</option>
				<option value="Major"
					<%if (ticket.getPriority().equals("Major"))
				out.println("selected");%>>Major</option>
			</select>
		</div>


		<div class="form-group">
			<label>Assignee<span class="alert">*</span></label> <select
				class="form-control" name="assignee" required>
				<option value="0">Select ...</option>
				<%
					for (User u : users) {
				%>
				<option value="<%=u.getName()%>"
					<%if (ticket.getAssignee().equals(u.getName()))
					out.println("selected");%>><%=u.getName()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group">
			<label>Reporter<span class="alert">*</span></label> <select
				class="form-control" name="reporter" required>
				<option value="0">Select ...</option>

				<%
					for (User u : users) {
				%>
				<option value="<%=u.getUserId()%>"
					<%if (ticket.getReporter().equals("" + u.getUserId()))
					out.println("selected");%>><%=u.getName()%></option>
				<%
					}
				%>


			</select>
		</div>
		<div class="form-group">
			<label>status <span class="alert">*</span></label> <select
				class="form-control" name="status" required>
				<option value="Open"
					<%if (ticket.getStatus().equals("Blocker"))
				out.println("selected");%>>Open</option>
				<option value="In Progress"
					<%if (ticket.getStatus().equals("In Progress"))
				out.println("selected");%>>In
					Progress</option>
				<option value="Resolved"
					<%if (ticket.getStatus().equals("Resolved"))
				out.println("selected");%>>Resolved</option>
			</select>
		</div>

		<input type="submit" name="submit" value="edit ticket"
			class="form-btn"> <input type="hidden" name="id"
			value="<%=ticket.getTicketId()%>" />
		<div id="eresult" style="color: firebrick; display: block;"></div>
	</div>
</form>

