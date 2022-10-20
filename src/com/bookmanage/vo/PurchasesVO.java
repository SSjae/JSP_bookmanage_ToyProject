package com.bookmanage.vo;

public class PurchasesVO {
	private String purchasesNum;
	private String userId;
	private String bookIsbn;
	private String purchasesDate;
	private String method;
	private String address;
	
	
	public String getPurchasesNum() {
		return purchasesNum;
	}
	public void setPurchasesNum(String purchasesNum) {
		this.purchasesNum = purchasesNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getPurchasesDate() {
		return purchasesDate;
	}
	public void setPurchasesDate(String purchasesDate) {
		this.purchasesDate = purchasesDate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
