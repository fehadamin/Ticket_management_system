package com.ticket.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.connection.MyConnectionProvider;
import com.ticket.dao.SqlQueries;
import com.ticket.dao.TicketTypesDao;
import com.ticket.entity.Product;
import com.ticket.entity.TicketType;
import com.ticket.exceptions.ProductException;
import com.ticket.exceptions.TicketTypeException;

public class TicketTypesModel implements TicketTypesDao,SqlQueries {

	
	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	
	public TicketTypesModel() {
		MyConnectionProvider db = new MyConnectionProvider();
		this.conn = db.getConnection();
	}

	@Override
	public int insert(TicketType tt) throws TicketTypeException {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(INSERT_TICKETTYPE);
			prep.setString(1, tt.getTicketName());
			flag=prep.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			throw new TicketTypeException("Duplicate data exception "); 
		}
		return flag;
	}

	public int updateById(int ticketTypeId,TicketType t) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_TICKETTYPE);
			prep.setString(1,t.getTicketName());
			prep.setInt(2, t.getTicketTypeId());
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<TicketType> getAll() {
		// TODO Auto-generated method stub
		
		List<TicketType> tickettype=new ArrayList<>();
		try {
			prep=conn.prepareStatement(GET_ALL_TICKETTYPES);
			result=prep.executeQuery();
			while(result.next())
			{
				TicketType t=new TicketType();
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setTicketName(result.getString("ticket_name"));
				tickettype.add(t);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickettype;
	}

	@Override
	public int remove(int ticketTypeId) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(REMOVE_TICKETTYPE);
			prep.setInt(1, ticketTypeId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public TicketType getById(int ticketTypeId) {
		// TODO Auto-generated method stub
		TicketType t=new TicketType();
		try {
			prep=conn.prepareStatement(GET_TICKETTYPE_BY_ID);
			prep.setInt(1,ticketTypeId) ;
			result = prep.executeQuery();
			while(result.next())
			{
				t.setTicketTypeId(result.getInt("ticket_type_id"));
				t.setTicketName(result.getString("ticket_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public TicketType getByName(String name) {
		TicketType p=new TicketType();
		try {
			prep=conn.prepareStatement("SELECT * FROM ticket_types WHERE ticket_name = ?");
			prep.setString(1, name);
			result=prep.executeQuery();
			while(result.next())
			{
				p.setTicketTypeId(result.getInt("ticket_type_id"));
				p.setTicketName(result.getString("ticket_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
