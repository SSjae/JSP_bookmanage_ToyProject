package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class LoginController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		ServletContext sc = req.getServletContext();
				
		BookManageService service = BookManageService.getInstance();
		int result = service.login(id, password);
		
		if(id.equals("admin")) {
			if(result == 1) {
				sc.setAttribute("id",id);
				out.println("<script>");
				out.println("window.location.href='adminBook.jsp'");
				out.println("</script>");
			}
			else if(result == 0) {
				out.println("<script>");
				out.println("alert('로그인에 실패하였습니다.')");
				out.println("window.location.href='login.jsp'");
				out.println("</script>");
			}
			else if(result == -1) {
				out.println("<script>");
				out.println("alert('로그인에 실패하였습니다.')");
				out.println("window.location.href='login.jsp'");
				out.println("</script>");
			}
		}
		else {
			if(result == 1) {
				sc.setAttribute("id",id);
				out.println("<script>");
				out.println("window.location.href='bookList.jsp'");
				out.println("</script>");
			}
			else if(result == 0) {
				out.println("<script>");
				out.println("alert('로그인에 실패하였습니다.')");
				out.println("window.location.href='login.jsp'");
				out.println("</script>");
			}
			else if(result == -1) {
				out.println("<script>");
				out.println("alert('로그인에 실패하였습니다.')");
				out.println("window.location.href='login.jsp'");
				out.println("</script>");
			}
		}
	}
}
