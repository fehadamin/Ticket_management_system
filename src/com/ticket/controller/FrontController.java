package com.ticket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ticket.entity.Department;
import com.ticket.entity.Product;
import com.ticket.entity.Ticket;
import com.ticket.entity.TicketType;
import com.ticket.entity.User;
import com.ticket.entity.Util;
import com.ticket.exceptions.DepartmentException;
import com.ticket.exceptions.ProductException;
import com.ticket.exceptions.TicketException;
import com.ticket.exceptions.TicketTypeException;
import com.ticket.exceptions.UserException;
import com.ticket.models.DepartmentModel;
import com.ticket.models.ProductModel;
import com.ticket.models.TicketModel;
import com.ticket.models.TicketTypesModel;
import com.ticket.models.UserModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.htm")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String HOME = "home.htm";
	private final static String LOGIN = "login.htm";
	private final static String AUTH = "auth.htm";
	private final static String L = "/";
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
	
	private final static String ADMIN_VIEW_TICKET = "single-ticket.htm";

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
	private final static String FILE_UPLOAD_EXCEL = "upload_file_store.htm";

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
			try {
				process(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				process(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException, ParseException {
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
			RequestDispatcher rd = request.getRequestDispatcher(path + "index.jsp");
			rd.forward(request, response);
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
				request.setAttribute("message", "incorrect email/password");
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);

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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

					cookie.setValue("department_already_exist");
					flag = 1;
				}
//				if (flag == 0) {
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DEPARTMENT_ALL);
//				} else {
//					request.setAttribute("pageName", "home");
//
//					RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
//					rd.forward(request, response);
//				}

			}
		}

		/**
		 * // viewing all the departments
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_ALL)) {
			// checking

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
			} else {

				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dModel = new DepartmentModel();
				Department d = null;
				try {
					d = dModel.getById(Integer.parseInt(request.getParameter("id")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DepartmentException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception");
					// e.printStackTrace();
				}
				request.setAttribute("department", d);
				request.setAttribute("pageName", "edit_department");
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing the values in database
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_EDIT_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					cookie.setValue("name_exists_try_a_new_name");
					// e.printStackTrace();
				}

				response.addCookie(cookie);

				response.sendRedirect(ADMIN_DEPARTMENT_ALL);

			}
		}
		/**
		 * removing the department
		 */
		else if (requestUrl.endsWith(ADMIN_DEPARTMENT_REMOVE)) {

			String ssid = getSSID(request);
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
					response.sendRedirect(ADMIN_DEPARTMENT_ALL);
				}

			}
		}
		/**
		 * product form
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_FORM)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				Cookie cookie = new Cookie("message", "product_added");
				cookie.setMaxAge(9);

				UserModel u = new UserModel();
				request.setAttribute("users", u.getAll());
				ProductModel pm = new ProductModel();
				try {
					request.setAttribute("parents", pm.getAll());
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Excption_in_product");
					// e.printStackTrace();
				}
				request.setAttribute("pageName", "create_product");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing the values in database
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_STORE)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					cookie.setValue("Product_already_exist");
					// request.setAttribute("message", "Product already exist");
					flag = 1;
				} catch (Exception e) {

					e.printStackTrace();
				}
				// if (flag == 0) {
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_PRODUCT_ALL);
				/*
				 * } else {
				 * 
				 * request.setAttribute("pageName", "home"); RequestDispatcher rd =
				 * request.getRequestDispatcher(path + "admin/dashboard.jsp");
				 * rd.forward(request, response);
				 * 
				 * }
				 */

			}
		}
		/**
		 * view all the products
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_ALL)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				Cookie cookie = new Cookie("message", "product_updated");
				cookie.setMaxAge(9);
				UserModel u = new UserModel();
				request.setAttribute("users", u.getAll());
				ProductModel pModel = new ProductModel();
				Product p = null;
				try {
					p = pModel.getById(Integer.parseInt(request.getParameter("id")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Exception_occured");
					// e.printStackTrace();
				}
				request.setAttribute("product", p);

				try {
					request.setAttribute("parents", pModel.getAll());
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					cookie.setValue("duplicate_value");
					// e.printStackTrace();
				}
				request.setAttribute("pageName", "edit_product");
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * // storing the values in db after editing
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_EDIT_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					cookie.setValue("duplicate_values");
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				request.setAttribute("message", "product updated successfully ");
				response.sendRedirect(ADMIN_PRODUCT_ALL);

			}

		}

		/**
		 * //deleting the product
		 */
		else if (requestUrl.endsWith(ADMIN_PRODUCT_REMOVE)) {

			String ssid = getSSID(request);
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
					response.sendRedirect(ADMIN_PRODUCT_ALL);
				}

			}

		}

		/**
		 * // tickettype form
		 */

		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_FORM)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					cookie.setValue("tickettype_already_exists");
					flag = 1;
				}
				// if (flag == 0) {
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_TICKETTYPE_ALL);

				/*
				 * } else { request.setAttribute("pageName", "create_tickettype");
				 * RequestDispatcher rd = request.getRequestDispatcher(path +
				 * "admin/dashboard.jsp"); rd.forward(request, response);
				 * 
				 * }
				 */
			}
		}
		/**
		 * // viewing all the tickettypes present
		 */

		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_ALL)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					cookie.setValue("tickettypename_already_exists");
					// request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}

				/*
				 * if (flag == 1) {
				 */

				request.setAttribute("message", "ticket type updated successfully ");
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_TICKETTYPE_ALL);

				// }

			}

		}
		/**
		 * deleting the ticket type
		 */
		else if (requestUrl.endsWith(ADMIN_TICKETTYPE_REMOVE)) {

			String ssid = getSSID(request);
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
					response.sendRedirect(ADMIN_TICKETTYPE_ALL);
				}
			}

		}

		/**
		 * insert ticket
		 */
		else if (requestUrl.endsWith(ADMIN_TICKET_FORM)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);

			} else {
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dptModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (DepartmentException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e1.printStackTrace();
				}

				ProductModel pdModel = new ProductModel();
				try {
					request.setAttribute("products", pdModel.getAll());
				} catch (ProductException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("Exception_occured_in_product");
					// e1.printStackTrace();
				}
				TicketTypesModel ttModel = new TicketTypesModel();

				try {
					request.setAttribute("tickettypes", ttModel.getAll());
				} catch (TicketTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("error_occured");
					// e.printStackTrace();
				}
				response.addCookie(cookie);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				ProductModel pm = new ProductModel();
				Cookie cookie = new Cookie("message", "ticket_added");
				cookie.setMaxAge(9);

				Product p = null;

				t.setProducts("0");
				if (request.getParameter("product") != null) {
					// if pdcr is not selected
					try {
						p = pm.getById(Integer.parseInt(request.getParameter("product")));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					} catch (ProductException e) {
						// TODO Auto-generated catch block
						cookie.setValue("Expection_occured_in");
						// e.printStackTrace();
					}
					t.setProductId(Integer.parseInt(request.getParameter("product")));

				} else {
					// pdcr is selected
					t.setProductId(0);
					String[] products = request.getParameterValues("products");
					StringBuilder pids = new StringBuilder("");
					for (String s : products) {
						pids.append(s + ",");
					}
					pids.setCharAt(pids.length() - 1, ' '); // remove comma
					System.out.print(pids);

					t.setProducts(pids.toString());// setting products instead of product as pdcr can have multiple
													// products
				}

				t.setTicketKey(request.getParameter("project"));
				t.setStatus("Open");
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setDueDate(request.getParameter("dueDate"));
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setReporter(request.getParameter("reporter"));
				t.setResolution("unresolved");
				t.setComponent(Integer.parseInt(request.getParameter("component")));

				if (request.getParameter("assignee").equals("0")) {

					// default assignee
					t.setAssignee(p.getDefaultAssignee());
				} else {
					t.setAssignee(request.getParameter("assignee"));
				}
				int id = 0;
				try {
					id = tModel.insert(t);
					if (p == null) {
						tModel.updateKey(id, "PDCR" + "-" + id); // concantate
					} else {
						tModel.updateKey(id, p.getProductName() + "-" + id);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				response.addCookie(cookie);
				//response.sendRedirect(ADMIN_TICKET_ALL);
				out.println(id);

			}

		}
		/**
		 * viewing all the tickets created
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_ALL)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
			} else {

				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dptModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (DepartmentException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e1.printStackTrace();
				}
				ProductModel pdModel = new ProductModel();
				try {
					request.setAttribute("products", pdModel.getAll());
				} catch (ProductException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("Exception_occured");
					// e1.printStackTrace();
				}
				TicketTypesModel ttModel = new TicketTypesModel();

				try {
					request.setAttribute("tickettypes", ttModel.getAll());
				} catch (TicketTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e.printStackTrace();
				}

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				request.setAttribute("user", session.getAttribute("userName")); // reporter
				request.setAttribute("userId", session.getAttribute("userId"));// reporter

				TicketModel tModel = new TicketModel();
				try {
					request.setAttribute("ticket", tModel.getById(Integer.parseInt(request.getParameter("id"))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TicketException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
				}
				request.setAttribute("pageName", "edit-ticket");
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing in the database after editing
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_EDIT_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
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
				t.setResolution(request.getParameter("status").equals("Closed")?"resolved":"unresolved");
				t.setDueDate(request.getParameter("dueDate"));
				t.setComponent(Integer.parseInt(request.getParameter("component")));
				t.setCreated(request.getParameter("createdDate"));
				
				
				System.out.println(t.getTicketId());
				System.out.println(t.toString());
				try {
					t1 = tModel.getById(t.getTicketId());
				} catch (TicketException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("Expection ticket not updated");
					// e1.printStackTrace();
				}
				int flag = 0;
				try {
					try {
						flag = tModel.updateById(t.getTicketId(), t);
					} catch (TicketException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						cookie.setValue("exception_occured");
					}
					try {
						tModel.updateKey(t1.getTicketId(), t1.getTicketKey());
					} catch (TicketException e) {
						// TODO Auto-generated catch block
						cookie.setValue("exception_found");
						// e.printStackTrace();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.addCookie(cookie);
				out.println(flag);
				//response.sendRedirect(ADMIN_TICKET_ALL);

			}

		}
		/**
		 * removing the ticket
		 */

		else if (requestUrl.endsWith(ADMIN_TICKET_REMOVE)) {

			String ssid = getSSID(request);
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
					response.sendRedirect(ADMIN_TICKET_ALL);
				}
			}

		}

		/**
		 * employee create ticket form
		 */

		else if (requestUrl.endsWith(EMPLOYEE_TICKET_FORM)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dptModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (DepartmentException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e1.printStackTrace();
				}
				ProductModel pdModel = new ProductModel();
				try {
					request.setAttribute("products", pdModel.getAll());
				} catch (ProductException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("Exception_occured_in_product");
					// e1.printStackTrace();
				}
				TicketTypesModel ttModel = new TicketTypesModel();

				try {
					request.setAttribute("tickettypes", ttModel.getAll());
				} catch (TicketTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e.printStackTrace();
				}

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				request.setAttribute("user", session.getAttribute("userName")); // reporter
				request.setAttribute("userId", session.getAttribute("userId"));// reporter

				request.setAttribute("pageName", "create_ticket");
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing the ticket in database
		 */

		else if (requestUrl.endsWith(EMPLOYEE_TICKET_STORE)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				// get the session data from database

				TicketModel tModel = new TicketModel();
				Ticket t = new Ticket();
				ProductModel pm = new ProductModel();
				Cookie cookie = new Cookie("message", "Ticked_added");
				cookie.setMaxAge(9);

				Product p = null;

				t.setProducts("0");
				if (request.getParameter("product") != null) {
					try {
						p = pm.getById(Integer.parseInt(request.getParameter("product")));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ProductException e) {
						// TODO Auto-generated catch block
						cookie.setValue("Product_exception");
						// e.printStackTrace();
					}
					t.setProductId(Integer.parseInt(request.getParameter("product")));

				} else {
					t.setProductId(0);
					String[] products = request.getParameterValues("products");
					StringBuilder pids = new StringBuilder("");
					for (String s : products) {
						pids.append(s + ",");
					}
					pids.setCharAt(pids.length() - 1, ' ');
					System.out.print(pids);

					t.setProducts(pids.toString());
				}

				t.setTicketKey(request.getParameter("project"));
				t.setStatus("Open");
				t.setTicketTypeId(Integer.parseInt(request.getParameter("tickettype")));
				t.setDueDate(request.getParameter("dueDate"));
				t.setSummary(request.getParameter("summary"));
				t.setPriority(request.getParameter("priority"));
				t.setReporter(request.getParameter("reporter"));
				t.setResolution("unresolved");
				t.setComponent(Integer.parseInt(request.getParameter("component")));

				if (request.getParameter("assignee").equals("0")) {
					t.setAssignee(p.getDefaultAssignee());
				} else {
					t.setAssignee(request.getParameter("assignee"));
				}

				try {
					int id = tModel.insert(t);
					if (p == null) {
						tModel.updateKey(id, "PDCR" + "-" + id);
					} else {
						tModel.updateKey(id, p.getProductName() + "-" + id);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(EMPLOYEE_TICKET_ALL);

			}
		}

		/**
		 * viewing all the tickets
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_ALL)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dptModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (DepartmentException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_has_occured");
					// e1.printStackTrace();
				}
				ProductModel pdModel = new ProductModel();
				try {
					request.setAttribute("products", pdModel.getAll());
				} catch (ProductException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_has_occured_in_product");
					// e1.printStackTrace();
				}
				TicketTypesModel ttModel = new TicketTypesModel();

				try {
					request.setAttribute("tickettypes", ttModel.getAll());
				} catch (TicketTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_has_occured");
					// e.printStackTrace();
				}

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				TicketModel tModel = new TicketModel();
				try {
					request.setAttribute("ticket", tModel.getById(Integer.parseInt(request.getParameter("id"))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TicketException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e.printStackTrace();
				}
				request.setAttribute("pageName", "edit-ticket");
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/**
		 * storing in db after editing
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_EDIT_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
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

				try {
					t1 = tModel.getById(t.getTicketId());
				} catch (TicketException e1) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured_in_getId");
				}
				try {
					try {
						tModel.updateById(t.getTicketId(), t);
					} catch (TicketException e) {
						// TODO Auto-generated catch block
						cookie.setValue("exception_occured");
						// e.printStackTrace();
					}
					try {
						tModel.updateKey(t1.getTicketId(), t1.getTicketKey());
					} catch (TicketException e) {
						// TODO Auto-generated catch block
						cookie.setValue("updateBykey_exception");
						// e.printStackTrace();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(EMPLOYEE_TICKET_ALL);
			}

		}
		/**
		 * deleting the ticket
		 */
		else if (requestUrl.endsWith(EMPLOYEE_TICKET_REMOVE)) {

			String ssid = getSSID(request);
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
					response.sendRedirect(EMPLOYEE_TICKET_ALL);
				}
			}

		}
		/**
		 * for filters
		 */
		else if (requestUrl.endsWith(FILTER_TICKET_TABLE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				TicketModel tModel = new TicketModel();
				String role = (String) session.getAttribute("role");
				String status = request.getParameter("status");
				String priority = request.getParameter("priority");
				String assignee = request.getParameter("assignee");
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				try {
					request.setAttribute("tickets", tModel.byFilter(status, priority, assignee));
				} catch (TicketException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Exception_occured");
					// e.printStackTrace();
				}
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
				response.addCookie(cookie);
			}

			/**
			 * for employee table
			 */

		} else if (requestUrl.endsWith(ADMIN_EMPLOYEE_FORM)) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
			} else {
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				DepartmentModel dModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dModel.getAll());
				} catch (DepartmentException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e.printStackTrace();
				}

				request.setAttribute("pageName", "create_employee");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);

			}

		}
		/**
		 * storing in db
		 */

		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
					response.sendRedirect(ADMIN_EMPLOYEE_ALL);
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

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
		 * edit form
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_EDIT_FORM)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired  ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(9);
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (DepartmentException e) {
					// TODO Auto-generated catch block
					cookie.setValue("exception_occured");
					// e.printStackTrace();
				}
				UserModel userModel = new UserModel();
				request.setAttribute("user", userModel.searchByUserId(Integer.parseInt(request.getParameter("id"))));
				response.addCookie(cookie);
				request.setAttribute("pageName", "edit_employee");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}

		}
		/*
		 * storing in database
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_EDIT_STORE)) {

			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has  Expired ... ");
				response.sendRedirect(LOGIN);
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
				try {
					uModel.updateUser(u);
				} catch (Exception e) {
					cookie.setValue("value_already_exists");
				}
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_EMPLOYEE_ALL);
			}

		}
		/**
		 * deleting the employee
		 */
		else if (requestUrl.endsWith(ADMIN_EMPLOYEE_REMOVE)) {
			String ssid = getSSID(request);
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
					response.sendRedirect(ADMIN_EMPLOYEE_ALL);

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
				request.setAttribute("message", "you have logged out successfully");
			}
			Cookie cookie = new Cookie("sessionId", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect(LOGIN);
		} else if (requestUrl.endsWith("report.htm")) {
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session Expired ... ");
				response.sendRedirect(LOGIN);
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
				// request.setAttribute("pageName", "report");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/pages/report.jsp");
				rd.forward(request, response);

			}

		} else if (requestUrl.endsWith(EMPLOYEE_DASHBOARD)) {
			request.setAttribute("pageName", "home");
			RequestDispatcher rd = request.getRequestDispatcher(path + "employee/dashboard.jsp");
			rd.forward(request, response);

		}
		else if(requestUrl.endsWith(ADMIN_VIEW_TICKET)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cookie cookie = new Cookie("message","");
			cookie.setMaxAge(10);
			
			
			TicketTypesModel ttsModel = new TicketTypesModel();
			UserModel userModel = new UserModel();
			ProductModel pdModel = new ProductModel();
			
			TicketModel tModel = new TicketModel();
			Ticket t = null;
			try {
				t = tModel.getById(id);
			} catch (TicketException e) {
				// TODO Auto-generated catch block
				cookie.setValue("Ticket_not_found");
			}
			
			
			request.setAttribute("ticket", t);
			try {
				request.setAttribute("tickettypes", ttsModel.getAll());
			} catch (TicketTypeException e) {
				// TODO Auto-generated catch block
				cookie.setValue("Ticket_types_not_found");
			}
			
			response.addCookie(cookie);
			request.setAttribute("users", userModel.getAll());
			try {
				request.setAttribute("products", pdModel.getAll());
			} catch (ProductException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("pageName", "ticket-view");
			RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
			rd.forward(request, response);
		}

		else if (requestUrl.endsWith(UPLOAD_FILE)) {
			// open form
			String ssid = getSSID(request);
			if (ssid.equals("")) {
				out.println(" Session has Expired  ");
			} else {
				request.setAttribute("pageName", "upload");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
				rd.forward(request, response);
			}
		} else if (requestUrl.endsWith(FILE_UPLOAD_EXCEL)) { // display file

			String filename = upload(request, response);
			System.out.println(filename);
			List<Object> list = readExcel(filename);
			
			List<Integer> flags = new ArrayList<Integer>();
			List<String> msgs = new ArrayList<String>();
			
			for(int i = 1;i<list.size();i++) {
				List<Object> data = validateRow((ArrayList)list.get(i));
				String msg = (String) data.get(0);
				Ticket t = (Ticket) data.get(1);
				msgs.add(msg);
				if(msg == "")
					flags.add(1);
				else
					flags.add(0);
			}
			
			System.out.print(list.toString());

			request.setAttribute("flags", flags);
			request.setAttribute("msgs", msgs);
			request.setAttribute("filename", filename);
			request.setAttribute("report", list);
			request.setAttribute("pageName", "upload-show");
			RequestDispatcher rd = request.getRequestDispatcher(path + "admin/dashboard.jsp");
			rd.forward(request, response);

		} else if (requestUrl.endsWith("upload-db.htm")) {
			String filename = request.getParameter("filename");
			System.out.println(filename);
			List<Object> list = readExcel(filename);
			
			List<Integer> flags = new ArrayList<Integer>();
			List<String> msgs = new ArrayList<String>();
			
			TicketModel ticketModel = new TicketModel();
			Cookie cookie = new Cookie("message","rows_uploaded_successfully");
			cookie.setMaxAge(10);
			for(int i = 1;i<list.size();i++) {
				List<Object> data = validateRow((ArrayList)list.get(i));
				String msg = (String) data.get(0);
				Ticket t = (Ticket) data.get(1);
				msgs.add(msg);
				if(msg == "") {
					int flag = ticketModel.isAlreadyExist(t);
					if(flag == 1){
						try {
							ticketModel.insert(t);
							System.out.println("store\t" + t.toString());
						} catch (TicketException e) {
							cookie.setValue("Ticket_already_exist");
							// e.printStackTrace();
						}
					}
				}
				
			}
			
			response.addCookie(cookie);
			response.sendRedirect(ADMIN_DASHBOARD);
			
		}else if (requestUrl.endsWith("upload-db1.htm")) {// add to db depricated

			String filename = request.getParameter("filename");
			System.out.println(filename);
			List<Object> list = readExcel(filename);

			Cookie cookie = new Cookie("message", "Records_added_successfully");
			cookie.setMaxAge(9);
			// Converting sheet data
			TicketTypesModel ttm = new TicketTypesModel();
			ProductModel pm = new ProductModel();
			UserModel um = new UserModel();
			List<Ticket> tickets = new ArrayList<Ticket>(); // create a list
			
			for(int i=1;i<list.size();i++) {
//			for (Object o : list) {
				List<String> data = (ArrayList) list.get(i); // columns
				int max = data.size();
				Ticket t = new Ticket();

				TicketType tt = null;
				int flag = 0;
				try {
					tt = ttm.getByName(data.get(0));
				} catch (TicketTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Ticket_type_incorrect");
					flag = 1;
					// e.printStackTrace();
				}

				t.setTicketTypeId(tt.getTicketTypeId());
				t.setTicketKey(data.get(1));
				t.setSummary(data.get(2));
				t.setAssignee(data.get(3));
				t.setReporter(data.get(4));
				t.setPriority(data.get(5));
				t.setStatus(data.get(6));
				t.setResolution(data.get(7));

				String[] str = data.get(1).split("-", -2);
				if (!str[0].equals("PDCR")) {
					Product p = pm.getByName(str[0]); // for products
					t.setProductId(p.getProductId());
					t.setComponent(0);
					t.setProducts("0");
				} else {

					t.setProducts("0");
				}
				int k = -1;
				k = t.getTicketKey().indexOf('-');// key
				if (k < 0) {
					flag = 1;
					cookie.setValue("Ticket_Key_incorrect");
				}
				// SimpleDateFormat("dd-M-yy");

				java.util.Date today = new java.util.Date();
				t.setDueDate(new SimpleDateFormat("dd-M-yy").format(today));
				System.out.println("k:" + k);
				System.out.println(data.get(8) + "|" + data.get(9));
				try {
					java.util.Date created = new SimpleDateFormat("dd/MM/yyyy").parse(data.get(8));
					java.util.Date updated = new SimpleDateFormat("dd/MM/yyyy").parse(data.get(9));

					t.setCreated(data.get(8));
					t.setUpdated(data.get(9));
				} catch (Exception e) {
					flag = 1;
					cookie.setValue("Ticket_Dates_incorrect");
					// System.out.println("Dates incorrect");
				}

				System.out.println("flag:" + flag);
				if (flag == 0) {
					tickets.add(t);
				}

			}

			// storing to database ticket to db
			TicketModel ticketModel = new TicketModel();
			System.out.println("TS" + tickets.size());
			for (int i = 0; i < tickets.size(); i++) {
				Ticket t = tickets.get(i);
				int flag = ticketModel.isAlreadyExist(t);
				int a = um.getUserByName(t.getAssignee());// assignee
				int r = um.getUserByName(t.getReporter());// reporter

				Product p = null;
				try {
					p = pm.getById(t.getProductId());
				} catch (ProductException e1) {
					cookie.setValue("Product_name_incorrect");
				}
				System.out.print("f" + flag + "a" + a + "r" + r + "PT" + (p.getProductId() > 0 || t.getTicketId() == 11)
						+ "T" + t.getTicketTypeId());
				System.out.println("\t" + t.toString());

				if (flag == 1) {
 
					if (a == 0) {
						if (r == 0) {
							if ((p.getProductId() > 0 || t.getTicketTypeId() == 11)) {
								try {
									try {
										ticketModel.insert(t);
										System.out.println("store\t" + t.toString());
									} catch (TicketException e) {
										cookie.setValue("Ticket_not_added");
										// e.printStackTrace();
									}
								} catch (ParseException e) {
									cookie.setValue("Parsering_error_occured");
								}

							} else {
								cookie.setValue("Product_not_found");
							}
						} else {
							cookie.setValue("Reporter_not_exist");
						}
					} else {
						cookie.setValue("Assignee_not_exist");
					}

				} else {
					cookie.setValue("Ticket_already_exist");
				}
//				System.out.println(t.toString());	
			}

			response.addCookie(cookie);
			System.out.println("Successful ");
			response.sendRedirect(ADMIN_DASHBOARD);
		}

	}// end process
	
	private List<Object> validateRow(List<String> row) {
		
		TicketTypesModel ttm = new TicketTypesModel();
		ProductModel pm = new ProductModel();
		UserModel um = new UserModel();
		int max = row.size();
		Ticket t = new Ticket();
		TicketType tt = null;
		int flag = 0;
		String message = "";
		List<Object> data = new ArrayList<Object>();
		// ticket type
		try {
			tt = ttm.getByName(row.get(1));
			t.setTicketTypeId(tt.getTicketTypeId());
		} catch (TicketTypeException e) {
			// TODO Auto-generated catch block
			message = "Ticket Type not found";
			flag = 1;
		}catch(Exception e) {
			message = "Ticket Type not found";
		}
		
		if(!row.get(1).equals("PDCR")) {
			
			// product
			try {
				Product p = pm.getByName(row.get(2));
				if(p != null) 
					t.setProductId(p.getProductId());
				else 
					message = message + ", Product not found";
			}catch(Exception e) {
				message = message + ", Product not found";
			}
			
			// component
			try {
				Product p = pm.getByName(row.get(3));
				if(p != null) 
					t.setProductId(p.getProductId());
				else 
					message = message + ", Component not found";
			}catch(Exception e) {
				message = message + ", Component not found";
			}
			
		}else {
			//message += "nd";
		}
		
		// priority
		if(row.get(5).equals("Blocker") || row.get(5).equals("Critical") || row.get(5).equals("Major"))
		{
			t.setPriority(row.get(5));
		}else {
			message = message + ", Priority incorrect";
		}
		
		// status, resulation
		if(row.get(8).equals("Open") || row.get(8).equals("Closed") || row.get(8).equals("In Progress") || row.get(8).equals("Resolved"))
		{
			t.setStatus(row.get(8));
			t.setResolution("unresolved");
		}else {
			message = message + ", Status incorrect";
		}
		
		// assignee
		try {
			int a = um.getUserByName(row.get(6));
			if(a== 0)
				t.setAssignee(row.get(6));
			else
				message = message + ", Assignee not found";
		}catch(Exception e) {
			message = message + ", Assignee not found";
		}
		
		// reporter
		try {
			int a = um.getUserByName(row.get(7));
			if(a== 0)
				t.setReporter(row.get(7));
			else
				message = message + ", Reporter not found";
			
		}catch(Exception e) {
			message = message + ", Reporter not found";
		}
		
		// ticket key	
		int k = -1;
		k = row.get(0).indexOf('-');// key
		if (k < 0) {
			message = message + ", Ticket key incorrect";
		}else {
			t.setTicketKey(row.get(0));
		}
		
		// summary
		t.setSummary(row.get(4));
		t.setProducts("0");
		// dates
		try {
			java.util.Date due = new SimpleDateFormat("dd/MM/yyyy").parse(row.get(9));
			java.util.Date created = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(row.get(10));
			java.util.Date updated = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(row.get(11));
			
			if(Util.getDays(row.get(10), row.get(9)) > 0) {
				t.setDueDate(new SimpleDateFormat("dd-M-yy").format(due));
				t.setCreated(new SimpleDateFormat("dd-M-yy HH:mm:ss").format(created));
				t.setUpdated(new SimpleDateFormat("dd-M-yy HH:mm:ss").format(updated));
			}
			
		} catch (Exception e) {
			message = message + ", Problem in due date and created date";
			e.printStackTrace();
		}
		
		
		TicketModel ticketModel = new TicketModel();
		int f = ticketModel.isAlreadyExist(t);
		if(f==0) {
			message = message + ", Duplicate ticket";
		}
		
		data.add(message);
		data.add(t);
		System.out.println("MESSAGE:"+message);
		System.out.println("TICKET:"+t.toString());
		return data;
	}
	
	private String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServletContext servletContext = request.getServletContext();
		String path = servletContext.getRealPath("/");
		String file_name = null;
		String tempName = "";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return "";
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return "";
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (file_name == null) {
						if (fileItem.getFieldName().equals("file_name")) {
							file_name = fileItem.getString();
						}
					}
				} else {
					if (fileItem.getSize() > 0) {
						tempName = fileItem.getName();
						System.out.println(path);
						// "C:\\Users\\fehad\\eclipse-workspace\\Ticket_management_(1)\\WebContent\\resources\\images\\"
						fileItem.write(new File( "C:\\Users\\fehad\\eclipse-workspace\\Ticket_management\\WebContent\\resources\\images\\"+ fileItem.getName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// response.sendRedirect("admin-upload-data.htm?file="+file_name+"&tmp="+tempName);
		}

		return tempName;
	}// end upload

	public List<Object> readExcel(String filename) {

		Workbook workbook = null;
		List<Object> report = new ArrayList<Object>();
		try {
			FileInputStream file = new FileInputStream(
					new File("C:\\Users\\fehad\\eclipse-workspace\\Ticket_management\\WebContent\\resources\\images\\"
							+ filename));
			try {
				workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				int col = sheet.getColumns();

				System.out.println(rows + "|" + col);

				for (int i = 0; i < rows; i++) {

					List<String> columns = new ArrayList<String>();
					for (int j = 0; j < col; j++) {

						Cell cell1 = sheet.getCell(j, i);
						System.out.print(cell1.getContents() + "\t");
						columns.add(cell1.getContents());
					}
					System.out.print("\n");
					report.add(columns);
				}

			} catch (BiffException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return report;
		// return null;

	}

	public String getSSID(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String ssid = "";
		for (Cookie c : cookies) {
			if (c.getName().equals("sessionId")) {
				ssid = c.getValue();
			}
		}
		return ssid;
	}

}
