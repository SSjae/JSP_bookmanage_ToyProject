<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/findPwdResult.css">
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
            <a href="findPwdResult.jsp" id="he"><h1>비밀번호 재설정</h1></a>
            <form action="findPwdResult.do" method="post">
                <div><h4>비밀번호</h4><input type="password" name="password1" placeholder="Password" autofocus required></div>
                <div class="in2"><h4>비밀번호 확인</h4><input type="password" name="password2" placeholder="Password" autofocus required></div>
                <div class="in3"><input type="submit" value="확인" id="button"></div>
            </form>
            <a href="join.jsp" id="join">회원가입</a>
            <a href="findId.jsp" id="join">아이디 찾기</a>
        </div>
    </header>
  </div>
</body>
</html>