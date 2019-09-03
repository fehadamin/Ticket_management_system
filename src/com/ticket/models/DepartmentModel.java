package com.ticket.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.connection.MyConnectionProvider;
import com.ticket.dao.DepartmentDao;
import com.ticket.dao.SqlQueries;
import com.ticket.entity.Department;
import com.ticket.exceptions.DepartmentException;

public class DepartmentModel implements DepartmentDao, SqlQueries {

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	public DepartmentModel() {
		MyConnectionProvider db = new MyConnectionProvider();
		this.conn = db.getConnection();
	}

	@Override
	public Department getById(int departmentId) throws DepartmentException {
		// TODO Auto-generated method stub
		Department d = new Department();
		try {
			prep = conn.prepareStatement(GET_DEPARTMENT_BY_ID);
			prep.setInt(1, departmentId);
			result = prep.executeQuery();
			while (result.next()) {
				d.setDepartmentId(result.getInt("department_id"));
				d.setDepartmentName(result.getString("department_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new DepartmentException("get by id exception");
		}
		return d;
	}

	@Override
	public int updateById(int departmentId, Department d) throws DepartmentException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			prep = conn.prepareStatement(UPDATE_DEPARTMENT);
			prep.setString(1, d.getDepartmentName());
			prep.setInt(2, d.getDepartmentId());
			flag = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			throw new DepartmentException("duplicate entry exception");
		}
		return flag;
	}

	@Override
	public List<Department> getAll() throws DepartmentException {
		// TODO Auto-generated method stub

		List<Department> department = new ArrayList<>();

		try {
			prep = conn.prepareStatement(GET_ALL_DEPARTMENTS);
			result = prep.executeQuery();
			while (result.next()) {
				Department d = new Department();
				d.setDepartmentId(result.getInt("department_id"));
				d.setDepartmentName(result.getString("department_name"));
				department.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DepartmentException("getall exception");
			//e.printStackTrace();
		}

		return department;
	}

// query f
	public int ifExists(String name) {
		int flag = 1;
		try {
			prep = conn.prepareStatement(IF_EXISTS);
			prep.setString(1, name);
			result = prep.executeQuery();
			while (result.next() == false) {
				flag = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	// select * friom table where dname=?

	@Override
	public int remove(int departmentId) throws DepartmentException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			prep = conn.prepareStatement(REMOVE_DEPARTMENT);
			prep.setInt(1, departmentId);
			flag = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DepartmentException("remove exception");
			//e.printStackTrace();
		}

		return flag;
	}

	@Override
	public int insert(Department d) throws DepartmentException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			prep = conn.prepareStatement(INSERT_DEPARTMENT);
			prep.setString(1, d.getDepartmentName());
			flag = prep.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DepartmentException("duplicate entry exception");
		}

		return flag;
	}

}
