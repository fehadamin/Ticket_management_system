package com.ticket.dao;

import java.util.List;

import com.ticket.entity.TicketType;
import com.ticket.exceptions.TicketTypeException;

public interface TicketTypesDao {
	/**
	 *  insert row into TicketType table
	 * @param tt
	 * @return
	 * @throws TicketTypeException 
	 */
	public int  insert(TicketType tt) throws TicketTypeException;
	
	/**
	 *  get all the tickettype row present in the table
	 * @return
	 * @throws TicketTypeException 
	 */
	public List<TicketType> getAll() throws TicketTypeException;
	/**
	 * remove 
	 * @param ticketTypeId
	 * @return
	 * @throws TicketTypeException 
	 */
	public int remove(int ticketTypeId) throws TicketTypeException;
	/**
	 *  get the details of one tickettype row based on TicketType
	 * @param ticketTypeId
	 * @return
	 */
	public TicketType getById(int ticketTypeId);
	/**
	 * 
	 * @param ticketTypeId
	 * @param t
	 * @return
	 * @throws TicketTypeException 
	 */
	public int updateById(int ticketTypeId,TicketType t) throws TicketTypeException;
}
