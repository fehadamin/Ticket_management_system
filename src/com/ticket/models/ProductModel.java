package com.ticket.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.connection.MyConnectionProvider;
import com.ticket.dao.ProductDao;
import com.ticket.dao.SqlQueries;
import com.ticket.entity.Product;
import com.ticket.exceptions.ProductException;

public class ProductModel implements ProductDao,SqlQueries {

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet result;

	public ProductModel() {
		MyConnectionProvider db = new MyConnectionProvider();
		this.conn = db.getConnection();
	}

	@Override
	public Product getById(int productId) {
		Product p=new Product();
		try {
			prep=conn.prepareStatement(GET_PRODUCT_BY_ID);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next())
			{
				p.setProductId(result.getInt("product_id"));
				p.setProductName(result.getString("product_name"));
				p.setDefaultAssignee(result.getString("default_assignee"));
				p.setParent(result.getInt("parent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public int updateById(int productId, Product p) {

		int flag = 0;
		try {
			prep = conn.prepareStatement(UPDATE_PRODUCT);
			prep.setString(1, p.getProductName());
			prep.setString(2, p.getDefaultAssignee());
			prep.setInt(3, p.getParent());
			prep.setInt(4, p.getProductId());
			flag = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<Product> getAll() {

		List<Product> product = new ArrayList<>();
		try {
			prep = conn.prepareStatement(GET_ALL_PRODUCTS);
			result = prep.executeQuery();
			while (result.next()) {
				Product p = new Product();
				p.setProductId(result.getInt("product_id"));
				p.setProductName(result.getString("product_name"));
				p.setDefaultAssignee(result.getString("default_assignee"));
				p.setParent(result.getInt("parent"));
				product.add(p);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;

	}

	@Override
	public int remove(int productId) {

		int flag = 0;

		try {
			prep = conn.prepareStatement(REMOVE_PRODUCT);
			prep.setInt(1, productId);
			flag = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public int insert(Product p) throws ProductException {

		int flag = 0;

		try {
			prep = conn.prepareStatement(INSERT_PRODUCT);
			prep.setString(1, p.getProductName());
			prep.setString(2,p.getDefaultAssignee());
			prep.setInt(3,p.getParent());
			flag = prep.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductException("Duplicate data exception ");
		}

		return flag;
	}

	public Product getByName(String name) {
		Product p=new Product();
		try {
			prep=conn.prepareStatement("SELECT * FROM products WHERE product_name = ?");
			prep.setString(1, name);
			result=prep.executeQuery();
			while(result.next())
			{
				p.setProductId(result.getInt("product_id"));
				p.setProductName(result.getString("product_name"));
				p.setDefaultAssignee(result.getString("default_assignee"));
				p.setParent(result.getInt("parent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
