<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
      <%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
    <!--sidebar-->
    <div class="sidebar">
        <div class="menu">
        <ul>
            <li> <a href="admin-dashboard.htm"><span class="fa fa-home"></span>DASHBOARD</a>   </li>
            <li> <a href="#"><span class="fa fa-home"></span>TICKET</a>  
                    <ul>
                            <li> <a href="admin-ticket-all.htm"><span class="fa fa-home">SHOW TICKET</span></a></li>  
                             <li> <a href="admin-ticket-form.htm"><span class="fa fa-home">CREATE TICKET</span></a>  </li>
                    </ul>
            
            </li>
             <li> <a href=""><span class="fa fa-home"></span>DEPARTMENT</a>  
                    <ul>
                            <li> <a href="admin-department-all.htm"><span class="fa fa-home">SHOW DEPARTMENT</span></a></li>  
                             <li> <a href="admin-department-form.htm"><span class="fa fa-home">CREATE DEPARTMENT</span></a>  </li>
                    </ul>
            
            </li>
            
             <li> <a href=""><span class="fa fa-home"></span> TICKET TYPE</a>  
                    <ul>
                            <li> <a href="admin-tickettype-all.htm"><span class="fa fa-home">SHOW TICKET TYPE</span></a></li>  
                             <li> <a href="admin-tickettype-form.htm"><span class="fa fa-home">CREATE TICKET TYPE</span></a>  </li>
                    </ul>
            
            </li>
             <li> <a href=""><span class="fa fa-home"></span> PRODUCT</a>  
                    <ul>
                            <li> <a href="admin-product-all.htm"><span class="fa fa-home">SHOW PRODUCT</span></a></li>  
                             <li> <a href="admin-product-form.htm"><span class="fa fa-home">CREATE PRODUCT</span></a>  </li>
                    </ul>
            
            </li>
            
             <li> <a href="#"><span class="fa fa-home"></span> EMPLOYEE</a>  
                    <ul>
                            <li> <a href="admin-employee-all.htm"><span class="fa fa-home">SHOW EMPLOYEE</span></a></li>  
                             <li> <a href="admin-employee-form.htm"><span class="fa fa-home">CREATE EMPLOYEE</span></a>  </li>
                    </ul>
            
            </li>
            <li> <a href="<%=Url%>upload_file.htm"><span class="fa fa-home"></span>UPLOAD</a>   </li>
			 <li> <a href="<%=Url%>upload_file_store.htm"><span class="fa fa-home"></span>LOG OUT</a>   </li>

        </ul>




    </div>
    
    </div>