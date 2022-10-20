package com.bookmanage.service;

import java.util.ArrayList;

import com.bookmanage.dao.BookManageDAO;
import com.bookmanage.vo.BooksVO;
import com.bookmanage.vo.LoansReturnsVO;
import com.bookmanage.vo.PurchasesVO;
import com.bookmanage.vo.UsersVO;

public class BookManageService {
	private static BookManageService service = new BookManageService();
	private BookManageDAO dao = BookManageDAO.getInstance();
	
	private BookManageService() {}
	
	public static BookManageService getInstance()
	{
		return service;
	}

	public boolean joinIdCheck(String id) {
		return dao.joinIdCheck(id);
	}
	
	public void joinUser(UsersVO user) {
		dao.joinUser(user);
	}
	
	public int login(String id, String password) {
		return dao.login(id, password);
	}
	
	public ArrayList<String> findId(String name, String phone) {
		return dao.findId(name, phone);
	}

	public boolean findPwd(String id, String name, String phone) {
		return dao.findPwd(id, name, phone);
	}
	
	public void passwordReset(String id, String password) {
		dao.passwordReset(id, password);
	}
	
	public ArrayList<BooksVO> bookList() {
		return dao.bookList();
	}

	public BooksVO isbnBook(String isbn) {
		return dao.isbnBook(isbn);
	}
	
	public boolean bookIsbnCheck(String isbn) {
		return dao.bookIsbnCheck(isbn);
	}
	
	public void bookRegister(BooksVO book) {
		dao.bookRegister(book);
	}
	
	public void bookUpdate(BooksVO book) {
		dao.bookUpdate(book);
	}
	
	public void bookCancle(String isbn) {
		dao.bookCancle(isbn);
	}
	
	public ArrayList<UsersVO> userAllList() {
		return dao.userAllList();
	}
	
	public ArrayList<LoansReturnsVO> allLoanList() {
		return dao.allLoanList();
	}
	
	public ArrayList<LoansReturnsVO> allReturnList() {
		return dao.allReturnList();
	}
	
	public ArrayList<PurchasesVO> allPurchaseList() {
		return dao.allPurchaseList();
	}
	
	public String currentLoanNum() {
		return dao.currentLoanNum();
	}
	
	public String currentReturnNum() {
		return dao.currentReturnNum();
	}

	public int countLoan(String id, String isbn) {
		return dao.countLoan(id, isbn);
	}
	
	public int countReturn(String id, String isbn) {
		return dao.countReturn(id, isbn);
	}

	public void loan(LoansReturnsVO loan) {
		dao.loan(loan);
	}

	public String returnLoanNum(String id, String isbn) {
		return dao.returnLoanNum(id, isbn);
	}

	public void returns(LoansReturnsVO returns) {
		dao.returns(returns);
	}
	
	public ArrayList<LoansReturnsVO> userLoanList(String id) {
		return dao.userLoanList(id);
	}
	
	public ArrayList<LoansReturnsVO> userReturnList(String id) {
		return dao.userReturnList(id);
	}
	
	public String currentPurchaseNum() {
		return dao.currentPurchaseNum();
	}

	public void purchase(PurchasesVO purchase) {
		dao.purchase(purchase);
	}
	
	public ArrayList<PurchasesVO> userPurchaseList(String id) {
		return dao.userPurchaseList(id);
	}

	public void bookPurchaseCancle(String purchaseNum) {
		dao.bookPurchaseCancle(purchaseNum);
	}

	public ArrayList<BooksVO> searchBook(String searchKind, String search) {
		return dao.searchBook(searchKind, search);
	}
}
