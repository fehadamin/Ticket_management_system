package com.ticket.dao;

import java.text.ParseException;
import java.util.List;

import com.ticket.entity.Ticket;
import com.ticket.exceptions.TicketException;

public interface TicketDao {
	
	/**
	 * get list of tickets present in the table
	 * @return
	 * @throws TicketException 
	 */
	public List<Ticket> getAll() throws TicketException;
	/**
	 * update  based on  ticketId
	 * @param ticketId
	 * @return
	 * @throws ParseException 
	 * @throws TicketException 
	 */
	public int updateById(int ticketId,Ticket t) throws ParseException, TicketException;
	/**
	 * insert into ticket table
	 * @param t
	 * @return
	 * @throws ParseException 
	 * @throws TicketException 
	 */
	public int insert(Ticket t) throws ParseException, TicketException;
	/**
	 *  get by id
	 * @param ticketId
	 * @return
	 * @throws TicketException 
	 */
	public Ticket getById(int ticketId) throws TicketException;
	/**
	 * get By Resolution
	 * @param ticketId
	 * @param resolution
	 * @return
	 */

	public List<Ticket> getByResolution(String resolution);
	/**
	 * update based on  summary
	 * @param ticketId
	 * @param summary
	 * @return
	 */
	public int updateBySummary(int ticketId,String summary);
	/**
	 *   update based on  assignee
	 * @param ticketId
	 * @param assignee
	 * @return
	 */
	public int  updateByAssignee(int ticketId,String assignee);
	/**
	 *  update based on reporter
	 * @param ticketId
	 * @param reporter
	 * @return
	 */
	public int  updateByReporter(int ticketId,String reporter);
	/**
	 *  update based on priority
	 * @param ticketId
	 * @param priority
	 * @return
	 */
	public int updateByPriority(int ticketId,String priority);
	/**
	 * update based on status
	 * @param ticketId
	 * @param status
	 * @return
	 */
	public int updateByStatus(int ticketId,String status);
	/**
	 * update based on resolution
	 * @param ticketId
	 * @param resolution
	 * @return
	 */
	public int updateByResolution(int ticketId,String resolution);	
	

	/**
	 *  get By Status
	 * 
	 * @param status
	 * @return
	 */
	public List<Ticket> getByStatus(String status);
	
	/**
	 * get By Priority
	 *
	 * @param priority
	 * @return
	 */
	public List<Ticket> getByPriority(String priority);
	/**
	 *  get By Reporter
	 * 
	 * @param reporter
	 * @return
	 */
	public List<Ticket> getByReporter(String reporter);
	/**
	 * get By Assignee
	 * @param assignee
	 * @return
	 */
	public List<Ticket> getByAssignee(String assignee);
	
	
	public int deleteTicket(int ticketId) throws TicketException;
	
}
