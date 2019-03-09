package top.vist.bookborrow.entity;

public class ReaderType {
	private Integer id;
	private String typeName;
	private Integer maxBorrowNum;
	private Integer limit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getMaxBorrowNum() {
		return maxBorrowNum;
	}
	public void setMaxBorrowNum(Integer maxBorrowNum) {
		this.maxBorrowNum = maxBorrowNum;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
}
