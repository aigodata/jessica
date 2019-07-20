package com.aigodata.jessica.basic.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "offset", "limit", "totalList" })
public class Pageable<T> {

	/*
	 * 偏移量, 默认0
	 */
	protected long offset = 0;

	/*
	 * 限制, 每页大小, 默认20
	 */
	protected long limit = 20;

	/*
	 * 起始位置
	 */
	protected long start = 0;

	/*
	 * 结束位置
	 */
	protected long end = 0;

	/*
	 * 总记录数
	 */
	private long total;

	/*
	 * 总结果集
	 */
	private List<T> totalList;

	/*
	 * 分页结果集
	 */
	private List<T> data;

	public Pageable() {
	}

	public Pageable(List<T> totalList) {
		this.offset = 0;
		this.start = 0;
		this.end = totalList.size();
		this.totalList = totalList;
		this.total = totalList == null ? 0 : totalList.size();
	}

	public Pageable(long start, long end, long total, List<T> data) {
		this.start = start;
		this.offset = start;
		this.end = end;
		this.limit = end - start;
		this.total = total;
		this.data = data;
	}

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

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getTotalList() {
		return totalList;
	}

	public void setTotalList(List<T> totalList) {
		this.totalList = totalList;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<T> page(long start, long end) {
		if (start < 0 || start > total) {
			return Collections.emptyList();
		}
		if (end > total) {
			end = total;
		}
		return totalList.subList((int) start, (int) end);
	}

}
