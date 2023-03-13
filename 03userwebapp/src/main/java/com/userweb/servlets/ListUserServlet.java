package com.userweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	  private Connection connection;
	  public void init(ServletConfig config) {
		  try {
			  
			  Class.forName("com.mysql.jdbc.Driver");
			  String dburl = config.getInitParameter("dburl");
			  
			String dbuser = config.getInitParameter("dbuser");
			
			String dbpassword = config.getInitParameter("dbpassword");
			connection=DriverManager.getConnection(dburl,dbuser,dbpassword);
			  
		  }
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
		  catch(ClassNotFoundException e) {
			  e.printStackTrace();
		  }
	  }
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		  String emailId=request.getParameter("emailId");
		  try (Statement statement=connection.createStatement();
			  ResultSet result=statement.executeQuery("select * from user ");){
			  PrintWriter out=response.getWriter();
			  response.setContentType("text/html");
			  out.println("<table border=1>");
			  out.println("<tr>");
			  out.println("<th>First Name</th>");
			  out.println("<th>Last Name</th>");
			  out.println("<th>Email</th>");
			  out.println("</tr>");
			  while(result.next()) {
				  String firstName=result.getString(1);
				  String lastName=result.getString(2);
				  String email=result.getString(3);
				  out.println("<tr>");
				  out.println("<th>"+firstName+"</th>");
				  out.println("<th>"+lastName+"</th>");
				  out.println("<th>"+email+"</th>");
				  out.println("</tr>");
			  }
			  out.println("</table>");
			  out.println("<a href=\"index.html\">Home</a>");
		  }
		  catch(SQLException e ) {
			  e.printStackTrace();
		  }
	  }
	  public void destroy () {
		  try {
			  if(connection !=null) {
				  connection.close();
			  }
		  }
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
	  }
}
	
