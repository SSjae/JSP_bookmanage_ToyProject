<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/join.css">
<script>
    function joinCheck(obj) {
        if(obj.id.value.trim().length < 4) {
            alert("아이디 4글자 이상으로 입력해주세요");
            obj.id.value = "";
            obj.id.focus();
            return false;
        }
        if(obj.password.value.trim().length < 4) {
            alert("비밀번호 4글자 이상으로 입력해주세요");
            obj.password.value = "";
            obj.password.focus();
            return false;
        }
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
      <div>
      <div class="join">
        <a href="join.jsp" id="he"><h1>JOIN</h1></a>
        <form action="join.do" method="post" onsubmit="return joinCheck(this)">
          <label for="id">아이디<em>*</em><span>  (영문 대소문자/숫자 4자~16자)</span></label>
          <input type="text" name="id" minlength="4" maxlength="16"placeholder="아이디 입력" autofocus required>
    
          <label for="pwd">비밀번호<em>*</em><span>  (영문 대소문자/숫자 4자~16자)</span></label>
          <input type="password" name="password" minlength="4" maxlength="16" placeholder="비밀번호 입력" required>
    
          <label for="name">이름</label><em>*</em>
          <input type="text" name="name" placeholder="이름 입력" required>
    
          <label for="number">전화번호</label><em>*</em>
          <input type="text" name="phone" placeholder="전화번호 입력(-제외)" required>
    
          <input type="submit" value="회원가입" id="button">
        </form>
        </div>
    </header>
  </div>
</body>
</html>