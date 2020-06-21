package com.tcs.ilp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.ilp.CustomerException.NoCustomerException;
import com.tcs.ilp.CustomerException.SameValueException;
import com.tcs.ilp.bean.Customer;
import com.tcs.ilp.service.CustomerService;
import com.tcs.ilp.util.DateUtil;

public class CustomerController extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String source = request.getParameter("source");
		if(source.equals("add"))
		{
			response.sendRedirect("addCustomer.html");
		}
		else if(source.equals("search"))
		{
			response.sendRedirect("searchCustomer.html");
		}
		else if(source.equals("update"))
		{
			response.sendRedirect("updateCustomer.html");
		}
		else if(source.equals("delete"))
		{
			response.sendRedirect("deleteCustomer.html");
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CustomerService service = new CustomerService();
		String source = request.getParameter("source");
		ArrayList<Customer> customerList = null;
		if(source.equals("addCustomer"))
		{
			Customer customer = new Customer();
			customer.setFirstName(request.getParameter("firstName"));
			customer.setLastName(request.getParameter("lastName"));
			customer.setDob(DateUtil.convertStringToDate(request.getParameter("dob"), "dd/MM/yyyy"));
			customer.setGender(request.getParameter("gender"));
			customer.setCity(request.getParameter("city"));
			customer.setCountry(request.getParameter("country"));
			customer.setEmail(request.getParameter("email"));
			customer.setAnnualSalary(Double.parseDouble(request.getParameter("salary")));
			
			System.out.println("Customer Details: \n"+customer);
			
			try
			{
				boolean flag = service.addCustomer(customer);
				if(flag)
				{
					response.setContentType("text/html");
					
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>View Customer</title");
					out.println("</head>");
					out.println("<body>");
					out.println("<h2>Customer Details</h2>");
					
					out.println("<table>");
					out.println("<tr>");
					out.println("<td>First Name : </td>");
					out.println("<td>"+customer.getFirstName()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Last Name : </td>");
					out.println("<td>"+customer.getLastName()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Date of Birth : </td>");
					out.println("<td>"+customer.getDob()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Gender : </td>");
					out.println("<td>"+customer.getGender()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>City : </td>");
					out.println("<td>"+customer.getCity()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Country : </td>");
					out.println("<td>"+customer.getCountry()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Email ID : </td>");
					out.println("<td>"+customer.getEmail()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Annual Salary : </td>");
					out.println("<td>"+customer.getAnnualSalary()+"</td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("</body>");
					out.println("</html>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		else if(source.equals("searchCustomer"))
		{
			String searchBy = request.getParameter("searchParam");
			if(searchBy.equals("customerId"))
			{
				try 
				{
					String customerId = request.getParameter("searchValue");
					Customer customer = service.searchCustomerById(Integer.parseInt(customerId));
					
					response.setContentType("text/html");
					
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Customer Details</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h2>Customer Details</h2>");
					
					if(customer != null)
					{
						out.println("<table>");
						out.println("<tr>");
						out.println("<td>Customer ID : </td>");
						out.println("<td>"+customer.getCustomerId()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>First Name : </td>");
						out.println("<td>"+customer.getFirstName()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Last Name : </td>");
						out.println("<td>"+customer.getLastName()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Date of Birth : </td>");
						out.println("<td>"+customer.getDob()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Gender : </td>");
						out.println("<td>"+customer.getGender()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>City : </td>");
						out.println("<td>"+customer.getCity()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Country : </td>");
						out.println("<td>"+customer.getCountry()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Email ID : </td>");
						out.println("<td>"+customer.getEmail()+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Annual Salary : </td>");
						out.println("<td>"+customer.getAnnualSalary()+"</td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else
					{
						out.println("Customer with customer Id as "+customerId+" does not exist");
					}
					out.println("</body>");
					out.println("</html>");
				}
				catch(ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
				}
				
				
			}
			else if(searchBy.equals("city"))
			{
				try
				{
					String city = request.getParameter("searchValue");
					customerList = service.searchCustomerByCity(city);
					
					response.setContentType("text/html");
					
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Customer Details</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h2>Customer Details</h2>");
					
					if(customerList.size()>0)
					{
						out.println("<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\">");
						out.println("<tr>");
						out.println("<td>Customer ID</td>");
						out.println("<td>First Name</td>");
						out.println("<td>Last Name</td>");
						out.println("<td>Date of Birth</td>");
						out.println("<td>Gender</td>");
						out.println("<td>City</td>");
						out.println("<td>Country</td>");
						out.println("<td>Email ID</td>");
						out.println("<td>Annual Salary</td>");
						out.println("</tr>");
						
						for(Customer customer : customerList)
						{
							out.println("<tr>");
							out.println("<td>"+customer.getCustomerId()+"</td>");
							out.println("<td>"+customer.getFirstName()+"</td>");
							out.println("<td>"+customer.getLastName()+"</td>");
							out.println("<td>"+customer.getDob()+"</td>");
							out.println("<td>"+customer.getGender()+"</td>");
							out.println("<td>"+customer.getCity()+"</td>");
							out.println("<td>"+customer.getCountry()+"</td>");
							out.println("<td>"+customer.getEmail()+"</td>");
							out.println("<td>"+customer.getAnnualSalary()+"</td>");
							out.println("</tr>");
						}
						out.println("</table>");
					}
					else
					{
						out.println("No Customer are from City = "+city);
					}
					out.println("</body>");
					out.println("</html>");
				}
				catch(ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		else if(source.equals("updateCustomer"))
		{
			PrintWriter out= response.getWriter();
			try
			{
				String customerId = request.getParameter("customerId");
				String updateParam = request.getParameter("updateParam");
				String updateValue = request.getParameter("updateValue");
				boolean flag=false;
				
				if(updateParam.equals("city"))
				{
					flag = service.updateCustomerCity(Integer.parseInt(customerId),updateValue);
				}
				else if(updateParam.equals("email"))
				{
					flag = service.updateCustomerEmail(Integer.parseInt(customerId),updateValue);
				}
				
				response.setContentType("text/html");
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Update Customer</title>");
				out.println("</head>");
				out.println("<body>");
				
				if(flag)
				{
					out.println("<h3>Customer record is updated successfully</h3>");
				}
				else
				{
					out.println("<h3>Sorry !!! Something went wrong. </h3>");
				}
				out.println("</body>");
				out.println("</html>");
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			catch(SameValueException e)
			{
				out.println("<h3>Sorry "+e.getMessage()+" </h3>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		
		else if(source.equals("deleteCustomer"))
		{
			PrintWriter out = response.getWriter();
			try
			{
				String customerId = request.getParameter("customerId");
				boolean status = service.deleteCustomer(Integer.parseInt(customerId));
				
				response.setContentType("text/html");
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Customer Details</title>");
				out.println("</head>");
				out.println("<body>");
				
				if(status)
				{
					out.println("<h3>Customer record is deleted successfully</h3>");
				}
				
				out.println("</body>");
				out.println("</html>");
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			catch(NoCustomerException e)
			{
				out.println("<h3>Sorry "+e.getMessage()+" </h3>");
				out.println("</body>");
				out.println("</html>");
			}
			
		}
	}

}
