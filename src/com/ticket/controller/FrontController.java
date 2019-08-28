package com.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ticket.entity.Department;
import com.ticket.entity.Product;
import com.ticket.entity.Ticket;
import com.ticket.entity.TicketType;
import com.ticket.entity.User;
import com.ticket.exceptions.DepartmentException;
import com.ticket.exceptions.ProductException;
import com.ticket.exceptions.UserException;
import com.ticket.models.DepartmentModel;
import com.ticket.models.ProductModel;
import com.ticket.models.TicketModel;
import com.ticket.models.TicketTypesModel;
import com.ticket.models.UserModel;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.htm")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String HOME = "home.htm";
	private final static String LOGIN = "login.htm";
	private final static String AUTH = "auth.htm";

	private final static String ADMIN_DASHBOARD = "admin-dashboard.htm";
	// department
	private final static String ADMIN_DEPARTMENT_FORM = "admin-department-form.htm";
	private final static String ADMIN_DEPARTMENT_STORE = "admin-department-store.htm";
	private final static String ADMIN_DEPARTMENT_ALL = "admin-department-all.htm";
	private final static String ADMIN_DEPARTMENT_EDIT_FORM = "admin-department-edit-form.htm";
	private final static String ADMIN_DEPARTMENT_EDIT_STORE = "admin-department-edit-store.htm";
	private final static String ADMIN_DEPARTMENT_REMOVE = "admin-department-remove.htm";

	// products
	private final static String ADMIN_PRODUCT_FORM = "admin-product-form.htm";
	private final static String ADMIN_PRODUCT_STORE = "admin-product-store.htm";
	private final static String ADMIN_PRODUCT_ALL = "admin-product-all.htm";
	private final static String ADMIN_PRODUCT_EDIT_FORM = "admin-product-edit-form.htm";
	private final static String ADMIN_PRODUCT_EDIT_STORE = "admin-product-edit-store.htm";
	private final static String ADMIN_PRODUCT_REMOVE = "admin-product-remove.htm";
	// employees

	private final static String ADMIN_EMPLOYEE_FORM = "admin-employee-form.htm";
	private final static String ADMIN_EMPLOYEE_STORE = "admin-employee-store.htm";
	private final static String ADMIN_EMPLOYEE_ALL = "admin-employee-all.htm";
	private final static String ADMIN_EMPLOYEE_EDIT_FORM = "admin-employee-edit-form.htm";
	private final static String ADMIN_EMPLOYEE_EDIT_STORE = "admin-employee-edit-store.htm";
	private final static String ADMIN_EMPLOYEE_REMOVE = "admin-employee-remove.htm";

	// ticketType

	private final static String ADMIN_TICKETTYPE_FORM = "admin-tickettype-form.htm";
	private final static String ADMIN_TICKETTYPE_STORE = "admin-tickettype-store.htm";
	private final static String ADMIN_TICKETTYPE_ALL = "admin-tickettype-all.htm";
	private final static String ADMIN_TICKETTYPE_EDIT_FORM = "admin-tickettype-edit-form.htm";// error
	private final static String ADMIN_TICKETTYPE_EDIT_STORE = "admin-tickettype-edit-store.htm";
	private final static String ADMIN_TICKETTYPE_REMOVE = "admin-tickettype-remove.htm";

	// ticket
	private final static String ADMIN_TICKET_FORM = "admin-ticket-form.htm";
	private final static String ADMIN_TICKET_STORE = "admin-ticket-store.htm";
	private final static String ADMIN_TICKET_ALL = "admin-ticket-all.htm";
	private final static String ADMIN_TICKET_EDIT_FORM = "admin-ticket-edit-form.htm";// error
	private final static String ADMIN_TICKET_EDIT_STORE = "admin-ticket-edit-store.htm";
	private final static String ADMIN_TICKET_REMOVE = "admin-ticket-remove.htm";
	private final static String ADMIN_TICKET_UPDATE_PRIORITY = ".htm";
	private final static String ADMIN_TICKET_UPDATE_STATUS = ".htm";
	private final static String ADMIN_TICKET_UPDATE_REPORTER = ".htm";
	private final static String ADMIN_TICKET_UPDATE_ASSIGNEE = ".htm";
	private final static String ADMIN_TICKET_UPDATE_RESOLUTION = ".htm";

	// employees

	private final static String EMPLOYEE_DASHBOARD = "employee-dashboard.htm";

	private final static String EMPLOYEE_TICKET_FORM = "employee-ticket-form.htm";
	private final static String EMPLOYEE_TICKET_STORE = "employee-ticket-store.htm";
	private final static String EMPLOYEE_TICKET_ALL = "employee-ticket-all.htm";
	private final static String EMPLOYEE_TICKET_EDIT_FORM = "employee-ticket-edit-form.htm";// error
	private final static String EMPLOYEE_TICKET_EDIT_STORE = "employee-ticket-edit-store.htm";
	private final static String EMPLOYEE_TICKET_REMOVE = "employee-ticket-remove.htm";
	private final static String EMPLOYEE_TICKET_UPDATE_PRIORITY = ".htm";
	private final static String EMPLOYEE_TICKET_UPDATE_STATUS = ".htm";
	private final static String EMPLOYEE_TICKET_UPDATE_REPORTER = ".htm";
	private final static String EMPLOYEE_TICKET_UPDATE_ASSIGNEE = ".htm";
	private final static String EMPLOYEE_TICKET_UPDATE_RESOLUTION = ".htm";

	// filters

	private final static String FILTER_TICKET_TABLE = "filter_ticket_side.htm";
	
	// UPLOAD_FILE
	private final static String UPLOAD_FILE = "upload_file.htm";

	// logout logout.htm
	private final static String LOGOUT = "logout.htm";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		// TODO Auto-generated method stub

		String requestUrl = request.getRequestURI();
		ServletContext ctx = request.getServletContext();
		String path = ctx.getInitParameter("viewpath");
		String url = ctx.getInitParameter("url");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // session

		System.out.println(requestUrl);

		if (requestUrl.endsWith("/")) {
			response.sendRedirect(HOME);
		} else if (requestUrl.endsWith(HOME)) {
			RequestDispatcher rd = request.getRequestDispatcher(path + "index.jsp");
			rd.forward(request, response);
		}
		/**
		 * login page
		 */
		else if (requestUrl.endsWith(LOGIN)) {
			RequestDispatcher rd = request.getRequestDispatcher(path + "login.jsp");
			rd.forward(request, response);
		}
		/**
		 * authenicate the user/admin
		 */
		else if (requestUrl.endsWith(AUTH)) {

			UserModel userModel = new UserModel();
			User u = new User();
			u = null;
			try {
				u = userModel.authenicateUser(request.getParameter("email"), request.getParameter("password"));
			} catch (UserException e) {
				request.setAttribute("message", "incorrect email / password");
			}
			if (u != null) {
				session.setAttribute("userId", u.getUserId());
				session.setAttribute("userName", u.getName());
				session.setAttribute("email", u.getEmail());
				session.setAttribute("role", u.getRole());

				Cookie cookie = new Cookie("sessionId", u.getEmail());

				cookie.setMaxAge(5 * 60 * 60);
				response.addCookie(cookie);

				if (u.getRole().equals("admin")) {
					response.sendRedirect(ADMIN_DASHBOARD);
				} else {

					response.sendRedirect(EMPLOYEE_DASHBOARD);
				}

			} else {
				RequestDispatcher rd = request.getRequestDispatcher(path + "login.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * code to go to admin dashboard
		 */
		else if (requestUrl.endsWith(ADMIN_DASHBOARD)) {

			request.setAttribute("pageName", "home");
			RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
			rd.forward(request, response);

		}
		// department form
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_FORM)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {

				request.setAttribute("pageName", "create_department");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * //storing the values taken from departmentform and storing it into database
		 * 
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				DepartmentModel dModel = new DepartmentModel();
				Department d = new Department();
				Cookie cookie = new Cookie("message", "department_added");
				cookie.setMaxAge(9);

				d.setDepartmentName(request.getParameter("departmentname"));
				int flag = 0;
				try {
					dModel.insert(d);
				} catch (Exception e) {
					request.setAttribute("message", "department already exist");
					flag = 1;
				}
				if (flag == 0) {
					response.addCookie(cookie);
					response.sendRedirect(ADMIN_DASHBOARD);
				} else {
					request.setAttribute("pageName", "home");

					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
					rd.forward(request, response);
				}

			}
		}

		/**
		 * // viewing all the departments
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_ALL)) {
			// checking
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				DepartmentModel dModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_departments_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}
		}

		/**
		 * // for editing the department
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				DepartmentModel dModel = new DepartmentModel();
				Department d = dModel.getById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("department", d);
				request.setAttribute("pageName", "edit_department");

				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing the values in database
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				DepartmentModel dModel = new DepartmentModel();
				Department d = new Department();
				d.setDepartmentId(Integer.parseInt(request.getParameter("id")));
				d.setDepartmentName(request.getParameter("departmentname"));
				Cookie cookie = new Cookie("message", "department_updated");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) dModel.updateById(d.getDepartmentId(), d);
				} catch (DepartmentException e) {
					cookie.setValue("Some_error_occured");
					// e.printStackTrace();
				}

				response.addCookie(cookie);

				response.sendRedirect(ADMIN_DASHBOARD);

			}
		}
		/**
		 * removing the department
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_REMOVE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				Cookie cookie = new Cookie("message", "department_deleted");
				cookie.setMaxAge(9);

				DepartmentModel dModel = new DepartmentModel();
				int flag = 0;
				try {
					flag = (int) dModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "Department removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}

			}
		}
		/**
		 * product form
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {

				UserModel u = new UserModel();
				request.setAttribute("users", u.getAll());
				ProductModel pm = new ProductModel();
				request.setAttribute("parents", pm.getAll());
				request.setAttribute("pageName", "create_product");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing the values in database
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				ProductModel pModel = new ProductModel();
				Product p = new Product();
				p.setProductName(request.getParameter("productname"));
				p.setDefaultAssignee(request.getParameter("defaultassignee"));
				p.setParent(Integer.parseInt(request.getParameter("parent")));

				Cookie cookie = new Cookie("message", "product_added");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					pModel.insert(p);
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Product already exist");
					flag = 1;
				} catch (Exception e) {

					e.printStackTrace();
				}
				if (flag == 0) {
					response.addCookie(cookie);
					response.sendRedirect(ADMIN_DASHBOARD);
				} else {

					request.setAttribute("pageName", "home");
					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
					rd.forward(request, response);

				}

			}
		}
		/**
		 * view all the products
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_ALL)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				ProductModel pModel = new ProductModel();
				try {
					request.setAttribute("products", pModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_products_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}
		}
		/**
		 * editing the product
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				UserModel u = new UserModel();
				request.setAttribute("users", u.getAll());
				ProductModel pModel = new ProductModel();
				Product p = pModel.getById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("product", p);

				request.setAttribute("parents", pModel.getAll());
				request.setAttribute("pageName", "edit_product");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * // storing the values in db after editing
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				ProductModel pModel = new ProductModel();
				Product p = new Product();
				p.setProductId(Integer.parseInt(request.getParameter("id")));// check the value
				p.setProductName(request.getParameter("productname")); // do

				p.setDefaultAssignee(request.getParameter("defaultassignee"));
				p.setParent(Integer.parseInt(request.getParameter("parent")));
				Cookie cookie = new Cookie("message", "product_updated");
				cookie.setMaxAge(9);

				System.out.println(p.toString());
				int flag = 0;
				try {
					flag = (int) pModel.updateById(p.getProductId(), p);
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				request.setAttribute("message", "product updated successfully ");
				response.sendRedirect(ADMIN_DASHBOARD);

			}

		}

		/**
		 * //deleting the product
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_REMOVE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				ProductModel pModel = new ProductModel();
				Cookie cookie = new Cookie("message", "Product_removed");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) pModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "product removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}

			}

		}

		/**
		 * // tickettype form
		 */

		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_FORM)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {

				request.setAttribute("pageName", "create_tickettype");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * // storing tickettype in database
		 */
		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketTypesModel ttModel = new TicketTypesModel();
				TicketType tt = new TicketType();
				Cookie cookie = new Cookie("message", "ticketType_added");
				cookie.setMaxAge(9);

				tt.setTicketName(request.getParameter("tickettypename"));
				int flag = 0;
				try {
					ttModel.insert(tt);
				} catch (Exception e) {
					request.setAttribute("message", "tickettype already exists");
					flag = 1;
				}
				if (flag == 0) {
					response.addCookie(cookie);
					response.sendRedirect(ADMIN_DASHBOARD);

				} else {
					request.setAttribute("pageName", "create_tickettype");
					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
					rd.forward(request, response);

				}
			}
		}
		/**
		 * // viewing all the tickettypes present
		 */

		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_ALL)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketTypesModel ttModel = new TicketTypesModel();
				try {
					request.setAttribute("tickettypes", ttModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_ticket_type_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}
		}

		/**
		 * // editing the ticket type
		 */
		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				TicketTypesModel ttModel = new TicketTypesModel();
				TicketType t = ttModel.getById(Integer.parseInt(request.getParameter("id")));
				System.out.println(t.toString());
				request.setAttribute("tickettype", t);
				request.setAttribute("pageName", "edit_ticket_type");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * // storing the ticket type in database
		 */
		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				TicketTypesModel ttModel = new TicketTypesModel();
				TicketType t = new TicketType();
				t.setTicketTypeId(Integer.parseInt(request.getParameter("id")));
				t.setTicketName(request.getParameter("tickettypename"));
				Cookie cookie = new Cookie("message", "ticketType_updated");
				cookie.setMaxAge(9);

				
				int flag = 0;
				try {
					flag = (int) ttModel.updateById(t.getTicketTypeId(), t);
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				/*
				 * if (flag == 1) {
				 */

				request.setAttribute("message", "ticket type updated successfully ");
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

				// }

			}

		}
		/**
		 * deleting the ticket type
		 */
		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_REMOVE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				TicketTypesModel ttModel = new TicketTypesModel();
				Cookie cookie = new Cookie("message", "ticketType_removed");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) ttModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some errors  occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "ticket type removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
			}

		}

		/**
		 * insert ticket
		 */
		else if (requestUrl.endsWith(ADMIN_TICKET_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				ProductModel pdModel = new ProductModel();
				request.setAttribute("products", pdModel.getAll());
				TicketTypesModel ttModel = new TicketTypesModel();
				request.setAttribute("tickettypes", ttModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				request.setAttribute("user", session.getAttribute("userName"));
				request.setAttribute("userId", session.getAttribute("userId"));

				request.setAttribute("pageName", "create_ticket");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}
		}
		/**
		 * storing the data into database
		 */
		else if (requestUrl.endsWith(ADMIN_TICKET_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				ProductModel pm = new ProductModel();
				Cookie cookie = new Cookie("message", "ticket_added");
				cookie.setMaxAge(9);

				t.setTicketKey(request.getParameter("project"));
				t.setAssignee(request.getParameter("assignee"));
				t.setStatus("open");
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setDueDate(request.getParameter("dueDate"));
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setProductId(Integer.parseInt(request.getParameter("product")));
				t.setReporter(request.getParameter("reporter"));
				t.setResolution("unresolved");
				// t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setComponent(Integer.parseInt(request.getParameter("component")));
				try {
					int id = tModel.insert(t);
					Product p = pm.getById(Integer.parseInt(request.getParameter("product")));
					tModel.updateKey(id, p.getProductName() + "-" + id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

			}

		}
		/**
		 * viewing all the tickets created
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_ALL)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				try {
					request.setAttribute("tickets", tModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_tickets_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * editing the ticket
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				ProductModel pdModel = new ProductModel();
				request.setAttribute("products", pdModel.getAll());
				TicketTypesModel ttModel = new TicketTypesModel();
				request.setAttribute("tickettypes", ttModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				request.setAttribute("user", session.getAttribute("userName")); // reporter
				request.setAttribute("userId", session.getAttribute("userId"));// reporter

				TicketModel tModel = new TicketModel();
				request.setAttribute("ticket", tModel.getById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageName", "edit-ticket");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing in the database after editing
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				Cookie cookie = new Cookie("message", "ticket_edited");
				cookie.setMaxAge(9);
				Ticket t1 = new Ticket();
				
				t.setTicketId(Integer.parseInt(request.getParameter("id")));
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setTicketKey(request.getParameter("project"));
				t.setAssignee(request.getParameter("assignee"));
				t.setStatus(request.getParameter("status"));
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setProductId(Integer.parseInt(request.getParameter("product")));
				t.setReporter(request.getParameter("reporter"));
				t.setResolution("unresolved");
				t.setDueDate(request.getParameter("dueDate"));
				t.setComponent(Integer.parseInt(request.getParameter("component")));
				
				System.out.println(t.getTicketId());
				System.out.println(t.toString());
				t1 = tModel.getById(t.getTicketId());
				try {
					tModel.updateById(t.getTicketId(), t);
					tModel.updateKey(t1.getTicketId(), t1.getTicketKey());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);
				
				

							}

		}
		/**
		 * removing the ticket
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_REMOVE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				TicketModel tModel = new TicketModel();
				Cookie cookie = new Cookie("message", "ticket_removed");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) tModel.deleteTicket(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some errors  occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "ticket  removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
			}

		}

		/**
		 * employee create ticket form
		 */

		else if (requestUrl.endsWith(EMPLOYEE_TICKET_FORM)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				ProductModel pdModel = new ProductModel();
				request.setAttribute("products", pdModel.getAll());
				TicketTypesModel ttModel = new TicketTypesModel();
				request.setAttribute("tickettypes", ttModel.getAll());

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				request.setAttribute("user", session.getAttribute("userName")); // reporter
				request.setAttribute("userId", session.getAttribute("userId"));// reporter

				request.setAttribute("pageName", "create_ticket");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing the ticket in database
		 */

		else if (requestUrl.endsWith(EMPLOYEE_TICKET_STORE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				ProductModel pm = new ProductModel();
				Cookie cookie = new Cookie("message", "Ticked_added");
				cookie.setMaxAge(9);
				t.setTicketKey(request.getParameter("project"));
				t.setAssignee(request.getParameter("assignee"));
				t.setStatus("open");
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setProductId(Integer.parseInt(request.getParameter("product")));
				t.setReporter(request.getParameter("reporter"));
				t.setDueDate(request.getParameter("dueDate"));
				t.setResolution("unresolved");
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setComponent(Integer.parseInt(request.getParameter("component")));
				try {
					int id = tModel.insert(t);
					Product p = pm.getById(Integer.parseInt(request.getParameter("product")));
					tModel.updateKey(id, p.getProductName() + "-" + id); // for unqiue key
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(EMPLOYEE_DASHBOARD);

			}
		}

		/**
		 * viewing all the tickets
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_ALL)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				try {
					request.setAttribute("tickets", tModel.getByAssignee((String) session.getAttribute("userName")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_tickets_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
				rd.forward(request, response);

			}

		}

		/**
		 * edit ticket
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				ProductModel pdModel = new ProductModel();
				request.setAttribute("products", pdModel.getAll());
				TicketTypesModel ttModel = new TicketTypesModel();
				request.setAttribute("tickettypes", ttModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				TicketModel tModel = new TicketModel();
				request.setAttribute("ticket", tModel.getById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageName", "edit-ticket");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing in db after editing
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				Ticket t1 = new Ticket();
				Cookie cookie = new Cookie("message", "ticket_edited");
				cookie.setMaxAge(9);

				t.setTicketId(Integer.parseInt(request.getParameter("id")));
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setTicketKey(request.getParameter("project"));
				t.setAssignee(request.getParameter("assignee"));
				t.setStatus(request.getParameter("status"));
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setProductId(Integer.parseInt(request.getParameter("product")));
				t.setReporter(request.getParameter("reporter"));
				t.setDueDate(request.getParameter("dueDate"));
				t.setResolution("unresolved");
				t.setDueDate(request.getParameter("dueDate"));
				t.setComponent(Integer.parseInt(request.getParameter("component")));

				t1 = tModel.getById(t.getTicketId());
				try {
					tModel.updateById(t.getTicketId(), t);
					tModel.updateKey(t1.getTicketId(), t1.getTicketKey());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(EMPLOYEE_DASHBOARD);
			}

		}
		/**
		 * deleting the ticket
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_REMOVE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				TicketModel tModel = new TicketModel();
				Cookie cookie = new Cookie("message", "ticket_deleted");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) tModel.deleteTicket(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some errors  occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "ticket  removed successfully ");
					response.sendRedirect(EMPLOYEE_DASHBOARD);
				}
			}

		}
		/**
		 * for filters
		 */
		else if (requestUrl.endsWith(FILTER_TICKET_TABLE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				TicketModel tModel = new TicketModel();
				String role = (String) session.getAttribute("role");
				String status = request.getParameter("status");
				String priority = request.getParameter("priority");
				String assignee = request.getParameter("assignee");

				request.setAttribute("tickets", tModel.byFilter(status, priority, assignee));
				request.setAttribute("pageName", "all_tickets_table");

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				if (role.equals("admin")) {
					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
					rd.forward(request, response);
				} else {

					RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
					rd.forward(request, response);
				}

			}

			/**
			 * for employee table
			 */

		} else if (requestUrl.endsWith(ADMIN_EMPLOYEE_FORM)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				DepartmentModel dModel = new DepartmentModel();
				request.setAttribute("departments", dModel.getAll());

				request.setAttribute("pageName", "create_employee");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing in db
		 */

		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				UserModel uModel = new UserModel();
				User u = new User();
				Cookie cookie = new Cookie("message", "employee_added");
				cookie.setMaxAge(9);

				u.setName(request.getParameter("name"));
				u.setUserName(request.getParameter("username"));
				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));
				u.setHomeCompany(request.getParameter("homecompany"));
				u.setRole(request.getParameter("role"));
				int flag = 0;
				
				System.out.println(request.getParameter("role"));
				try {

					uModel.addUser(u);
				} catch (Exception e) {
					request.setAttribute("message", "duplicate values");
					flag = 1;
				}
				if (flag == 0) {
					response.addCookie(cookie);
					response.sendRedirect(ADMIN_DASHBOARD);
				} else {
					request.setAttribute("pageName", "home");
					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
					rd.forward(request, response);
				}
			}

		}
		/**
		 * viewing all the tables
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_ALL)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
			} else {
				// get the session data from database

				UserModel uModel = new UserModel();
				try {
					request.setAttribute("users", uModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "all_employees_table");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}
		}
		/**
		 *  edit form
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_EDIT_FORM)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired  ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("user", userModel.searchByUserId(Integer.parseInt(request.getParameter("id"))));

				request.setAttribute("pageName", "edit_employee");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/*
		 *	storing in database
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_EDIT_STORE)) {

			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
			} else {

				UserModel uModel = new UserModel();
				User u = new User();
				Cookie cookie = new Cookie("message", "employee_updated");
				cookie.setMaxAge(9);
				u.setUserId(Integer.parseInt(request.getParameter("id")));
				u.setName(request.getParameter("name"));
				u.setUserName(request.getParameter("username"));
				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));
				u.setHomeCompany(request.getParameter("homecompany"));
				u.setRole(request.getParameter("role"));

				uModel.updateUser(u);
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);
			}

		} 
		/**
		 * deleting the employee
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_REMOVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				UserModel uModel = new UserModel();
				Cookie cookie = new Cookie("message", "employee_deleted");
				cookie.setMaxAge(9);

				int flag = 0;
				try {
					flag = (int) uModel.deleteUser(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some errors  occured !!");
					e.printStackTrace();
				}

				if (flag == 1) {
					response.addCookie(cookie);
					request.setAttribute("message", "employee  removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);

				}
			}

		}
		/**
		 * logout
		 */
		else if (requestUrl.endsWith(LOGOUT)) {

			String ssid = (String) session.getAttribute("sessionId");
			if (ssid != null) {
				session.invalidate();
				Cookie cookie = new Cookie("sessionId", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				request.setAttribute("message", "you have logged out successfully");
			}

			response.sendRedirect(LOGIN);
		}

		else if (requestUrl.endsWith(EMPLOYEE_DASHBOARD)) {
			request.setAttribute("pageName", "home");
			RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
			rd.forward(request, response);

		}
		
		else if(requestUrl.endsWith(UPLOAD_FILE))
		{
			
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("sessionId")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
			request.setAttribute("pageName", "upload");
			RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
			rd.forward(request, response);
			}
		}

	}// end process

}
