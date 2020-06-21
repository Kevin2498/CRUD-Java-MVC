package com.tcs.ilp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.CustomerException.NoCustomerException;
import com.tcs.ilp.CustomerException.SameValueException;
import com.tcs.ilp.bean.Customer;
import com.tcs.ilp.dao.CustomerDAO;

public class CustomerService 
{
	CustomerDAO cdao = new CustomerDAO();
	
	public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException
	{
		return cdao.addCustomer(customer);
	}
	
	public Customer searchCustomerById(int customerId) throws SQLException, ClassNotFoundException
	{
		return cdao.searchCustomerById(customerId);
	}
	
	public ArrayList<Customer> searchCustomerByCity(String city) throws SQLException, ClassNotFoundException
	{
		return cdao.searchCustomerByCity(city);
	}
	
	public boolean updateCustomerCity (int customerId, String city) throws SQLException, ClassNotFoundException, SameValueException
	{
		return cdao.updateCustomerCity(customerId,city);
	}
	
	public boolean updateCustomerEmail (int customerId, String email) throws SQLException, ClassNotFoundException, SameValueException
	{
		return cdao.updateCustomerEmail(customerId,email);
	}
	
	public boolean deleteCustomer(int customerId) throws SQLException, ClassNotFoundException, NoCustomerException
	{
		return cdao.deleteCustomer(customerId);
	}

}
