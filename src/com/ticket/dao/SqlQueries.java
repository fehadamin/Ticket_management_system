package com.ticket.dao;

public interface SqlQueries {
	
	//department queries
	 final static String UPDATE_DEPARTMENT = "UPDATE departments SET department_name=? where department_id=?  ";
	 final static String INSERT_DEPARTMENT = "INSERT into departments (department_id,department_name)	values(null,?)";
	 final static String GET_ALL_DEPARTMENTS = "SELECT * from departments";
	 final static String REMOVE_DEPARTMENT = "DELETE  from departments where department_id=? ";
	 final static String GET_DEPARTMENT_BY_ID = "SELECT * from departments where department_id =? ";
	 final static String IF_EXISTS = "SELECT * FROM departments WHERE department_name=?";

	//product queries
	  	final static String INSERT_PRODUCT = "INSERT into products (product_id,product_name,default_assignee,parent)	values(null,?,?,?)";
		 final static String REMOVE_PRODUCT = "DELETE  FROM products where product_id=? ";
		 final static String UPDATE_PRODUCT = "UPDATE products SET product_name=?, default_assignee=?, parent=?  where product_id=?  ";
		 final static String GET_ALL_PRODUCTS = "SELECT * from products";
		 final static String GET_PRODUCT_BY_ID = "SELECT * from products where product_id =? ";

		 // ticket queries
		  final static String GET_ALL_TICKETS = "SELECT * from tickets";
			 final static String UPDATE_TICKET = "UPDATE tickets SET ticket_key=?	,ticket_type_id=?	,product_id=?	,summary=?	,assignee=?	,reporter=?,	priority=?	,status=?,	resolution=?, due_date = ?,	created=?	,updated=? ,component=?  where ticket_id=?";
			 final static String INSERT_TICKET = "INSERT into tickets (ticket_id,ticket_key,ticket_type_id,product_id,summary,assignee,reporter,priority,status,resolution,due_date,created,updated,component,products)	values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 final static String GET_BY_RESOLUTION = "SELECT * from tickets where 	resolution=?";
			 final static String GET_TICKET_BY_ID = "SELECT * from tickets where ticket_id =? ";
			 final static String GET_BY_STATUS = "SELECT * from tickets where status=?";
			 final static String GET_BY_PRIORITY = "SELECT * from tickets where priority=?";
			 final static String GET_BY_REPORTER = "SELECT * from tickets where reporter=?";
			 final static String GET_BY_ASSIGNEE = "SELECT * from tickets where assignee=?";
			 final static String GET_BY_DUEDATE	= "SELECT * FROM tickets where due_date = ?";
			 final static String UPDATE_REPORTER_BY_ID = "UPDATE tickets SET reporter=? where ticket_id=?";
			 final static String UPDATE_PRIORITY_BY_ID = "UPDATE tickets SET priority=? where ticket_id=?";
			 final static String UPDATE_SUMMARY_BY_ID = "UPDATE tickets SET summary=? where ticket_id=?";
			 final static String UPDATE_ASSIGNEE_BY_ID = "UPDATE tickets SET assignee=? where ticket_id=?";
			 final static String UPDATE_STATUS_BY_ID = "UPDATE tickets SET status=? where ticket_id=?";
			 final static String UPDATE_RESOLUTION_BY_ID = "UPDATE tickets SET resolution=? where ticket_id=?";
			 final static String DELETE_TICKET = "DELETE FROM tickets where ticket_id=?";
			 final static String UPDATE_KEY = "UPDATE tickets SET ticket_key = ? WHERE ticket_id = ?";
			
			//ticket types
			 final static String GET_ALL_TICKETTYPES = "SELECT * from ticket_types";
			 final static String UPDATE_TICKETTYPE = "UPDATE ticket_types SET ticket_name=? where ticket_type_id=?  ";
			 final static String INSERT_TICKETTYPE = "INSERT into ticket_types (ticket_type_id,ticket_name)	values(null,?)";
			 final static String REMOVE_TICKETTYPE = "DELETE FROM ticket_types where ticket_type_id=? ";
			 final static String GET_TICKETTYPE_BY_ID = "SELECT * from ticket_types where ticket_type_id =? ";

			//user model
			
			 final static String  AUTHENICATE_USER 	= "SELECT * from users where email=? and password = ?";
			 final static String GET_ALL_USERS = "SELECT * from users";
			 final static String SEARCH_BY_ID="SELECT * from users where user_id=?";
			 final static String SEARCH_BY_ROLES="SELECT * from users where role=?";
			 final static String ADD_USER = "INSERT into users (user_id,password,name,username,email,role,department_id,home_company,created)	values(null,?,?,?,?,?,?,?,?)";
			 final static String DELETE_USER = "DELETE  from users where user_id=?";
			 final static String UPDATE_USER = "UPDATE users set password=? ,name=? ,username=?,email=?,role=?,department_id=?,home_company=?,created=? where user_id=?"; 
			 final static String UPDATE_PASSWORD = "UPDATE users set password=? where user_id=?";
			
			
}
