package com.wjl.portal.service;

import com.wjl.portal.pojo.SolrQueryResult;

public interface SearchService {
	SolrQueryResult search(String keyword,Integer page);
}
