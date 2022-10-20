package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class BookPurchaseCancleController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String purchaseNum = req.getParameter("purchaseNum");
		
		BookManageService service = BookManageService.getInstance();
		service.bookPurchaseCancle(purchaseNum);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<script>");
		out.println("alert('구매 취소되었습니다.')");
		out.println("window.location.href='userPurchase.jsp'");
		out.println("</script>");
	}
}
