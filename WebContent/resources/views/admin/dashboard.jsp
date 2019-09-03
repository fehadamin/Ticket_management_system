<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    	ServletContext ctx = getServletContext();
    	String Url= ctx.getInitParameter("url");
    	String viewpath=ctx.getInitParameter("viewpath");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>admin panel</title>
    <link rel="stylesheet" href="<%=Url%>resources/css/dashboard.css" />
    <link rel="stylesheet" href="<%=Url%>resources/css/create_employee.css" />
 
    <link rel="stylesheet" href="<%=Url%>resources/css/tables.css" />
  	<link rel="stylesheet" href="<%=Url%>resources/css/create_department.css" />
    <link rel="stylesheet" href="<%=Url%>resources/css/create_ticket.css" />
	<link rel="stylesheet" href="<%=Url%>resources/css/common.css" />
	<link rel="stylesheet" href="<%=Url%>resources/css/jqueryui.css" />	
		
</head>
<body>
     
     
     <jsp:include page="include/header.jsp"/>
     <jsp:include page="include/sidebar.jsp"/>
     
	    <div class="data"><br>
	      
	      	<jsp:include page="pages/${pageName}.jsp"/>
	      
	    </div> 

    <jsp:include page="include/footer.jsp"/>
    
</body>
</html>

<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  
<script
  src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
  integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
  crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="<%=Url%>resources/js/toexcel.js"></script>  
  
<script src="<%=Url%>resources/js/main.js"></script>
<script src="<%=Url%>resources/js/validations.js"></script>


<script>
		
		$('#downloadReport').on("click",function(){
			
			$("#mytable").table2excel({
				exclude: ".noExl",
				name:"Worksheet Name",
				filename:"xyz",
				fileext: '.txt',
				preserveColors :true,
				
				
				
			});
			
		});


		
		$("#dueDate" ).datepicker({
			changeMonth:true,
			numberOfMonths:1,
			minDate:0,
			dateFormat: 'dd-mm-yy'
			});
		
		
		
		// to display message in the dashboard
		function cookies_get(cookie_name)
		{
			var name = cookie_name + "=";
			var dCookie = decodeURIComponent(document.cookie);
			var ca = dCookie.split(';');
			
			for(var i = 0;i<ca.length;i++){
				var c = ca[i];
				while(c.charAt(0) == ' '){
					c = c.substring(1);
					
				}
				if(c.indexOf(name) == 0){
					return c.substring(name.length,c.length);
				}
			}
			
			return "";
			
			
			
		}
		
		
		let message = cookies_get("message");
		console.log("message",message);
		if(message != "" && message != null)
			{
				$(".alert").css("display","block");
				message = message.replace(/_/g," ");
				console.log(message);
				$(".alert").html(message);
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// component product matching
		
		 $(function() {
		        $("#product").change(function(){
		            var element = $(this);
		            var id = element.val();
					
		            $("#comp option").each(function(i){
		            	var element = $(this);
			            var pid = element.attr("data-parent");
			            $(this).css("display","none");
			            if(pid == id){
			            	$(this).css("display","block");
			            }
		                
		            });
		            
		        });
	    });
		 $('#mytable').DataTable();
</script>






