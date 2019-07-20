package com.aigodata.jessica.basic.model;

/**
 * 分页信息
 * 
 * @author mengxiangyun
 *
 */
public class Page {

	/*
	 * 偏移量, 默认0
	 */
	protected long offset = 0;

	/*
	 * 限制, 每页大小, 默认20
	 */
	protected long limit = 20;

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

}
