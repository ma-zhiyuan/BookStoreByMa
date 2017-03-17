package org.lanqiao.base.bean;

public class BookCretiera {
	private String keyword;
	private double minPrice;
	private double maxPrice;
	private int pageNo;
	private int pageSize=16;
	private int orderBy;
	private int maxPageNo;
	private int count;
	
	public boolean hasNext(){
		if(pageNo==maxPageNo){
			return false;
		}else{
			return true;
		}
	}
	 
	public boolean hasPre(){
		if(pageNo==1){
			return false;
		}else{
			return true;
		}
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		if(count%pageSize==0){
			maxPageNo = count/pageSize;
		}else{
			maxPageNo = count/pageSize+1;
		}
	}

	public int getMaxPageNo() {
		return maxPageNo;
	}

	public void setMaxPageNo(int maxPageNo) {
		this.maxPageNo = maxPageNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	
	public BookCretiera() {
		super();
	}

	public BookCretiera(String keyword, double minPrice, double maxPrice, int pageNo, int pageSize, int orderBy) {
		super();
		this.keyword = keyword;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "BookCretiera [keyword=" + keyword + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", maxPageNo=" + maxPageNo + ", count="
				+ count + "]";
	}

}
