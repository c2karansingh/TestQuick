package com;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import util.Database;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/CheckCredentials")
public class Login extends HttpServlet 
{
	
	private final String NO_SUCH_USER="Yikes!!No such user exists.";
	private final String WRONG_PASSWORD="Oops! Wrong username and/or password.";
	private final String USER_VALIDATED="true";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		PrintWriter out =  response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginDao errMessage = new LoginDao();
		
		int statusCode = errMessage.returnError(username, password);
		switch (statusCode) 
		{
		case -1:
					out.println(NO_SUCH_USER);
					break;
		case 0:
					out.println(WRONG_PASSWORD);
					break;
		case 1:
					out.println(true);
					break;
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginDao errMessage = new LoginDao();
		
		RequestDispatcher dispatch = null;
		int statusCode = errMessage.returnError(username, password);
		HttpSession session = request.getSession();

		if(statusCode==1)
		{
			session.setAttribute("username", username);
			dispatch = request.getRequestDispatcher("home.jsp");
		}
		else
		{
			dispatch= request.getRequestDispatcher("index.html");
		}
		dispatch.forward(request, response);

	}

}
