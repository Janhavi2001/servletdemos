package com.userweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public AddUserServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String emailId=request.getParameter("emailid");
		String password=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
				Statement statement=connection.createStatement();)
		{
			int result =statement.executeUpdate("insert into user values('"+ firstName+"', '"+lastName+"', '"+ emailId+ "', '"+password+ "')");
			PrintWriter out=response.getWriter(); 
			if(result>0) {
				out.println("<h1>User Created In DB</h1>");
			}
			else{
				out.println("<h1> Error creating User </h1>");
			}
			
			out.println("<a href=\"index.html\">Home</a>");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
