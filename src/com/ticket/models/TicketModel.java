package com.ticket.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ticket.connection.MyConnectionProvider;
import com.ticket.dao.SqlQueries;
import com.ticket.dao.TicketDao;
import com.ticket.entity.Ticket;

public class TicketModel implements TicketDao,SqlQueries{

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	private Statement stmt;
	
	
	public TicketModel() {
		MyConnectionProvider db = new MyConnectionProvider();
		this.conn = db.getConnection();
	}

	
	
	@Override
	public List<Ticket> getAll() {
		// TODO Auto-generated method stub

		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_ALL_TICKETS);
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setDueDate(result.getString("due_date"));
				t.setStatus(result.getString("status"));
				t.setComponent(result.getInt("component"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	public int deleteTicket(int ticketId) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(DELETE_TICKET);
			prep.setInt(1,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<Ticket> byFilter(String status,String priority,String assignee){
		List<Ticket> ticket = new ArrayList<>();
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM tickets WHERE 0 = 0 ");
			
			int p = 0,s=0;
			if(!priority.equals("0")) {
				query.append(" AND priority = '" + priority +"'");
				p=1;
			}
			if(!status.equals("0")) {
				
				query.append(" AND status = '"+status+"'");
				s=1;
			}
			if(!assignee.equals("0") && assignee != null ) {
				
				query.append(" AND assignee = '"+ assignee+"'");
			}
			
			
			stmt = conn.createStatement();
			System.out.println(query);
			result = stmt.executeQuery(query.toString());
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setComponent(result.getInt("component"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setDueDate(result.getString("due_date"));
				t.setStatus(result.getString("status"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
		
	}
	
	
	@Override
	public int updateById(int ticketId, Ticket t) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");
		int flag = 0;
		try {
			prep = conn.prepareStatement(UPDATE_TICKET);
			prep.setString(1, t.getTicketKey());
			prep.setInt(2, t.getTicketTypeId());
			prep.setInt(3, t.getProductId());
			prep.setString(4, t.getSummary());
			prep.setString(5, t.getAssignee());
			prep.setString(6, t.getReporter());
			prep.setString(7, t.getPriority());
			prep.setString(8, t.getStatus());
			prep.setString(9, t.getResolution());
			Date due = format.parse(t.getDueDate());
			java.sql.Date sDue = new java.sql.Date(due.getTime());
			prep.setDate(10,sDue);
			prep.setString(11, t.getCreated());
			prep.setString(12, t.getUpdated());
			prep.setInt(13, t.getComponent());
			prep.setInt(14, t.getTicketId());
			flag = prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}
	public int updateKey(int id,String key) {
		
		int flag = 0;
		try {
			prep = conn.prepareStatement(UPDATE_KEY);
			prep.setString(1, key);
			prep.setInt(2, id);
			
			flag = prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public int insert(Ticket t) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yy");
		int flag = 0;
		try {
			prep = conn.prepareStatement(INSERT_TICKET,Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, t.getTicketKey());
			prep.setInt(2, t.getTicketTypeId());
			prep.setInt(3, t.getProductId());
			prep.setString(4, t.getSummary());
			prep.setString(5, t.getAssignee());
			prep.setString(6, t.getReporter());
			prep.setString(7, t.getPriority());
			prep.setString(8, t.getStatus());
			prep.setString(9, t.getResolution());
			
			Date due = format.parse(t.getDueDate());
			java.sql.Date sDue = new java.sql.Date(due.getTime());
			prep.setDate(10,sDue);
			//prep.setString(11, t.getCreated());
			//prep.setString(12, t.getUpdated());
			prep.setInt(11, t.getComponent());
			prep.setString(12, t.getProducts());
			flag = prep.executeUpdate();
			
			result = prep.getGeneratedKeys();
			while(result.next()) {
				flag = result.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Ticket getById(int ticketId) {
		// TODO Auto-generated method stub
		Ticket t = new Ticket();
		try {
			prep = conn.prepareStatement(GET_TICKET_BY_ID);
			prep.setInt(1, ticketId);
			result = prep.executeQuery();
			while (result.next()) {

				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setStatus(result.getString("status"));
				t.setComponent(result.getInt("component"));
				t.setDueDate(result.getString("due_date"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;

	}

	@Override
	public List<Ticket> getByResolution(String resolution) {
		// TODO Auto-generated method stub
		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_BY_RESOLUTION);
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setStatus(result.getString("status"));
				t.setDueDate(result.getString("due_date"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setComponent(result.getInt("component"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	@Override
	public int updateBySummary(int ticketId, String summary) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_SUMMARY_BY_ID);
			prep.setString(1,summary );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int updateByAssignee(int ticketId, String assignee) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_ASSIGNEE_BY_ID);
			prep.setString(1,assignee );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	//updating reporter by id 
	public int updateByReporter(int ticketId, String reporter) {
		
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_REPORTER_BY_ID);
			prep.setString(1,reporter );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int updateByPriority(int ticketId, String priority) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_PRIORITY_BY_ID);
			prep.setString(1,priority );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
		
	}

	@Override
	public int updateByStatus(int ticketId, String status) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_STATUS_BY_ID);
			prep.setString(1,status );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int updateByResolution(int ticketId, String resolution) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_RESOLUTION_BY_ID);
			prep.setString(1,resolution );
			prep.setInt(2,ticketId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<Ticket> getByStatus(String status) {
		// TODO Auto-generated method stub
		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_BY_STATUS);
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setStatus(result.getString("status"));
				t.setComponent(result.getInt("component"));
				t.setDueDate(result.getString("due_date"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
		
	}

	@Override
	public List<Ticket> getByPriority(String priority) {
		// TODO Auto-generated method stub
		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_BY_PRIORITY);
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setComponent(result.getInt("component"));
				t.setStatus(result.getString("status"));
				t.setDueDate(result.getString("due_date"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	@Override
	public List<Ticket> getByReporter(String reporter) {
		// TODO Auto-generated method stub
		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_BY_REPORTER);
			prep.setString(1, reporter);
			System.out.println(prep.toString());
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setStatus(result.getString("status"));
				t.setDueDate(result.getString("due_date"));
				t.setComponent(result.getInt("component"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	@Override
	public List<Ticket> getByAssignee(String assignee) {
		// TODO Auto-generated method stub
		List<Ticket> ticket = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_BY_ASSIGNEE);
			prep.setString(1, assignee);
			result = prep.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt("ticket_id"));
				t.setTicketKey(result.getString("ticket_key"));
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setProductId(result.getInt("product_id"));
				t.setSummary(result.getString("summary"));
				t.setAssignee(result.getString("assignee"));
				t.setReporter(result.getString("reporter"));
				t.setPriority(result.getString("priority"));
				t.setStatus(result.getString("status"));
				t.setComponent(result.getInt("component"));
				t.setDueDate(result.getString("due_date"));
				t.setProducts(result.getString("products"));
				t.setResolution(result.getString("resolution"));
				t.setCreated(result.getString("created"));
				t.setUpdated(result.getString("updated"));
				ticket.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	public int isAlreadyExist(Ticket t) {
		int flag= 0;
		try {
			prep = conn.prepareStatement("SELECT * FROM tickets WHERE ticket_key = ? AND ticket_type_id = ? AND product_id =? AND assignee =?");
			prep.setString(1, t.getTicketKey());
			prep.setInt(2, t.getTicketTypeId());
			prep.setInt(3, t.getProductId());
			prep.setString(4, t.getAssignee());
			result = prep.executeQuery();
			//System.out.println(prep.toString());
			if (result.next() == false) {
				flag = 1;// empty
				System.out.println("rs is  empty");
			}else {
				System.out.println("rs availible");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	
}
