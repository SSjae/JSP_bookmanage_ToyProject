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

public class BookReturnController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext sc = req.getServletContext();
		
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		BookManageService service = BookManageService.getInstance();
		String num1 = service.currentReturnNum();
		String returnsNum = null;
		
		if (num1 == null) {
			returnsNum = "r1";
		}
		else {
			String num2 = num1.substring(1, num1.length());
	        int num3 = Integer.parseInt(num2) + 1;
	        returnsNum = "r" + num3;
		}
		String id = (String) sc.getAttribute("id");
		String isbn = req.getParameter("isbn");
		String returnsDate = date.format(today);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		int countLoan = service.countLoan(id, isbn);
		int countReturn = service.countReturn(id, isbn);
		String returnLoanNum = service.returnLoanNum(id, isbn);
		
		if (countLoan == countReturn) {
			out.println("<script>");
			out.println("alert('대출을 아직 하지 않으셨습니다. 대출 후 반납 가능합니다.')");
			out.println("window.location.href='bookList.jsp'");
			out.println("</script>");
		}
		else {
			LoansReturnsVO returns = new LoansReturnsVO();
			returns.setReturnsNum(returnsNum);
			returns.setLoansNum(returnLoanNum);
			returns.setUserId(id);
			returns.setBookIsbn(isbn);
			returns.setReturnsDate(returnsDate);
			
			service.returns(returns);
			out.println("<script>");
			out.println("alert('반납 완료했습니다.')");
			out.println("window.location.href='bookList.jsp'");
			out.println("</script>");
		}
	}
}
