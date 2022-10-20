package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;
import com.bookmanage.vo.PurchasesVO;

public class BookPurchaseController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext sc = req.getServletContext();
		
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		BookManageService service = BookManageService.getInstance();
		String num1 = service.currentPurchaseNum();
		String purchasesNum = null;
		
		if(num1 == null) {
			purchasesNum = "p1";
		}
		else {
			String num2 = num1.substring(1, num1.length());
	        int num3 = Integer.parseInt(num2) + 1;
	        purchasesNum = "p" + num3;
			
		}
		String id = (String) sc.getAttribute("id");
		String isbn = req.getParameter("isbn");
		String purchasesDate = date.format(today);
		String method = req.getParameter("credit");
		String address = req.getParameter("address");
		
		PurchasesVO purchase = new PurchasesVO();
		purchase.setPurchasesNum(purchasesNum);
		purchase.setUserId(id);
		purchase.setBookIsbn(isbn);
		purchase.setPurchasesDate(purchasesDate);
		purchase.setMethod(method);
		purchase.setAddress(address);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		service.purchase(purchase);
		out.println("<script>");
		out.println("alert('구매 완료했습니다.')");
		out.println("window.location.href='bookList.jsp'");
		out.println("</script>");
	}

}
