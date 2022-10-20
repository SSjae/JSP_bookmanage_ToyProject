package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class BookCancleController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String isbn = req.getParameter("isbn");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		BookManageService service = BookManageService.getInstance();
		service.bookCancle(isbn);

		out.println("<script>");
		out.println("alert('삭제되었습니다.')");
		out.println("window.location.href='adminBook.jsp'");
		out.println("</script>");
	}
}
