package com.bookmanage.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.controller.Controller;

public class FrontController extends HttpServlet{
	
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		list = new HashMap<String, Controller>();
		list.put("/join.do", new JoinController());
		list.put("/login.do", new LoginController());
		list.put("/findId.do", new FindIdController());
		list.put("/findPwd.do", new FindPwdController());
		list.put("/findPwdResult.do", new FindPwdResultController());
		list.put("/logout.do", new LogoutController());
		list.put("/bookRegister.do", new BookRegisterController());
		list.put("/bookUpdate.do", new BookUpdateController());
		list.put("/bookCancle.do", new BookCancleController());
		list.put("/bookLoan.do", new BookLoanController());
		list.put("/bookReturn.do", new BookReturnController());
		list.put("/bookPurchase.do", new BookPurchaseController());
		list.put("/bookPurchaseCancle.do", new BookPurchaseCancleController());
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = uri.substring(contextPath.length());
		
		Controller subController = list.get(path);
		subController.execute(req, resp);
	}
}