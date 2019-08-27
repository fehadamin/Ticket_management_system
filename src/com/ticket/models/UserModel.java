package com.ticket.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.connection.MyConnectionProvider;
import com.ticket.dao.SqlQueries;
import com.ticket.dao.UserDao;
import com.ticket.entity.Ticket;
import com.ticket.entity.User;
import com.ticket.exceptions.DepartmentException;
import com.ticket.exceptions.UserException;

public class UserModel implements UserDao,SqlQueries {

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;
	
	public UserModel() {
		MyConnectionProvider db = new MyConnectionProvider();
		this.conn = db.getConnection();
	}

	
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> user = new ArrayList<>();
		
		try {
			prep = conn.prepareStatement(GET_ALL_USERS);
			result = prep.executeQuery();
			while(result.next()) {
				
				User u=new User();
				u.setUserId(result.getInt("user_id"));
				u.setPassword(result.getString("password"));
				u.setName(result.getString("name"));
				u.setUserName(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setRole(result.getString("role"));
				u.setDepartmentId(result.getInt("department_id"));
				u.setHomeCompany(result.getString("home_company"));
				u.setCreated(result.getString("created"));
				user.add(u);
				
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}

	@Override
	public User searchByUserId(int userId) {
		// TODO Auto-generated method stub
		
		User u=new User();
		try {
			prep=conn.prepareStatement(SEARCH_BY_ID);
			prep.setInt(1, userId);
			result=prep.executeQuery();
			while(result.next())
			{
				u.setUserId(result.getInt("user_id"));
				u.setPassword(result.getString("password"));
				u.setName(result.getString("name"));
				u.setUserName(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setRole(result.getString("role"));
				u.setDepartmentId(result.getInt("department_id"));
				u.setHomeCompany(result.getString("home_company"));
				u.setCreated(result.getString("created"));
			
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> searchByRoles(String role) {
		// TODO Auto-generated method stub
List<User> user = new ArrayList<>();
		
		try {
			prep = conn.prepareStatement(SEARCH_BY_ROLES);
			prep.setString(1, role);
			result = prep.executeQuery();
			while(result.next()) {
				
				User u=new User();
				u.setUserId(result.getInt("user_id"));
				u.setPassword(result.getString("password"));
				u.setName(result.getString("name"));
				u.setUserName(result.getString("username"));
				u.setEmail(result.getString("email"));
				u.setRole(result.getString("role"));
				u.setDepartmentId(result.getInt("department_id"));
				u.setHomeCompany(result.getString("home_company"));
				u.setCreated(result.getString("created"));
				user.add(u);
				
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}

	@Override
	public int deleteUser(int userId) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(DELETE_USER);
			prep.setInt(1,userId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int updateUser(User u) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep=conn.prepareStatement(UPDATE_USER);
			prep.setString(1,u.getPassword());
			prep.setString(2,u.getName());
			prep.setString(3,u.getUserName());
			prep.setString(4,u.getEmail());
			prep.setString(5,u.getRole());
			prep.setInt(6, u.getDepartmentId());
			prep.setString(7,u.getHomeCompany());
			prep.setString(8,u.getCreated());
			prep.setInt(9, u.getUserId());
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int addUser(User u) throws UserException {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			prep = conn.prepareStatement(ADD_USER);
			prep.setString(1,u.getPassword());
			prep.setString(2,u.getName());
			prep.setString(3,u.getUserName());
			prep.setString(4,u.getEmail());
			prep.setString(5,u.getRole());
			prep.setInt(6, u.getDepartmentId());
			prep.setString(7, u.getHomeCompany());
			prep.setString(8, u.getCreated());
			flag=prep.executeUpdate();
		} catch (Exception e) {
			throw new UserException("duplicate entry exception");
		}
		return flag;
	}

	@Override
	public User authenicateUser(String email, String password) throws UserException {
		// TODO Auto-generated method stub

		User user = new User();
		try {
			prep = conn.prepareStatement(AUTHENICATE_USER);
			prep.setString(1, email);
			prep.setString(2, password);
			result = prep.executeQuery();

			if (result.next() == false) {
				user = null;
				System.out.println("rs is  empty");
				throw  new  UserException("incorrect values");
				// exception insert
			} else {
				setUser(user, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

	private void setUser(User user, ResultSet result2) {
		// TODO Auto-generated method stub
		// throw exceptions
		try {
			user.setUserId(result.getInt("user_id"));
			user.setPassword(result.getString("password"));
			user.setName(result.getString("name"));
			user.setUserName(result.getString("username"));
			user.setEmail(result.getString("email"));
			user.setRole(result.getString("role"));
			user.setDepartmentId(result.getInt("department_id"));
			user.setHomeCompany(result.getString("home_company"));
			user.setCreated(result.getString("created"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int updatePassword(String password,int userId) {
		// TODO Auto-generated method stub
		int flag=0;
		
		try {
			prep=conn.prepareStatement(UPDATE_PASSWORD);
			prep.setString(1,password );
			prep.setInt(2,userId);
			flag=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	
	}

}
