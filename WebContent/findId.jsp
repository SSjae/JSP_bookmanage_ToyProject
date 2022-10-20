<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/findId.css">
<script>
    function check(obj) {
        if(obj.phone.value.trim().length != 11) {
            alert("핸드폰 번호 11자리에 맞춰서 입력해주세요.");
            obj.phone.value = "";
            obj.phone.focus();
            return false;
        }
    }
</script>
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
            <a href="findId.jsp" id="he"><h1>아이디 찾기</h1></a>
            <form action="findId.do" method="post" onsubmit="return check(this)">
                <div><h4>이름</h4><input type="text" name="name" id="name" placeholder="Name" autofocus required></div>
                <div class="in2"><h4>전화번호</h4><input type="text" name="phone" id="phone" placeholder="Phone" required></div>
                <div class="in3"><input type="submit" value="확인" id="button"></div>
            </form>
            <a href="join.jsp" id="join">회원가입</a>
            <a href="findPwd.jsp" id="join">비밀번호 찾기</a>
        </div>
    </header>
  </div>
</body>
</html>