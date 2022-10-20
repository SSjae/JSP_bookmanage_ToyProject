<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
  <div id="all">
    <header>
      <div class="header1">
        <div class="head">
          <a href="login.jsp" id="title">도서관리</a>
        </div>
      </div>
      <div class="login">
            <a href="login.jsp" id="he"><h1>LOGIN</h1></a>
            <form action="login.do" method="post">
                <input class="in1" type="text" name="id" id="id" placeholder="ID" autofocus required>
                <input class="in2" type="password" name="password" id="pwd" placeholder="PASSWORD" required>
                <input class="in3" type="submit" value="로그인" id="button">
            </form>
            <a href="join.jsp" id="join">회원가입</a>
            <a href="findId.jsp" id="join">아이디 찾기</a>
            <a href="findPwd.jsp" id="join">비밀번호 찾기</a>
        </div>
    </header>
  </div>
</body>
</html>