package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class FindIdController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		BookManageService service = BookManageService.getInstance();
		ArrayList<String> list = service.findId(name, phone);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		ServletContext sc = req.getServletContext();
		
		if(list.isEmpty()) {
			out.println("<script>");
			out.println("alert('존재하지 않는 회원정보입니다.')");
			out.println("window.location.href='findId.jsp'");
			out.println("</script>");
		}
		else {
			sc.setAttribute("idList", list);
			out.println("<script>");
			out.println("window.location.href='findIdResult.jsp'");
			out.println("</script>");
		}
	}
}