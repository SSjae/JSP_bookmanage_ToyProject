package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;
import com.bookmanage.vo.UsersVO;

public class JoinController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		BookManageService service = BookManageService.getInstance();
		if(service.joinIdCheck(id)) {
			out.println("<script>");
			out.println("alert('아이디가 중복되었습니다.')");
			out.println("window.location.href='join.jsp'");
			out.println("</script>");
		}
		else {
			UsersVO user = new UsersVO();
			user.setId(id);
			user.setPassword(password);
			user.setName(name);
			user.setPhone(phone);
			
			service.joinUser(user);

			out.println("<script>");
			out.println("alert('회원가입에 성공하였습니다.')");
			out.println("window.location.href='login.jsp'");
			out.println("</script>");
		}
	}
}