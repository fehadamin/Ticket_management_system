/**
 * 
 */
package com.ticket.dao;

import java.util.List;

import com.ticket.entity.User;
import com.ticket.exceptions.DepartmentException;
import com.ticket.exceptions.UserException;

/**
 * @author fehad
 *
 */
public interface UserDao {
	
	/*
	 * get all the users present in the database
	 */
	public List<User> getAll();
	
	/*
	 * search by userId
	 */
	public User searchByUserId(int userId);
	
	/**
	 * this function will return the list of users based on the roles;
	 * @param role
	 * @return
	 */
	public List<User> searchByRoles(String role);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteUser(int userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public int updateUser(User u);
	
	/**
	 * 
	 * @return
	 * @throws DepartmentException 
	 * @throws UserException 
	 */
	public int addUser(User u) throws  UserException;
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws UserException 
	 */
	public User authenicateUser(String email,String password) throws UserException;
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	public int updatePassword(String password,int userId);
}
