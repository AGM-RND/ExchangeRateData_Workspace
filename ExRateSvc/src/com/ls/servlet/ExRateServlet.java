package com.ls.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ExRateServlet extends HttpServlet{
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		RequestDispatcher view = request.getRequestDispatcher("html/exRateWeb.html");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}