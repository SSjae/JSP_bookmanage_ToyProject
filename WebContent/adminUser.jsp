<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="java.util.ArrayList"
    import="com.bookmanage.vo.UsersVO"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/adminUser.css">
</head>
<body>
  <% String id = (String) application.getAttribute("id");
  	 BookManageService service = BookManageService.getInstance();
  	 ArrayList<UsersVO> list = service.userAllList();
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
                 <th width=50px>번호</th><th width=250px>아이디</th><th width=250px>비밀번호</th><th width=150px>이름</th><th width=250px>전화번호</th>
           </table>
           <h3>등록된 회원이 없습니다.</h3>
       </article>
       <footer id="f1"></footer>
    <%} else {%>
       <article>
            <table>
               	<tr>
                    <th width=50px>번호</th><th width=250px>아이디</th><th width=250px>비밀번호</th><th width=150px>이름</th><th width=250px>전화번호</th>
                </tr>
                <%
            	for(int i = 0; i < list.size(); i++) { 
            		UsersVO user = list.get(i);
            		int number = i + 1;
            		String pwd = user.getPassword();
            		String repeat = new String(new char[pwd.length()]).replace("\0", "*");
            	%>
                <tr>
                    <td width=50px><%=number %></td>
                    <td width=250px><%=user.getId() %></td>
                    <td width=250px><%=repeat %></td>
                    <td width=150px><%=user.getName() %></td>
                    <td width=250px><%=user.getPhone() %></td>
                </tr>
            	<%} %>
            </table>
        </article>
        <footer id ="f2">
        </footer>
    <%} %>
    </div>
</body>
</html>