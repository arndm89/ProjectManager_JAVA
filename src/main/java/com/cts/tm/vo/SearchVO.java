package com.cts.tm.vo;

import java.util.List;

public class SearchVO {
	
	Integer searchCount;
	List<SearchTaskVO> searchList;
	
	public SearchVO(){}
	
	public SearchVO(Integer i, List<SearchTaskVO> searchList) {
		this.searchCount = i;
		this.searchList = searchList;
	}
	public Integer getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	public List<SearchTaskVO> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<SearchTaskVO> searchList) {
		this.searchList = searchList;
	}
}