<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="java.util.ArrayList"
    import="com.bookmanage.vo.BooksVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/adminBook.css">
</head>
<body>
  <% String id = (String) application.getAttribute("id");
     BookManageService service = BookManageService.getInstance();
     ArrayList<BooksVO> list = service.bookList();
  %>
  <div id="all">
    <header>
      <div class="header1">
        <div class="head">
          <a href="adminBook.jsp" id="title">도서관리</a>
          <span>관리자로 로그인하였습니다.<a href="logout.do" id="logout">로그아웃</a></span>
        </div> 
      </div>
      <div class="menu">
        <a href="adminBook.jsp">도서관리</a> <a href="adminUser.jsp">회원현황</a> <a href="adminLoanReturn.jsp">대출/반납 현황</a> <a href="adminPurchase.jsp">구매현황</a>
      </div>
    </header>
    <% if (list.isEmpty()) { %>
       <article>
           <table>
               <tr>
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>저자</th><th width=200px>출판사</th><th width=130px>종류</th><th width=100px>가격</th><th width=100px></th>
               </tr>
           </table>
           <h3>등록된 책이 없습니다.</h3>
        </article>
        <footer id="f1"></footer>
        <button id="button" onclick="window.location.href='bookRegister.jsp'">등록</button>
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
                <form action="adminBookDetail.jsp" method="post">
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
        <button id="button" onclick="window.location.href='bookRegister.jsp'">등록</button>
    <%} %>
    </div>
</body>
</html>