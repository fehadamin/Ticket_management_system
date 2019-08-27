/**
 * 
 */
package com.ticket.dao;

import java.util.List;

import com.ticket.entity.Product;
import com.ticket.exceptions.ProductException;

/**
 * @author fehad
 *
 */

/**
 * 
 * @author fehad
 *
 */
public interface ProductDao {
	
	
	/**
	 *  getting the product by id
	 * @return
	 */
	public Product getById(int productId);
	
	/**
	 * updating based on product id 
	 * @param productId
	 * @return
	 */
	public int updateById(int productId,Product p);
	/**
	 *  get the list of products
	 * @return
	 */
	public List<Product> getAll();
	/**
	 * remove the product based on the product id
	 * @param productId
	 * @return
	 */
	public int remove(int productId);
	/**
	 * inserting into database
	 * @param p
	 * @return
	 * @throws ProductException 
	 */
	public int insert(Product p) throws ProductException;
	
	
	
	

}
