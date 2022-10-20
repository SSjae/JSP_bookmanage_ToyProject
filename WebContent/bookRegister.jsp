<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/bookRegister.css">
</head>
<body>
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
    <div class="subtitle">
        <a href="bookRegister.jsp"><h1>책 등록</h1></a>
    </div>
    <article>
        <form action="bookRegister.do" method="post">
            <table>
                <tr>
                    <th width=150px height=80px>ISBN</th>
                    <td width=300px><input type="text" name="isbn" placeholder="isbn" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>제목</th>
                    <td width=300px><input type="text" name="title" placeholder="title" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>저자</th>
                    <td width=300px><input type="text" name="author" placeholder="author" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>출판사</th>
                    <td width=300px><input type="text" name="publish" placeholder="publish" autofocus required></td>
                </tr>
                <tr>
                    <th width=150px height=80px>종류</th>
                    <td width=300px>
                        <select name="kind">
                            <option value="소설">소설</option>
                            <option value="수필">수필</option>
                            <option value="시">시</option>
                            <option value="사회정치">사회정치</option>
                            <option value="자연과학">자연과학</option>
                            <option value="인문">인문</option>
                            <option value="예술">예술</option>
                            <option value="종교">종교</option>
                            <option value="참고서">참고서</option>
                            <option value="역사">역사</option>
                            <option value="유아">유아</option>
                            <option value="여행">여행</option>
                            <option value="기타">기타</option>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <th width=150px height=80px>책 설명</th>
                    <td width=300px height=400px><textarea id="explain" cols=28px rows = 20px name="explains" placeholder="explains" style="font-family:맑은 고딕" autofocus required></textarea></td>
                </tr>
                <tr>
                    <th width=150px height=80px>가격</th>
                    <td width=300px><input type="text" name="price" placeholder="price" autofocus required></td>
                </tr>
            </table>
            <div class="button1">
                <input type="submit" id="button2" value="등록">
            </div>
        </form>
    </article>
    <footer id ="f2">
    </footer>
  </div>
</body>
</html>