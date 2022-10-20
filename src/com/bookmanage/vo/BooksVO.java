package com.bookmanage.vo;

public class BooksVO {
	private String isbn;
	private String title;
	private String author;
	private String publish;
	private String kind;
	private String explains;
	private int price;
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getKind() {
		return kind;
	}
	public void setKind (String kind) {
		this.kind = kind;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}