<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="java.util.ArrayList"
    import="com.bookmanage.vo.PurchasesVO" import="com.bookmanage.vo.BooksVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/adminPurchase.css">
</head>
<body>
  <% String id = (String) application.getAttribute("id");
  	 BookManageService service = BookManageService.getInstance();
  	 ArrayList<PurchasesVO> list = service.allPurchaseList();
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
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>아이디</th><th width=100px>가격</th><th width=130px>구매일자</th><th width=100px>구매수단</th><th width=390px>주소</th><th width=100px></th>
               </tr>
           </table>
           <h3>등록된 구매내역이 없습니다.</h3>
        </article>
        <footer id="f1"></footer>
	<%} else {%>
        <article>
            <table>
                <tr>
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>아이디</th><th width=100px>가격</th><th width=130px>구매일자</th><th width=100px>구매수단</th><th width=390px>주소</th><th width=100px></th>
               	</tr>
                <%
                for(int i = 0; i < list.size(); i++) { 
                    PurchasesVO purchase = list.get(i);
                    BooksVO isbnBook = service.isbnBook(purchase.getBookIsbn());
                    int number = i + 1;
                %>
                <form action="adminBookDetail.jsp" method="post">
                    <tr>
                        <td width=50px><%=number %></td>
                        <td width=100px><%=purchase.getBookIsbn() %></td>
                        <input type="hidden" name="isbn" value="<%=purchase.getBookIsbn() %>">
                        <td width=390px><%=isbnBook.getTitle() %></td>
                        <td width=180px><%=purchase.getUserId() %></td>
                        <td width=100px><%=isbnBook.getPrice() %></td>
                        <td width=130px><%=purchase.getPurchasesDate() %></td>
                        <td width=100px><%=purchase.getMethod() %></td>
                        <td width=390px><%=purchase.getAddress() %></td>
                        <td width=100px><input type="submit" id="button2" value="책 정보"></td>
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