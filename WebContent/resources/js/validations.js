function ticket_validation() {

	var flag = 0;
	var tickettype = document.getElementById("tickett").value;
	var departmentname = document.getElementById("departmentname").value;
	var product = document.getElementById("product").value;
	var component = document.getElementById("comp").value;
	var priority = document.getElementById("priority").value;
	var assignee = document.getElementById("assignee").value;
	var reporter = document.getElementById("reporter").value;
	var message;

	if (reporter == 0) {
		message = "select reporter type";
		flag = 1
	}

//	if (assignee == 0) {
//		message = "select assignee type";
//		//flag = 1
//	}

	if (priority == 0) {
		message = "select priority type";
		flag = 1
	}

	if (component == 0) {
		message = "select component type";
		flag = 1
	}
	if (product == 0) {
		message = "select product type";
		//flag = 1
	}

	if (departmentname == 0) {
		message = "select departmentname field";
		flag = 1;
	}

	if (tickettype == 0) {
		message = "select ticket type";
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
