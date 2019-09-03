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
	public List<User> getAll() throws UserException;
	
	/*
	 * search by userId
	 */
	public User searchByUserId(int userId) throws UserException;
	
	/**
	 * this function will return the list of users based on the roles;
	 * @param role
	 * @return
	 * @throws UserException 
	 */
	public List<User> searchByRoles(String role) throws UserException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws UserException 
	 */
	public int deleteUser(int userId) throws UserException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws UserException 
	 */
	public int updateUser(User u) throws UserException;
	
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
	 * @throws UserException 
	 */
	public int updatePassword(String password,int userId) throws UserException;
}
