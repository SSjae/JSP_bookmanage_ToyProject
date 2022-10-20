<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="com.bookmanage.vo.BooksVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/bookPurchase.css">
</head>
<body>
  <% 
    String id = (String) application.getAttribute("id");
	String isbn = request.getParameter("isbn");
	BookManageService service = BookManageService.getInstance();
	BooksVO book = service.isbnBook(isbn);
	String price = book.getPrice() + "원";
  %>
  <div id="all"> 
    <header>
      <div class="header1">
        <div class="head">
          <a href="bookList.jsp" id="title">도서관리</a>
          <form action="bookSearchResult.jsp" method="post">
            <select name="searchKind">
                <option value="title">제목</option>
                <option value="author">저자</option>
                <option value="publish">출판사</option>
            </select> 
            <input type="text" name="search" placeholder="Search" required>
            <input type="submit" id="button3" value="검색">
          </form>
          <span><%=id %>님 환영합니다.<a href="logout.do" id="logout">로그아웃</a></span>
        </div>
      </div>
      <div class="menu">
        <a href="bookList.jsp">도서목록</a><a href="userLoanReturn.jsp">대출/반납 현황</a><a href="userPurchase.jsp">구매현황</a>
      </div>
    </header>
    <article>
        <form action="bookPurchase.do" method="post">
            <table>
                <tr>
                    <th rowspan="4" width=100px>책 정보</th>
                    <th width=150px height=80px>ISBN</th>
                    <td width=300px height=100%><%=book.getIsbn() %></td>
                    <input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
                    <th width=150px height=80px>제목</th>
                    <td width=300px height=100%><%=book.getTitle() %></td>
                </tr>
                <tr>
                    <th width=150px height=80px>저자</th>
                    <td width=300px height=100%><%=book.getAuthor() %></td>
                    <th width=150px height=80px>출판사</th>
                    <td width=300px height=100%><%=book.getPublish() %></td>
                </tr>
                <tr>
                    <th width=150px height=80px>종류</th>
                    <td width=300px height=100%><%=book.getKind() %></td>
                    <th width=150px height=80px>가격</th>
                    <td width=300px height=100%><%=price %></td>
                </tr>
                <tr>
                    <th width=150px height=80px>책 설명</th>
                    <td width=300px height=100% colspan="3" id="explain"><%=book.getExplains() %></td>
                </tr>
                <tr>
                    <th rowspan="4" width=100px>주문 정보</th>
                    <th width=150px height=80px>주문자</th>
                    <td width=300px height=100% colspan="3"><%=id %></td>
                </tr>
                <tr>
                    <th width=150px height=80px>결제 방식</th>
                    <td width=300px height=100% colspan="3">
                        <input type="radio" name="credit" value="현금"/>현금
                        <input type="radio" id="credit" name="credit" value="카드"/>카드
                    </td>
                </tr>
                <tr>
                    <th width=150px height=80px>주소</th>
                    <td width=300px height=100% colspan="3">
                        <input type="text" id="address" name="address" placeholder="address" autofocus required>
                    </td>
                </tr>
            </table>
            <div class="button1">
                <input type="submit" id="button2" value="구매">
            </div>
        </form>
    </article>
    <footer id ="f2">
    </footer>
  </div>
</body>
</html>