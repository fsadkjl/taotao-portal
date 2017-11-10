package com.wjl.portal.pojo;

import java.util.List;

/**
 * 首页搜索框查询结果返回
 * 
 * @author wujiale 2017-10-30 上午10:26:42
 */
public class SolrQueryResult {
	// 商品列表
	private List<SolrField> itemList;
	// 总记录数
	private long recordCount;
	// 总页数
	private long pageCount;
	// 当前页
	private long currentPage;

	public SolrQueryResult() {
		// TODO Auto-generated constructor stub
	}

	public SolrQueryResult(List<SolrField> itemList, long recordCount, long pageCount, long currentPage) {
		super();
		this.itemList = itemList;
		this.recordCount = recordCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
	}

	public List<SolrField> getItemList() {
		return itemList;
	}

	public void setItemList(List<SolrField> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

}
