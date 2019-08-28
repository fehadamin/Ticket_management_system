<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<%-- <div class="form-group">
			<label>ticket key <span class="alert">*</span></label> <input
				type="text" class="form-control" required name="project"
				<c:out value="${ticket.getTicketKey()}"/> id="project"
				placeholder="Enter ticket key">
		</div> --%>

		<div class="form-group">
			<label>Ticket Type <span class="alert">*</span></label> <select
				class="form-control" name="tickettype" required>
				<option value="0">Select ...</option>

				<c:forEach var="d" items="${departments}">

					<option value="<c:out value="${d.getDepartmentId()}"/>"
						<c:if  test= "${ ticket.getTicketKey()} == ${d.getDepartmentId()}">
					
								<c:out value="selected" />
							</c:if>>
						<c:out value="${d.getDepartmentName()}" />
					</option>
				</c:forEach>


			</select>
		</div>

		<div class="form-group">
			<label>Product <span class="alert">*</span></label> <select
				name="product" class="form-control" required>
				<option value="0">Select ...</option>

				<c:forEach var="d" items="${products}">
					<option <c:out value="${d.getProductId()}"/>
						<c:if test = "${ticket.getProductId()} == ${d.getProductId()}" >
										<c:out value="selected" />  </c:if>>
						<c:out value="${d.getProductName()}" />
					</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">

			<label>Component <span class="alert">*</span></label> <select
				name="component" class="form-control" required>
				<option value="0">Select ...</option>
				<c:forEach var="d" items="${products}">
					<c:if
						test="${d.getParent() != 0 }" >
				 
						<option <c:out value="${d.getProductId()}"/>
							<c:if test = "${ticket.getComponent()} == ${d.getProductId()}" >
											<c:out value="selected" />  </c:if>>
							<c:out value="${d.getProductName()}" />
						</option>
					</c:if>
				</c:forEach>
				
			
			</select> 
		</div>


		<div class="form-group">
			<label>Summary <span class="alert">*</span></label>
			<textarea style="width: 100%; height: 40px;" class="form-control"
				name="summary">${ticket.getSummary()}</textarea>
		</div>

		<div class="form-group">
			<label>Due date *</label> <input type="text" class="form-control"
				name="dueDate" id="dueDate" value="${ticket.getDueDate()}"
				required />
		</div>

		<div class="form-group">
			<label>priority <span class="alert">*</span></label> <select
				class="form-control" name="priority" required>





				<option value="Blocker"
					<c:if test="${ticket.getPriority() == 'Blocker'}">
							<c:out value="selected" /> Blocker
				</c:if>>Blocker
				</option>

				<option value="Critical"
					<c:if test="${ticket.getPriority() == 'Critical'}">
							<c:out value="selected" /> Critical
				</c:if>>Critical
				</option>
				
			
					<option value="Major"
					<c:if test="${ticket.getPriority() == 'Major'}">
							<c:out value="selected" /> Major
				</c:if>   >Major
				</option>
					
				
				
				
				
			</select>
		</div>


		<div class="form-group">
			<label>Assignee<span class="alert">*</span></label> <select
				class="form-control" name="assignee" required>
				<option value="0">Select ...</option>
				
				<c:forEach var="u" items="${users}">
				
					<option value="<c:out value="${u.getName()}"/>"
							<c:if test = "${ticket.getAssignee()} == ${u.getName()}" >
											<c:out value="selected" />  </c:if> >
							<c:out value="${u.getName()}" />
						</option>
				
				</c:forEach>
				
			</select>
		</div>
 
		<div class="form-group">
			<label>Reporter<span class="alert">*</span></label> <select
				class="form-control" name="reporter" required>
				<option value="0">Select ...</option>

				<c:forEach var="u" items="${users}">
				
					<option value="<c:out value="${u.getName()}"/>"
							<c:if test = "${ticket.getReporter()} == ${u.getName()}" >
											<c:out value="selected" />  </c:if> >
							<c:out value="${u.getName()}" />
						</option>
				
				</c:forEach>


			</select>
			<c:out value="${ticket.getReporter()} " />
		</div> 
		
		
		
		
		<div class="form-group">
			<label>status <span class="alert">*</span></label> <select
				class="form-control" name="status" required>
				
				
				<option value="open"
					<c:if test="${ticket.getStatus() == 'Blocker'}">
							<c:out value="selected" /> Blocker
				</c:if>>Open
				</option>
			
				<option value="In Progress"
					<c:if test="${ticket.getStatus() == 'In Progress'}">
							<c:out value="selected" /> In Progress
				</c:if>> In Progress
				</option>
				
				
				<option value="Resolved"
					<c:if test="${ticket.getStatus() == 'Resolved'}">
							<c:out value="selected" /> Resolved
				</c:if>>Resolved
				</option>
				<%-- 
				<option value="Open"
					<%if (ticket.getStatus().equals("Blocker"))
				out.println("selected");%>>Open</option>
				<option value="In Progress"
					<%if (ticket.getStatus().equals("In Progress"))
				out.println("selected");%>>In
					Progress</option>
				<option value="Resolved"
					<%if (ticket.getStatus().equals("Resolved"))
				out.println("selected");%>>Resolved</option> --%>
			</select>
		</div>

		<input type="submit" name="submit" value="edit ticket"
			class="form-btn"> <input type="hidden" name="id"
			value="<%=ticket.getTicketId()%>" />
		<div id="eresult" style="color: firebrick; display: block;"></div>
	</div>
</form>

