package com.aigodata.jessica.basic.model;

/**
 * 排序信息
 * 
 * @author mengxiangyun
 *
 */
public class Sort {

	/*
	 * 排序字段
	 */
	private String sort;

	/*
	 * 排序方向 asc/desc
	 */
	private String order;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
