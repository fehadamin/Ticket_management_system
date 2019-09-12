<%@ page language="java" contentType="text/html; charget=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,com.ticket.entity.*,com.ticket.models.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<User> users = (ArrayList) request.getAttribute("users");
	TicketTypesModel ttm = new TicketTypesModel();
	ProductModel pm = new ProductModel();
%>

<div>

	<form method="post" action="filter_ticket_side.htm">
		<label class="filterstext"> Status</label> <select name="status">
			<option value="0">select..</option>
			<option value="Open">Open</option>
			<option value="In Progress">In Progress</option>
			<option value="Resolved">Resolved</option>
		</select> <label class="filterstext"> Priority</label> <select name="priority">
			<option value="0">select..</option>
			<option value="Blocker">Blocker</option>
			<option value="Critical">Critical</option>
			<option value="Major">Major</option>
		</select> <label class="filterstext"> Assignee</label> <select name="assignee">
			<option value="0">select..</option>
			<%
				for (User u : users) {
			%>
			<option value="<%=u.getName()%>"><%=u.getName()%></option>
			<%
				}
			%>

		</select> <input type="submit" class="filters" name="filter" /> <a
			class="filters" href="admin-ticket-all.htm">reset </a>
	</form>
</div>




<div class="res-tab">
	<h1>all ticket table</h1>
	<a href="report.htm" >Generate excel file</a>
	<button id="downloadReport">Download</button>
	<table id="mytable" class="myTable">
		<thead>
			<!--  <th> Key</th> -->
			<th>ticket key</th>
			<th>ticket type</th>
			<th>product</th>
			<th>Component</th>
			<th>summary</th>
			<th>priority</th>
			<th>Assignee</th>
			<th>Reporter</th>
			<th>status</th>
			<th>Resolution</th>
			<th>created</th>
			<th>updated</th>
			<th>Action</th>


		</thead>
		<tbody>
			<%
				List<Ticket> tickets = (ArrayList) request.getAttribute("tickets");
				for (Ticket t : tickets) {
					System.out.println(t.toString());
					Product p = pm.getById(t.getProductId());
					Product c = pm.getById(t.getComponent());
					TicketType tt = ttm.getById(t.getTicketTypeId());
			%>
			<tr>
				<td><a href="<%=Url%>single-ticket.htm?id=<%=t.getTicketId()%>" target="_blank"><%=t.getTicketKey()%></a></td>
				<td><%=tt.getTicketName()%></td>
				
				<td>
					<%
						if (t.getProductId() == 0) {
								/* out.println(t.getProducts()); */
								String[] ids = t.getProducts().split(",", -2);
								for (String id : ids) {
									p = pm.getById(Integer.parseInt("" + id.charAt(0)));
									out.println(p.getProductName() + ",");

								}
							} else {
								out.println(p.getProductName());
							}
					%>
				</td>
				<td><%=c.getProductName()%></td>
				<td><%=t.getSummary()%></td>
				<td><%=t.getPriority()%></td>
				<td><%=t.getAssignee()%></td>
				<td><%=t.getReporter()%></td>
				<td><%=t.getStatus()%></td>
				<td><%=t.getDueDate()%></td>
				<td><%=t.getCreated()%> </td>
				<td><%=t.getUpdated() %></td>
				<td><a
					href="<%=Url%>admin-ticket-edit-form.htm?id=<%=t.getTicketId()%>">edit</a>
					<a href="<%=Url%>admin-ticket-remove.htm?id=<%=t.getTicketId()%>">delete</a>
				</td>

			</tr>
			<%
				}
				session.setAttribute("tickets",tickets);
				%>
		</tbody>
	</table>
</div>
