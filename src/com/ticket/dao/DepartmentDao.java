package com.ticket.dao;

import java.util.List;

import com.ticket.entity.Department;
import com.ticket.exceptions.DepartmentException;

public interface DepartmentDao {
	/**
	 *  get department by using primarykey  departmentId
	 * @param departmentId
	 * @return
	 * @throws DepartmentException 
	 */
	public Department getById(int departmentId) throws DepartmentException;
	/**
	 * updating the table 
	 * @param departmentId
	 * @param d
	 * @return
	 * @throws DepartmentException 
	 */
	public int updateById(int departmentId,Department d) throws DepartmentException;
	/**
	 * get  list of departments present
	 * @return
	 * @throws DepartmentException 
	 */
	public List<Department> getAll() throws DepartmentException;
	/**
	 *  remove the department
	 * @param departmentId
	 * @return
	 * @throws DepartmentException 
	 */
	public int remove(int departmentId) throws DepartmentException;
	/**
	 * inserting a row into the department table
	 * @param d
	 * @return
	 * @throws DepartmentException 
	 */
	public int insert(Department d) throws DepartmentException;
	

}
