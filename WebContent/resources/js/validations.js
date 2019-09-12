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
	changeYear:true,
	numberOfMonths:1,
	//minDate:0,
	dateFormat: 'dd-mm-yy'
});
$("#createdDate" ).datepicker({
	changeMonth:true,
	changeYear:true,
	numberOfMonths:1,
	//minDate:0,
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
//
////component product matching
// $(function() {
//        $("#comp").change(function(){
//            var element = $(this);
//            var cid = element.val();
//            var pid = $("#product").val();
//            $("#assignee option").each(function(i){
//            	var element = $(this);
//	            var pids = element.attr("data-assigned");
//	            $(this).css("display","none");
//	            console.log(pids);
//	            pids = pids.split(',');
//	            console.log(pids);
////	            if(pid == id){
////	            	$(this).css("display","block");
////	            }
//            });
//            
//        });
//});
 
$('#mytable').DataTable();

// ticket form on submit handler
$("#ticketForm").on("submit",function(e){
	e.preventDefault();
	if($(this).attr('data-form-type') == 'create'){
		ticket_validation(e);
	}
	else{
		ticket_edit_validation(e);
	}	
	
});


function ticket_validation(e) {
	
	var flag = 0;
	var tickettype = document.getElementById("tickett").value;
	var departmentname = document.getElementById("departmentname").value;
	var product = document.getElementById("product").value;
	var component = document.getElementById("comp").value;
	var priority = document.getElementById("priority").value;
	var assignee = document.getElementById("assignee").value;
	var reporter = document.getElementById("reporter").value;
	var dueDate = document.getElementById("dueDate").value;
	//var createdDate = document.getElementById("createdDate").value;
	var message;

	var today = new Date();

	if(Date.parse(dueDate) < today){
		message = "due date is incorrect";
		document.getElementById("dueDate-tip").style.visibility = 'visible';
		flag = 1;
	}
	else{
		document.getElementById("dueDate-tip").style.visibility = 'hidden';
	}
	
	if (reporter == 0) {
		message = "select reporter type";
		document.getElementById("reporter").class = "form-control error";
		document.getElementById("reporter-tip").style.visibility = 'visible';
		
		flag = 1;
	}else{
		document.getElementById("reporter-tip").style.visibility = 'hidden';
	}

//	if (assignee == 0) {
//		message = "select assignee type";
//		//flag = 1
//	}

	if (priority == 0) {
		message = "select priority type";
		document.getElementById("priority").class = "form-control error";
		document.getElementById("priority-tip").style.visibility = 'visible';
		flag = 2
	}else{
		document.getElementById("priority-tip").style.visibility = 'hidden';
	}

	if (component == 0) {
		message = "select component type";
		document.getElementById("comp").class = "form-control error";
		document.getElementById("comp-tip").style.visibility = 'visible';
		flag = 3;
	}else{
		document.getElementById("comp-tip").style.visibility = 'hidden';
	}
	
	if (product == 0) {
		message = "select product type";
		document.getElementById("product").class = "form-control error";
		document.getElementById("product-tip").style.visibility = 'visible';
		//flag = 1
	}else{
		document.getElementById("product-tip").style.visibility = 'hidden';
	}

	if (departmentname == 0) {
		message = "select departmentname field";
		document.getElementById("departmentname").class = "form-control error";
		document.getElementById("departmentname-tip").style.visibility = 'visible';
		flag = 5;
	}else{
		document.getElementById("departmentname-tip").style.visibility = 'hidden';
	}
	

	if (tickettype == 0) {
		message = "select ticket type";
		document.getElementById("tickett").class = "form-control error";
		document.getElementById("tickett-tip").style.visibility = 'visible';
		flag = 6;
	}else{
		document.getElementById("tickett-tip").style.visibility = 'hidden';
	}
	
	
	
	if (flag > 0) {
		alert(message);
		document.getElementById('eresult').innerHTML = message;
		document.getElementById('eresult2').innerHTML = message;
		return false;
	}else {
		/////
		$(".form-btn").attr("disabled",true);
		$.post($('#ticketForm').attr("action"),$('#ticketForm').serialize(),function(result){
				
			var url = document.getElementById("url").value;
			if(result){
				console.log(result);
				window.location = url;
			}
			
		});
	}
	
}

function ticket_edit_validation(e){
	
	var flag = 0;
	var tickettype = document.getElementById("tickett").value;
	var product = document.getElementById("product").value;
	var component = document.getElementById("comp").value;
	var priority = document.getElementById("priority").value;
	var assignee = document.getElementById("assignee").value;
	var reporter = document.getElementById("reporter").value;
	var dueDate = document.getElementById("dueDate").value;
	var createdDate = document.getElementById("createdDate").value;
	var message;

	var today = new Date();

	if(Date.parse(dueDate) < today){
		message = "due date is incorrect";
		document.getElementById("dueDate-tip").style.visibility = 'visible';
		flag = 1;
	}
	else{
		document.getElementById("dueDate-tip").style.visibility = 'hidden';
	}
	
	if (reporter == 0) {
		message = "select reporter type";
		document.getElementById("reporter").class = "form-control error";
		document.getElementById("reporter-tip").style.visibility = 'visible';
		
		flag = 1;
	}else{
		document.getElementById("reporter-tip").style.visibility = 'hidden';
	}

	if (priority == 0) {
		message = "select priority type";
		document.getElementById("priority").class = "form-control error";
		document.getElementById("priority-tip").style.visibility = 'visible';
		flag = 2
	}else{
		document.getElementById("priority-tip").style.visibility = 'hidden';
	}

	if (component == 0) {
		message = "select component type";
		document.getElementById("comp").class = "form-control error";
		document.getElementById("comp-tip").style.visibility = 'visible';
		flag = 3;
	}else{
		document.getElementById("comp-tip").style.visibility = 'hidden';
	}
	
	if (product == 0) {
		message = "select product type";
		document.getElementById("product").class = "form-control error";
		document.getElementById("product-tip").style.visibility = 'visible';
		//flag = 1
	}else{
		document.getElementById("product-tip").style.visibility = 'hidden';
	}

	if (tickettype == 0) {
		message = "select ticket type";
		document.getElementById("tickett").class = "form-control error";
		document.getElementById("tickett-tip").style.visibility = 'visible';
		flag = 6;
	}else{
		document.getElementById("tickett-tip").style.visibility = 'hidden';
	}
	
	if (flag > 0) {
		alert(message);
		document.getElementById('eresult').innerHTML = message;
		document.getElementById('eresult2').innerHTML = message;
		return false;
	}else {
		$(".form-btn").attr("disabled",true);
		$.post($('#ticketForm').attr("action"),$('#ticketForm').serialize(),function(result){
				
			var url = document.getElementById("url").value;
			if(result){
				console.log(result);
				window.location = url;
			}
			
		});
	}
	
	
	
}


function department_validation(e) {
	var departmentname = document.getElementById('departmentname').value;
	/*
	 * let url = document.getElementById("url").value; let url = url +
	 * "if-department-exist.htm";
	 */
	if (departmentname.length < 3) {
		document.getElementById('eresult').innerHTML = 'Department length should be greater than 3';

		return false;
	}

	/*
	 * function loadDoc(url) { var xhttp = new XMLHttpRequest();
	 * xhttp.onreadystatechange = function() { if (this.readyState == 4 &&
	 * this.status == 200) { console.log(this.responseText); } };
	 * xhttp.open("GET", url, true); xhttp.send(); }
	 */
}

function compns() {

	var x = document.getElementById('tickett');
	var tc = x.options[x.selectedIndex].innerHTML;
	if (tc == 'PDCR') {
		document.getElementById('comp').style.visibility = 'hidden';
		document.getElementById('product').style.visibility = 'hidden';
		document.getElementById('product').disabled = true;
		document.getElementById('pds').style.display = 'block';
		document.getElementById('assignee').required = true; 
		document.getElementById('assignee').firstElementChild.remove();
		
	} else {
		document.getElementById('comp').style.visibility = 'visible';
		document.getElementById('product').style.visibility = 'visible';
		document.getElementById('product').disabled = false;
		document.getElementById('pds').style.display = 'none';
		document.getElementById('assignee').required = false;
		
		var select = document.getElementById('assignee');
		var opt = new Option('Select..', '0');
		select.insertBefore(opt, select.firstChild);
	}
	return false;
}

/**
 * function to validate product form
 * 
 * @returns
 */
function product_validation() {
	var productname = document.getElementById('productname').value;
	var defaultassignee = document.getElementById('defaultassignee').value;

	if (productname.length < 4) {
		document.getElementById('eresult').textContent = 'Product length should be greater than 3';

		return false;
	}
	if (defaultassignee.length < 3) {
		document.getElementById('eresult').innerHTML = 'Product length should be greater than 3';

		return false;
	}
}

/**
 * function to validate employee form
 * 
 * @returns
 */
function employee_validation() {
	var name = document.getElementById('name').value;
	var username = document.getElementById('username').value;
	var email = document.getElementById('email').value;
	var password = document.getElementById('password').value;
	var homecompany = document.getElementById('homecompany').value;
	var flag=0;
	var message;

	
	if (homecompany.length < 4) {
		message = "homecompany should be greater than 4";
		flag = 1
	}
	if (password.length < 4) {
		message = "password should be greater than 4";
		flag = 1
	}
	
	if (email.length < 4) {
		message = "email should be greater than 4";
		flag = 1
	}
	if (username.length < 4) {
		message = "username should be greater than 4";
		flag = 1
	}

	if (name.length < 4) {
		message = "name should be greater than 4";
		flag = 1
	}

	
	if (flag == 1) {

		document.getElementById('eresult').innerHTML = message;
		document.getElementById('eresult2').innerHTML = message;
		return false;
	} else {
		return true;
	}
}




/**
 *  function to validate ticket type
 */
function tickettypevalidation() {

	var tickettypename = document.getElementById('tickettypename').value;

	if (tickettypename.length < 4) {
		document.getElementById('eresult').innerHTML = 'tickettypename length should be greater than 4';
		return false;
	} else {
		return true;
	}

}






