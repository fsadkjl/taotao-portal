package com.wjl.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wjl.portal.pojo.SolrQueryResult;
import com.wjl.portal.service.SearchService;
/**
 * 商品搜索controller
 * @author wujiale
 * 2017-10-30 下午4:49:02
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String searchKeyWord(@RequestParam("q")String keyword, @RequestParam(defaultValue="1")Integer page,Model model){
		if (keyword != null) {
			try {
				keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SolrQueryResult result = searchService.search(keyword, page);
		//向页面传递参数
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("page", page);
		return "search";
	}
}
