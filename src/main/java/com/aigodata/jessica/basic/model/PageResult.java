package com.aigodata.jessica.basic.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果封装
 * 
 * @author mengxiangyun
 *
 * @param <T>
 */
public class PageResult<T> extends Page implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * 总记录数
	 */
	private long total;

	/*
	 * 结果集
	 */
	private List<T> list;

	public PageResult() {
	}

	public PageResult(List<T> list) {
		this.list = list;
		this.total = list == null ? 0 : list.size();
	}

	public PageResult(long offset, long limit, long total, List<T> list) {
		this.offset = offset;
		this.limit = limit;
		this.total = total;
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> page(long offset, long limit) {
		if (offset < 0 || offset > total) {
			return Collections.emptyList();
		}
		long end = offset + limit;
		if (end > total) {
			end = total;
		}
		return list.subList((int) offset, (int) end);
	}

}
