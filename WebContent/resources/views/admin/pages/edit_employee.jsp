<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import= "java.util.*,com.ticket.entity.*"%>
<%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    	
    	User user = (User)request.getAttribute("user");
    %>

    

    <form action="admin-employee-edit-store.htm" method="POST" onclick=" return employee_validation();">
        <div class="box">
            
            <h1> edit employee</h1>
            <input type="hidden" name="id" value="<%=user.getUserId()%>"/>
            <div class="form-group">
            <label> name <span class="alert">*</span></label>
            <input type="text" class="form-control" name="name" id="name" value="<%=user.getName()%>"placeholder="Enter name">
            </div>
            
            <div class="form-group">
            <label> username <span class="alert">*</span></label>
            <input type="text" name="username" class="form-control"   value="<%=user.getUserName()%>"id="username" placeholder="enter username" />
            </div>
            
             <div class="form-group">
            <label> email <span class="alert">*</span></label>
            <input type="email" name="email" class="form-control" id="email"  value="<%=user.getEmail()%>" placeholder="enter email" />
            </div>
            
            <div class="form-group">
            <label> password <span class="alert">*</span></label>
            <input type="password" required name="password" class="form-control" id="password" placeholder="enter password" />
            </div>
               <div class="form-group">
            <label> home company <span class="alert">*</span></label>
            <input type="text"  required name="homecompany"  class="form-control" id="homecompany"  value="<%=user.getHomeCompany()%>" placeholder="enter homecompany" />
			</div>
			
				 <div class="form-group">
             <label>Role  <span class="alert">*</span></label>
            <select  class="form-control" name="role" required >
                    <option value="admin" 
                    	<%if(user.getRole().equals("admin"))out.println("selected"); %>
                    >Admin</option>
                    <option value="employee"
                                        	<%if(user.getRole().equals("employee"))out.println("selected"); %>
                    >Employee</option>
            </select> 
            </div>
            <br>
            <input type="submit" name="submit" class="form-btn"  value="create User"> 
          <div id="eresult" style="color:firebrick;display:block;"></div>
        </div>
    </form>
  
    

