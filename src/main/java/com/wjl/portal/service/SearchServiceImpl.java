package com.wjl.portal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.portal.pojo.SolrQueryResult;
@Service
public class SearchServiceImpl implements SearchService{
	//baseURL=http://192.168.203.128:8080/solr
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SolrQueryResult search(String keyword, Integer page) {
		Map<String, String> param = new HashMap<String,String>();
		param.put("q", keyword);
		param.put("page", page+"");
		String string = HttpClientUtil.doGet(SEARCH_BASE_URL, param );
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(string, SolrQueryResult.class);
		if (taotaoResult.getStatus() == 200) {
			return (SolrQueryResult) taotaoResult.getData();
		}
		return null;
	}

}
