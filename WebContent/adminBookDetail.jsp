<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="com.bookmanage.vo.BooksVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/adminBookDetail.css">
</head>
<script>
function mySubmit(index) {
		if (index == 1) {
			document.detail.action = "bookUpdate.jsp";
		}
		if (index == 2) {
			if (confirm("정말 삭제하시겠습니까??") == true)
			{    //확인
				document.detail.action = "bookCancle.do";
			}
			else{   //취소
			     return false;
			}
		}
	}
</script>
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
          <a href="adminBook.jsp" id="title">도서관리</a>
          <span>관리자로 로그인하였습니다.<a href="logout.do" id="logout">로그아웃</a></span>
        </div>
      </div>
      <div class="menu">
        <a href="adminBook.jsp">도서관리</a> <a href="adminUser.jsp">회원현황</a> <a href="adminLoanReturn.jsp">대출/반납 현황</a> <a href="adminPurchase.jsp">구매현황</a>
      </div>
    </header>
    <article>
        <form name="detail" method="post">
            <table>
                <tr>
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
            </table>
            <div class="button1">
                <input type="submit" id="button2" value="수정" onClick="mySubmit(1)">
                <input type="submit" id="button2" value="삭제" onClick="mySubmit(2)">
            </div>
        </form>
    </article>
    <footer id ="f2">
    </footer>
  </div>
</body>
</html>