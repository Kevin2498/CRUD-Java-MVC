package com.tcs.ilp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.CustomerException.NoCustomerException;
import com.tcs.ilp.CustomerException.SameValueException;
import com.tcs.ilp.bean.Customer;
import com.tcs.ilp.util.DBConnection;

public class CustomerDAO 
{
	Connection con = null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	ResultSet result=null;
	
	public boolean addCustomer(Customer cust) throws SQLException, ClassNotFoundException
	{
		boolean flag=false;
		con = DBConnection.getConnection();
		
		ps = con.prepareStatement("insert into CUSTOMER_463730(CUSTOMERID,FIRSTNAME,LASTNAME,DOB,GENDER,CITY,COUNTRY,EMAIL,ANNUAL_SALARY) values(custSeq.nextval,?,?,?,?,?,?,?,?)");
		ps.setString(1, cust.getFirstName());
		ps.setString(2, cust.getLastName());
		ps.setDate(3, new java.sql.Date(cust.getDob().getTime()));
		ps.setString(4, cust.getGender());
		ps.setString(5, cust.getCity());
		ps.setString(6, cust.getCountry());
		ps.setString(7, cust.getEmail());
		ps.setDouble(8, cust.getAnnualSalary());
		
		int rowstatus = ps.executeUpdate();
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		
		if(rowstatus>0)
		{
			return true;
		}
		
		return flag;
	}
	
	public Customer searchCustomerById(int customerId) throws SQLException, ClassNotFoundException
	{
		Customer customer = null;
		boolean flag = false;
		
		con = DBConnection.getConnection();
		
		ps = con.prepareStatement("select * from CUSTOMER_463730 where CUSTOMERID= ?");
		ps.setInt(1, customerId);
		
		result = ps.executeQuery();
		
		while(result.next())
		{
			customer = new Customer();
			customer.setCustomerId(result.getInt(1));
			customer.setFirstName(result.getString(2));
			customer.setLastName(result.getString(3));
			customer.setDob(result.getDate(4));
			customer.setGender(result.getString(5));
			customer.setCity(result.getString(6));
			customer.setCountry(result.getString(7));
			customer.setEmail(result.getString(8));
			customer.setAnnualSalary(result.getDouble(9));
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		return customer;
		
	}
	
	public ArrayList<Customer> searchCustomerByCity(String city) throws SQLException, ClassNotFoundException
	{
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Customer customer = null;
		boolean flag = false;
		
		con = DBConnection.getConnection();
		ps = con.prepareStatement("select * from CUSTOMER_463730 where UPPER(CITY) = UPPER(?)");
		ps.setString(1, city);
		result = ps.executeQuery();
		
		while(result.next())
		{
			customer = new Customer();
			
			customer.setCustomerId(result.getInt(1));
			customer.setFirstName(result.getString(2));
			customer.setLastName(result.getString(3));
			customer.setDob(result.getDate(4));
			customer.setGender(result.getString(5));
			customer.setCity(result.getString(6));
			customer.setCountry(result.getString(7));
			customer.setEmail(result.getString(8));
			customer.setAnnualSalary(result.getDouble(9));
			
			customerList.add(customer);
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		
		return customerList;
	}
	
	public boolean updateCustomerCity(int customerId, String city) throws SQLException, ClassNotFoundException, SameValueException
	{
		con = DBConnection.getConnection();
		
		ps = con.prepareStatement("select CITY from CUSTOMER_463730 where CUSTOMERID=?");		
		ps.setInt(1, customerId);
		result = ps.executeQuery();
		
		String customerCity ="";
		while(result.next())
		{
			customerCity = result.getString(1);
		}
		if(customerCity.equals(city))
		{
			throw new SameValueException("Nothing to Modify");
		}
		
		ps1 = con.prepareStatement("update CUSTOMER_463730 set CITY = ? where CUSTOMERID = ?");
		ps1.setString(1, city);
		ps1.setInt(2, customerId);
		
		int count = ps1.executeUpdate();
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		DBConnection.closeStatement(ps1);
		
		if(count>0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean updateCustomerEmail(int customerId, String email) throws SQLException, ClassNotFoundException, SameValueException
	{
		con = DBConnection.getConnection();
		
		ps = con.prepareStatement("select EMAIL from CUSTOMER_463730 where CUSTOMERID = ?");
		ps.setInt(1, customerId);
		result = ps.executeQuery();
		
		String customerEmail ="";
		
		while(result.next())
		{
			customerEmail = result.getString(1);
		}
		
		if(customerEmail.equals(email))
		{
			throw new SameValueException("Nothing to modify");
		}
		
		ps1 = con.prepareStatement("update CUSTOMER_463730 set EMAIL = ? where CUSTOMERID = ?");
		ps1.setString(1, customerEmail);
		ps1.setInt(2, customerId);
		
		int count = ps1.executeUpdate();
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		DBConnection.closeStatement(ps1);
		
		if(count>0)
		{
			return true;
		}
		
		
		return false;
	}
	
	public boolean deleteCustomer(int customerId) throws SQLException, ClassNotFoundException, NoCustomerException
	{
		Customer customer = null;
		boolean flag = false;
		con = DBConnection.getConnection();
		
		ps = con.prepareStatement("delete from CUSTOMER_463730 where CUSTOMERID = ?");
		ps.setInt(1, customerId);
		int count = ps.executeUpdate();
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(ps);
		
		if(count>0)
		{
			return true;
		}
		else 
			throw new NoCustomerException("Customer does not exist");
	}

}
