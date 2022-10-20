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
import com.bookmanage.vo.LoansReturnsVO;

public class BookLoanController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext sc = req.getServletContext();
		
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		BookManageService service = BookManageService.getInstance();
		String num1 = service.currentLoanNum();
		String loansNum = null;
		
		if (num1 == null) {
			loansNum = "l1";
		}
		else {
			String num2 = num1.substring(1, num1.length());
	        int num3 = Integer.parseInt(num2) + 1;
	        loansNum = "l" + num3;
		}
		String id = (String) sc.getAttribute("id");
		String isbn = req.getParameter("isbn");
		String loansDate = date.format(today);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		int countLoan = service.countLoan(id, isbn);
		int countReturn = service.countReturn(id, isbn);
		
		if (countLoan != countReturn) {
			out.println("<script>");
			out.println("alert('이미 대출을 받으셨습니다. 반납 후 대출 가능합니다.')");
			out.println("window.location.href='bookList.jsp'");
			out.println("</script>");
		}
		else {
			LoansReturnsVO loan = new LoansReturnsVO();
			loan.setLoansNum(loansNum);
			loan.setUserId(id);
			loan.setBookIsbn(isbn);
			loan.setLoansDate(loansDate);
			
			service.loan(loan);
			out.println("<script>");
			out.println("alert('대출 완료했습니다.')");
			out.println("window.location.href='bookList.jsp'");
			out.println("</script>");
		}
	}

}
