<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bookmanage.service.BookManageService" import="java.util.ArrayList"
    import="com.bookmanage.vo.LoansReturnsVO" import="com.bookmanage.vo.BooksVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookManage</title>
<link rel="stylesheet" href="css/adminLoanReturn.css">
</head>
<body>
  <% String id = (String) application.getAttribute("id");
  	 BookManageService service = BookManageService.getInstance();
  	 ArrayList<LoansReturnsVO> allLoanList = service.allLoanList();
  	 ArrayList<LoansReturnsVO> allReturnList = service.allReturnList();
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
    <% if (allLoanList.isEmpty()) { %>
        <article>
           <table>
               <tr>
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>아이디</th><th width=130px>대출날짜</th><th width="130px">반납날짜</th><th width="100px"></th>
               </tr>
           </table>
           <h3>등록된 대출 / 반납이 없습니다.</h3>
        </article>
        <footer id="f1"></footer>
	<%} else {%>
        <article>
            <table>
                <tr>
                 <th width=50px>번호</th><th width=100px>ISBN</th><th width=390px>제목</th><th width=180px>아이디</th><th width=130px>대출날짜</th><th width="130px">반납날짜</th><th width="100px"></th>
               	</tr>
                <%
                ArrayList<String> rnList = new ArrayList<String>();
                for(int k = 0; k < allReturnList.size(); k++) { 
                	LoansReturnsVO returns = allReturnList.get(k);
                	rnList.add(returns.getLoansNum());
                }
        		LoansReturnsVO loanReturn = null;
                int number = 0;
                BooksVO isbnBook = null;
            	for(int i = 0; i < allLoanList.size(); i++) { 
            		LoansReturnsVO loan = allLoanList.get(i);
            	  	isbnBook = service.isbnBook(loan.getBookIsbn());
            		number = i + 1;
            		if(rnList.contains(loan.getLoansNum())) {
            			for(int j = 0; j < allReturnList.size(); j++) {
                    		LoansReturnsVO returns = allReturnList.get(j);
                    		if(loan.getLoansNum().equals(returns.getLoansNum())) {
                    			loanReturn = loan;
                    			loanReturn.setReturnsNum(returns.getReturnsNum());
                    			loanReturn.setReturnsDate(returns.getReturnsDate());
                    			%>
    	                			<form action="adminBookDetail.jsp" method="post">
    				                <tr>
    				                    <td width=50px><%=number %></td>
    				                    <td width=100px><%=loanReturn.getBookIsbn() %></td>
    			                        <input type="hidden" name="isbn" value="<%=loanReturn.getBookIsbn() %>">
    				                    <td width=390px><%=isbnBook.getTitle() %></td>
    				                    <td width=180px><%=loanReturn.getUserId() %></td>
    				                    <td width=130px><%=loanReturn.getLoansDate() %></td>
    				                    <td width=130px><%=loanReturn.getReturnsDate() %></td>
    			                        <td width=100px><input type="submit" id="button2" value="책 정보"></td>
    				                </tr>
    			            	</form>
                    			<%
                    			break;
                    		}
                		}
            		}
            		else {
            			loanReturn = loan;
	            		loanReturn.setReturnsDate("");
	            		%>
	            		<form action="adminBookDetail.jsp" method="post">
			               	<tr>
			                    <td width=50px><%=number %></td>
			                    <td width=100px><%=loanReturn.getBookIsbn() %></td>
		                        <input type="hidden" name="isbn" value="<%=loanReturn.getBookIsbn() %>">
			                    <td width=390px><%=isbnBook.getTitle() %></td>
			                    <td width=180px><%=loanReturn.getUserId() %></td>
			                    <td width=130px><%=loanReturn.getLoansDate() %></td>
			                    <td width=130px><%=loanReturn.getReturnsDate() %></td>
		                        <td width=100px><input type="submit" id="button2" value="책 정보"></td>
			                </tr>
	           			</form>
	            		<% 
            		}
            	}
            	%>
            </table>
        </article>
        <footer id ="f2">
        </footer>
    <%} %>
    </div>
</body>
</html>