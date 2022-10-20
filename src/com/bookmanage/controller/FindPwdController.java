package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class FindPwdController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		BookManageService service = BookManageService.getInstance();
		boolean bool  = service.findPwd(id, name, phone);
		
		ServletContext sc = req.getServletContext();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(bool) {
			sc.setAttribute("pwdId", id);
			out.println("<script>");
			out.println("window.location.href='findPwdResult.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('존재하지 않는 회원정보입니다.')");
			out.println("window.location.href='findPwd.jsp'");
			out.println("</script>");
		}
	}
}
