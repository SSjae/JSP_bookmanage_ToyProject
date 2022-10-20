<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="java.util.ArrayList"
    import="com.bookmanage.vo.BooksVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/bookSearchResult.css">
</head>
<body>
  <% String id = (String) application.getAttribute("id");
  	 request.setCharacterEncoding("UTF-8");
  	 String searchKind = request.getParameter("searchKind");
  	 String search = request.getParameter("search");
     BookManageService service = BookManageService.getInstance();
     ArrayList<BooksVO> list = service.searchBook(searchKind, search);
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
    <% if (list.isEmpty()) { %>
       <article>
           <table>
               <tr>
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>저자</th><th width=200px>출판사</th><th width=130px>종류</th><th width=100px>가격</th><th width=100px></th>
               </tr>
           </table>
           <h3>존재하지 않는 검색결과입니다.</h3>
        </article>
        <footer id="f1"></footer>
	<%} else {%>
        <article>
            <table>
                <tr>
                    <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>저자</th><th width=200px>출판사</th><th width=130px>종류</th><th width=100px>가격</th><th width="100px"></th>
                </tr>
                <%
                for(int i = 0; i < list.size(); i++) { 
                    BooksVO book = list.get(i);
                    int number = i + 1;
                %>
                <form action="bookDetail.jsp" method="post">
                    <tr>
                        <td width=50px><%=number %></td>
                        <td width=100px><%=book.getIsbn() %></td>
                        <input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
                        <td width=390px><%=book.getTitle() %></td>
                        <td width=180px><%=book.getAuthor() %></td>
                        <td width=200px><%=book.getPublish() %></td>
                        <td width=130px><%=book.getKind() %></td>
                        <td width=100px><%=book.getPrice() %></td>
                        <td width=100px><input type="submit" id="button2" value="상세정보"></td>
                    </tr>
                </form>
                <%} %>
            </table>
        </article>
        <footer id ="f2">
        </footer>
    <%} %>
    </div>
</body>
</html>