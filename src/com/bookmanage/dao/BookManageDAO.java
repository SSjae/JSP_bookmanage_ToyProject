package com.bookmanage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bookmanage.vo.BooksVO;
import com.bookmanage.vo.LoansReturnsVO;
import com.bookmanage.vo.PurchasesVO;
import com.bookmanage.vo.UsersVO;

public class BookManageDAO {
	private static BookManageDAO dao = new BookManageDAO();
	
	private BookManageDAO() {};
	
	public static BookManageDAO getInstance()
	{
		return dao;
	}
	private Connection connect()
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bookmanage";
			conn = DriverManager.getConnection(url, "root", "1234");
		} catch (Exception e) {
			System.out.println("Conn error!!!");
			e.printStackTrace();
		}
		return conn;
	}
	private void close(Connection conn)
	{
		if(conn != null)
		{
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void close(PreparedStatement pstmt)
	{
		if(pstmt != null)
		{
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void close(ResultSet rs)
	{
		if(rs != null)
		{
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//joinIdCheck
	public boolean joinIdCheck(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select id from users");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("id").equals(id)) {
					return true;
				}
			}
		}
		catch (Exception e) {
			System.out.println("joinIdCheck error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return false;
	}
	
	//join
	public void joinUser(UsersVO user)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into users values (?, ?, ?, ?)");
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhone());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("joinUser error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//login
	public int login(String id, String password)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(id.equals("admin")) {
			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from admin where id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("password").equals(password))
						return 1;
					else
						return 0;
				}
				return -1;
			}
			catch (Exception e) {
				System.out.println("login admin error!!!");
			} finally {
				close(conn);
				close(pstmt);
				close(rs);
			}
			return -2;
		}
		else {
			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from users where id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("password").equals(password))
						return 1;
					else
						return 0;
				}
				return -1;
			}
			catch (Exception e) {
				System.out.println("login user error!!!");
			} finally {
				close(conn);
				close(pstmt);
				close(rs);
			}
			return -2;
		}
	}

	//findId
	public ArrayList<String> findId(String name, String phone)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();

		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from users where name = ? and phone = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				list.add(id);
			}
		}
		catch (Exception e) {
			System.out.println("findId error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//findPwd
	public boolean findPwd(String id, String name, String phone)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from users where id = ? and name = ? and phone = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("findPwd error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return false;
	}

	//passwordReset
	public void passwordReset(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update users set password = ? where id = ?");
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("passwordReset error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//bookList
	public ArrayList<BooksVO> bookList()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BooksVO> list = new ArrayList<BooksVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from books");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BooksVO book = new BooksVO();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setKind(rs.getString("kind"));
				book.setExplains(rs.getString("explains"));
				book.setPrice(rs.getInt("price"));
				list.add(book);
			}
		}
		catch (Exception e) {
			System.out.println("bookList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//bookIsbnCheck
	public boolean bookIsbnCheck(String isbn)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select isbn from books");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("isbn").equals(isbn)) {
					return true;
				}
			}
		}
		catch (Exception e) {
			System.out.println("bookIsbnCheck error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return false;
	}
	
	//isbnBook
	public BooksVO isbnBook(String isbn)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BooksVO isbnBook = new BooksVO();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from books where isbn = ?");
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isbnBook.setIsbn(isbn);
				isbnBook.setTitle(rs.getString("title"));
				isbnBook.setAuthor(rs.getString("author"));
				isbnBook.setPublish(rs.getString("publish"));
				isbnBook.setKind(rs.getString("kind"));
				isbnBook.setExplains(rs.getString("explains"));
				isbnBook.setPrice(rs.getInt("price"));
			}
		}
		catch (Exception e) {
			System.out.println("isbnBook error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return isbnBook;
	}
	
	//bookRegister
	public void bookRegister(BooksVO book)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into books values (?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublish());
			pstmt.setString(5, book.getKind());
			pstmt.setString(6, book.getExplains());
			pstmt.setInt(7, book.getPrice());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("bookRegister error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//bookUpdate
	public void bookUpdate(BooksVO book)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update books set title = ?, author = ?, publish = ?, kind = ?, explains = ?, price = ? where isbn = ?");
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublish());
			pstmt.setString(4, book.getKind());
			pstmt.setString(5, book.getExplains());
			pstmt.setInt(6, book.getPrice());
			pstmt.setString(7, book.getIsbn());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("bookUpdate error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//bookCancle
	public void bookCancle(String isbn)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from books where isbn = ?");
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("bookRegister error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//userAllList
	public ArrayList<UsersVO> userAllList()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UsersVO> list = new ArrayList<UsersVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from users");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UsersVO user = new UsersVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				list.add(user);
			}
		}
		catch (Exception e) {
			System.out.println("userAllList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//allLoanList
	public ArrayList<LoansReturnsVO> allLoanList()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LoansReturnsVO> list = new ArrayList<LoansReturnsVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from loans order by length(l_num)");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LoansReturnsVO loanReturn = new LoansReturnsVO();
				loanReturn.setLoansNum(rs.getString("l_num"));
				loanReturn.setUserId(rs.getString("l_u_id"));
				loanReturn.setBookIsbn(rs.getString("l_b_isbn"));
				loanReturn.setLoansDate(rs.getString("l_date"));
				list.add(loanReturn);
			}
		}
		catch (Exception e) {
			System.out.println("allLoanList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//allReturnList
	public ArrayList<LoansReturnsVO> allReturnList()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LoansReturnsVO> list = new ArrayList<LoansReturnsVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from returns order by length(r_num)");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LoansReturnsVO loanReturn = new LoansReturnsVO();
				loanReturn.setReturnsNum(rs.getString("r_num"));
				loanReturn.setLoansNum(rs.getString("r_l_num"));
				loanReturn.setUserId(rs.getString("r_u_id"));
				loanReturn.setBookIsbn(rs.getString("r_b_isbn"));
				loanReturn.setReturnsDate(rs.getString("r_date"));
				list.add(loanReturn);
			}
		}
		catch (Exception e) {
			System.out.println("allReturnList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//allPurchaseList
	public ArrayList<PurchasesVO> allPurchaseList()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PurchasesVO> list = new ArrayList<PurchasesVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from purchases order by length(p_num)");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PurchasesVO purchase = new PurchasesVO();
				purchase.setPurchasesNum(rs.getString("p_num"));
				purchase.setUserId(rs.getString("p_u_id"));
				purchase.setBookIsbn(rs.getString("p_b_isbn"));
				purchase.setPurchasesDate(rs.getString("p_date"));
				purchase.setMethod(rs.getString("method"));
				purchase.setAddress(rs.getString("address"));
				list.add(purchase);
			}
		}
		catch (Exception e) {
			System.out.println("allPurchaseList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//currentLoanNum
	public String currentLoanNum()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loanNum = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select l_num from loans order by length(l_num) desc, l_num desc limit 1");
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				loanNum = rs.getString("l_num");
			}
		}
		catch (Exception e) {
			System.out.println("currentLoanNum error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return loanNum;
	}
	
	//currentReturnNum
	public String currentReturnNum()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String returnNum = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select r_num from returns order by length(r_num) desc, r_num desc limit 1");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				returnNum = rs.getString("r_num");
			}
		}
		catch (Exception e) {
			System.out.println("currentReturnNum error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return returnNum;
	}

	//countLoan
	public int countLoan(String id, String isbn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select count(*) from loans where l_u_id = ? and l_b_isbn = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}
		catch (Exception e) {
			System.out.println("countLoan error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return count;
	}
	
	//countReturn
	public int countReturn(String id, String isbn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select count(*) from returns where r_u_id = ? and r_b_isbn = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}
		catch (Exception e) {
			System.out.println("countReturn error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return count;
	}

	//loan
	public void loan(LoansReturnsVO loan) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into loans values (?, ?, ?, ?)");
			pstmt.setString(1, loan.getLoansNum());
			pstmt.setString(2, loan.getUserId());
			pstmt.setString(3, loan.getBookIsbn());
			pstmt.setString(4, loan.getLoansDate());
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("loan error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//returnLoanNum
	public String returnLoanNum(String id, String isbn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loanNum = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select l_num from loans where l_u_id = ? and l_b_isbn = ? order by length(l_num) desc, l_num desc limit 1");
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loanNum = rs.getString("l_num");
			}
		}
		catch (Exception e) {
			System.out.println("returnLoanNum error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return loanNum;
	}
	
	//returns
	public void returns(LoansReturnsVO returns) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into returns values (?, ?, ?, ?, ?)");
			pstmt.setString(1, returns.getReturnsNum());
			pstmt.setString(2, returns.getLoansNum());
			pstmt.setString(3, returns.getUserId());
			pstmt.setString(4, returns.getBookIsbn());
			pstmt.setString(5, returns.getReturnsDate());
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("returns error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}
	
	//userLoanList
	public ArrayList<LoansReturnsVO> userLoanList(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LoansReturnsVO> list = new ArrayList<LoansReturnsVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from loans where l_u_id = ? order by length(l_num)");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LoansReturnsVO loanReturn = new LoansReturnsVO();
				loanReturn.setLoansNum(rs.getString("l_num"));
				loanReturn.setUserId(rs.getString("l_u_id"));
				loanReturn.setBookIsbn(rs.getString("l_b_isbn"));
				loanReturn.setLoansDate(rs.getString("l_date"));
				list.add(loanReturn);
			}
		}
		catch (Exception e) {
			System.out.println("userLoanList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//userReturnList
	public ArrayList<LoansReturnsVO> userReturnList(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LoansReturnsVO> list = new ArrayList<LoansReturnsVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from returns where r_u_id = ? order by length(r_num)");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LoansReturnsVO loanReturn = new LoansReturnsVO();
				loanReturn.setReturnsNum(rs.getString("r_num"));
				loanReturn.setLoansNum(rs.getString("r_l_num"));
				loanReturn.setUserId(rs.getString("r_u_id"));
				loanReturn.setBookIsbn(rs.getString("r_b_isbn"));
				loanReturn.setReturnsDate(rs.getString("r_date"));
				list.add(loanReturn);
			}
		}
		catch (Exception e) {
			System.out.println("userReturnList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}

	//currentPurchaseNum
	public String currentPurchaseNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String purchaseNum = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select p_num from purchases order by length(p_num), p_num desc limit 1");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				purchaseNum = rs.getString("p_num");
			}
		}
		catch (Exception e) {
			System.out.println("currentPurchaseNum error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return purchaseNum;
	}

	//purchase
	public void purchase(PurchasesVO purchase) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into purchases values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, purchase.getPurchasesNum());
			pstmt.setString(2, purchase.getUserId());
			pstmt.setString(3, purchase.getBookIsbn());
			pstmt.setString(4, purchase.getPurchasesDate());
			pstmt.setString(5, purchase.getMethod());
			pstmt.setString(6, purchase.getAddress());
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("purchase error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}

	//userPurchaseList
	public ArrayList<PurchasesVO> userPurchaseList(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PurchasesVO> list = new ArrayList<PurchasesVO>();
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from purchases where p_u_id = ? order by length(p_num)");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PurchasesVO purchase = new PurchasesVO();
				purchase.setPurchasesNum(rs.getString("p_num"));
				purchase.setUserId(rs.getString("p_u_id"));
				purchase.setBookIsbn(rs.getString("p_b_isbn"));
				purchase.setPurchasesDate(rs.getString("p_date"));
				purchase.setMethod(rs.getString("method"));
				purchase.setAddress(rs.getString("address"));
				list.add(purchase);
			}
		}
		catch (Exception e) {
			System.out.println("userPurchaseList error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//bookPurchaseCancle
	public void bookPurchaseCancle(String purchaseNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from purchases where p_num = ?");
			pstmt.setString(1, purchaseNum);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("bookPurchaseCancle error!!!");
		} finally {
			close(conn);
			close(pstmt);
		}
	}

	//seachBook
	public ArrayList<BooksVO> searchBook(String searchKind, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BooksVO> list = new ArrayList<BooksVO>();
		
		try {
			conn = connect();
			if(searchKind.equals("title"))
				pstmt = conn.prepareStatement("select * from books where title like concat ('%',?,'%') ");
			else if(searchKind.equals("author"))
				pstmt = conn.prepareStatement("select * from books where author like concat ('%',?,'%') ");
			else
				pstmt = conn.prepareStatement("select * from books where publish like concat ('%',?,'%') ");
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BooksVO book = new BooksVO();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setKind(rs.getString("kind"));
				book.setExplains(rs.getString("explains"));
				book.setPrice(rs.getInt("price"));
				list.add(book);

				System.out.println(book.getTitle());
			}
		}
		catch (Exception e) {
			System.out.println("seachBook error!!!");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return list;
	}
}