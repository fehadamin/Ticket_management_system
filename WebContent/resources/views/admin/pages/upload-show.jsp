<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.ticket.entity.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Object> list = (ArrayList) request.getAttribute("report");
%>

<a href="upload-db.htm?filename=${filename}">Upload data</a>
<a href="admin-dashboard.htm">Cancel</a>

<div class="res-tab">
	<h1>all ticket table types</h1>

	<table class="myTable" id="mytable">
		<tbody>
			<tr>
				<th>ticket Type</th>
				<th>Key</th>
				<th>Summary</th>
				<th>Assignee</th>
				<th>Reporter</th>
				<th>Priority</th>
				<th>Status</th>
				<th>Resolution</th>
				<th>Created</th>
				<th>Updated</th>

			</tr>

		</tbody>

		<%
			for(int i=1;i<list.size();i++) {
				Object o = list.get(i);
				List<String> data = (ArrayList) o;
				out.println("<tr>");
							for (String s : data) {
					%>
					<td><%=s%></td>
					
					<%
			}
				out.println("</tr>");
			}
		%>
	</table>
</div>
