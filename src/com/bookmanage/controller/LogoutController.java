package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		ServletContext sc = req.getServletContext();
		
		sc.removeAttribute("id");

		out.println("<script>");
		out.println("window.location.href='login.jsp'");
		out.println("</script>");
	}
}