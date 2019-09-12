<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.ticket.entity.*,java.util.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Product> products = (ArrayList) request.getAttribute("products");
	List<TicketType> tickettypes = (ArrayList) request.getAttribute("tickettypes");
	Ticket ticket = (Ticket) request.getAttribute("ticket");
	List<User> users = (ArrayList) request.getAttribute("users");
%>

<style>
.form-group label {
	font-size: 18px;
	font-weight: 600;
}
</style>
<div>
	<div class="box"
		style="width: 80% !important; display: flex; flex-direction: column;">

		<h1><%=ticket.getTicketKey()%></h1>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">

					<label>Ticket Type : </label>
					<%
						for (TicketType d : tickettypes) {
							if (ticket.getTicketTypeId() == d.getTicketTypeId()) {
								out.println(d.getTicketName());
							}
						}
					%>

				</div>
				<div class="form-group">
					<label>Product : </label>
					<%
						for (Product p : products) {
							if (p.getParent() == 0) {
								if (p.getProductId() == ticket.getProductId()) {
									out.println(p.getProductName());
								}
							}
						}
					%>
					</select>
				</div>
				<div class="form-group">
					<label>Component : </label>
					<%
						for (Product p : products) {
							if (p.getParent() != 0) {
								if (p.getProductId() == ticket.getComponent()) {
									out.println(p.getProductName());
								}
							}
						}
					%>
				</div>
				<div class="form-group">
					<label>Assignee : </label>
					<%
						for (User u : users) {
							if (ticket.getAssignee().equals(u.getName()))
								out.println(u.getName());
						}
					%>
				</div>
				<div class="form-group">
					<label>Reporter : </label>
					<%
						for (User u : users) {
							if (ticket.getReporter().equals(u.getName()))
								out.println(u.getName());
						}
					%>
				</div>
				<div class="form-group">
					<label>Created at: </label>
					<%=ticket.getCreated()%>
				</div>
				<%
					if (!ticket.getStatus().equals("Closed")) {
				%>
				<a
					href="<%=Url%>admin-ticket-edit-form.htm?id=<%=ticket.getTicketId()%>"
					class="form-btn">Edit</a>
				<%
					}
				%>
			</div>

			<div class="col-md-6">

				<div class="form-group">
					<label>Summary <span class="alert">*</span></label>
					<p><%=ticket.getSummary()%></p>
				</div>
				<div class="form-group">
					<label>Due date *</label>
					<p><%=ticket.getDueDate()%></p>
				</div>

				<div class="form-group">
					<label>priority <span class="alert">*</span></label>
					<p><%=ticket.getPriority()%></p>
				</div>
				<div class="form-group">
					<label>Status : </label>
					<p><%=ticket.getStatus()%></p>
				</div>
			</div>
		</div>


	</div>


</div>

