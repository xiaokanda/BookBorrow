package top.vist.bookborrow.entity;

import java.util.Date;

public class Book {
	private String ISBN;
	private Integer typeId;
	private String bookName;
	private String author;
	private String publish;
	private String strPublishDate;
	private Date PublishDate;
	private Integer publishNum;
	private Float unitprice;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
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

	public Integer getPublishNum() {
		return publishNum;
	}

	public void setPublishNum(Integer publishNum) {
		this.publishNum = publishNum;
	}

	public String getStrPublishDate() {
		return strPublishDate;
	}

	public void setStrPublishDate(String strPublishDate) {
		this.strPublishDate = strPublishDate;
	}

	public Date getPublishDate() {
		return PublishDate;
	}

	public void setPublishDate(Date publishDate) {
		PublishDate = publishDate;
	}

	public Float getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Float unitprice) {
		this.unitprice = unitprice;
	}

}
