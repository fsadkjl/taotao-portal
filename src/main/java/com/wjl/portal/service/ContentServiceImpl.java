package com.wjl.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.common.util.JsonUtils;
import com.wjl.pojo.TbContent;
/**
 * 向taotao-rest请求服务，查询大广告列表
 * @author wujiale
 * 2017-11-09 下午8:42:07
 */
@Service
public class ContentServiceImpl implements ContentService{
	
	@Value("${baseUrl}")
	private String baseUrl;
	@Value("${adUrl}")
	private String adUrl;
	
	@Override
	public String getContentList() {
		//使用httpClient中的get方法调用taotao-rest中的服务
		String string = HttpClientUtil.doGet(baseUrl + adUrl);
//		String string = HttpClientUtil.doGet("http://localhost:8081/rest/content/list/89");
		try {
			TaotaoResult taotaoResult = TaotaoResult.formatToList(string, TbContent.class);
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			List<Map> resultList = new ArrayList<Map>();
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
