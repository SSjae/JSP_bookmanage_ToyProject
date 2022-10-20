package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;

public class FindPwdResultController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		
		BookManageService service = BookManageService.getInstance();
		ServletContext sc = req.getServletContext();
		
		String id = (String) sc.getAttribute("pwdId");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(password1.equals(password2)) {
			service.passwordReset(id, password1);
			out.println("<script>");
			out.println("alert('비밀번호 재설정되었습니다.')");
			out.println("window.location.href='login.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('다시 시도해주세요.')");
			out.println("window.location.href='findPwdResult.jsp'");
			out.println("</script>");
		}
	}

}