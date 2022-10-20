<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="com.bookmanage.vo.BooksVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/bookUpdate.css">
</head>
<script>
	function updateCheck() {
		 if (confirm("정말 수정하시겠습니까??") == true) {    //확인
		     document.removefrm.submit();
		 }
		 else{   //취소
		     return false;
		 }
	}
</script>
<body> 
  <%
  	String id = (String) application.getAttribute("id");
  	String isbn = request.getParameter("isbn");
	BookManageService service = BookManageService.getInstance();
	BooksVO book = service.isbnBook(isbn);
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
        <form action="bookUpdate.do" method="post">
            <table>
                <tr>
                    <th width=150px height=80px>ISBN</th>
                    <td width=300px><%=book.getIsbn() %></td>
                    <input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
                    <th width=150px height=80px>제목</th>
                    <td width=300px><input type="text" name="title" value="<%=book.getTitle() %>" placeholder="title" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>저자</th>
                    <td width=300px><input type="text" name="author" value="<%=book.getAuthor() %>"  placeholder="author" autofocus required></td>
                    <th width=150px height=80px>출판사</th>
                    <td width=300px><input type="text" name="publish" value="<%=book.getPublish() %>" placeholder="publish" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>종류</th>
                    <td width=300px>
                        <select name="kind">
                            <option value="소설" <%if((book.getKind()).equals("소설")) { %> selected <%} %> >소설</option>
                            <option value="수필" <%if((book.getKind()).equals("수필")) { %> selected <%} %> >수필</option>
                            <option value="시" <%if((book.getKind()).equals("시")) { %> selected <%} %> >시</option>
                            <option value="사회정치" <%if((book.getKind()).equals("사회정치")) { %> selected <%} %> >사회정치</option>
                            <option value="자연과학" <%if((book.getKind()).equals("자연과학")) { %> selected <%} %> >자연과학</option>
                            <option value="인문" <%if((book.getKind()).equals("인문")) { %> selected <%} %> >인문</option>
                            <option value="예술" <%if((book.getKind()).equals("예술")) { %> selected <%} %> >예술</option>
                            <option value="종교" <%if((book.getKind()).equals("종교")) { %> selected <%} %> >종교</option>
                            <option value="참고서" <%if((book.getKind()).equals("참고서")) { %> selected <%} %> >참고서</option>
                            <option value="역사" <%if((book.getKind()).equals("역사")) { %> selected <%} %> >역사</option>
                            <option value="유아" <%if((book.getKind()).equals("유아")) { %> selected <%} %> >유아</option>
                            <option value="여행" <%if((book.getKind()).equals("여행")) { %> selected <%} %> >여행</option>
                            <option value="기타" <%if((book.getKind()).equals("기타")) { %> selected <%} %> >기타</option>
                        </select> 
                    </td>
                    <th width=150px height=80px>가격</th>
                    <td width=300px><input type="text" name="price" value="<%=book.getPrice() %>" placeholder="price" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>책 설명</th>
                    <td width=300px height=100% colspan="3" style="padding: 30px 0;"><textarea id="explain" cols=97px rows = 10px name="explains" placeholder="explains" style="font-family:맑은 고딕" autofocus required><%=book.getExplains() %></textarea></td>
                </tr>
            </table>
            <div class="button1">
                <input type="submit" id="button2" value="수정" onClick="updateCheck()">
            </div>
        </form>
    </article>
    <footer id ="f2">
    </footer>
  </div>
</body>
</html>