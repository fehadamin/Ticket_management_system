<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.ticket.entity.*"%>
<%
	ServletContext ctx = getServletContext();
	String Url = ctx.getInitParameter("url");
	String viewpath = ctx.getInitParameter("viewpath");

	List<Object> list = (ArrayList) request.getAttribute("report");
	List<Integer> flags = (ArrayList) request.getAttribute("flags");
	List<String> msgs = (ArrayList) request.getAttribute("msgs");
%>

<a href="upload-db.htm?filename=${filename}">Upload data</a>
<a href="admin-dashboard.htm">Cancel</a>

<div class="res-tab">
	<h1>all ticket table types</h1>

	<table class="myTable" id="mytable">
		<tbody>
			<tr>
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

			</tr>

		</tbody>

		<%
			for (int i = 1; i < list.size(); i++) {
				Object o = list.get(i);
				List<String> data = (ArrayList) o;
				Integer flag = flags.get(i - 1);
				String msg = msgs.get(i - 1);
				
				if (flag == 0) {
					out.println("<tr style=\"background:red;\">");
				} else {
					out.println("<tr >");
				}
				int j=0;
				for (String s : data) {
					if (j == 12) {
						out.println("<td>"+msg+"</td>");
					} else {
						out.println("<td>"+s+"</td>");
					}
					j++;
				}
				out.println("</tr>");
			}
		%>
	</table>
</div>
