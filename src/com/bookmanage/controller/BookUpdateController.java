package com.bookmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmanage.service.BookManageService;
import com.bookmanage.vo.BooksVO;

public class BookUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String isbn = req.getParameter("isbn");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String publish = req.getParameter("publish");
		String kind = req.getParameter("kind");
		String explains = req.getParameter("explains");
		int price = Integer.parseInt(req.getParameter("price"));
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		BookManageService service = BookManageService.getInstance();
		
		BooksVO book = new BooksVO();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublish(publish);
		book.setKind(kind);
		book.setExplains(explains);
		book.setPrice(price);
		
		service.bookUpdate(book);
		
		out.println("<script>");
		out.println("alert('수정이 완료되었습니다.')");
		out.println("window.location.href='adminBook.jsp'");
		out.println("</script>");
	}
}