<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/findIdResult.css">
</head>
<body>
<%
	ArrayList<String> idList = (ArrayList<String>) application.getAttribute("idList");
	application.removeAttribute("idList");
%>
  <div id="all">
    <header>
      <div class="header1">
        <div class="head">
          <a href="login.jsp" id="title">도서관리</a>
        </div>
      </div>
      <div class="login">
            <a href="login.jsp" id="he"><h1>아이디 찾기</h1></a>
            <form action="login.jsp" method="post">
            <%
            	for(int i = 0; i < idList.size(); i++) {
            		String id = idList.get(i); %>
            	<div id="idList"><h4><%=id %><h4></div>
            <% } %>
                <div class="in3"><input type="submit" value="로그인" id="button"></div>
            </form>
            <a href="join.jsp" id="join">회원가입</a>
            <a href="findPwd.jsp" id="join">비밀번호 찾기</a>
        </div>
    </header>
  </div>
</body>
</html>