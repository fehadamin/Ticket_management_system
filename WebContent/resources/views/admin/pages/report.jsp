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
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
			<th>Message</th>


		</thead>
		<tbody>
			<%
				response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","inline;filename=xyz.xls");
				List<Ticket> tickets = (ArrayList) request.getAttribute("tickets");
			ArrayList<Ticket> ticketss = (ArrayList<Ticket>) session.getAttribute("tickets");
				for (Ticket t : tickets) {
					System.out.println(t.toString());
					Product p = pm.getById(t.getProductId());
					Product c = pm.getById(t.getComponent());
					TicketType tt = ttm.getById(t.getTicketTypeId());
			%>
			<tr>
				<td><%=t.getTicketKey()%></td>
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
				<td></td>
				
			</tr>
			<%
				}
				%>
		</tbody>
	</table>


</body>
</html>