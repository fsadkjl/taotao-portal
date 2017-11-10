package com.wjl.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.portal.pojo.TbItemCustom;
import com.wjl.portal.service.ItemInfoService;
/**
 * 点击图片获取商品相关信息的@Controller
 * @author wujiale
 * 2017-10-30 下午10:45:59
 */
@Controller
public class ItemInfoController {
	@Autowired
	private ItemInfoService itemInfoService;
	
	@RequestMapping("/item/{id}")
	public String getItemInfo(@PathVariable Long id,Model model){
		TbItemCustom item = itemInfoService.getItemInfo(id);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{id}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long id){
		return itemInfoService.getItemDesc(id);
	}
	
	@RequestMapping(value="/item/param/{id}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
//	@RequestMapping("/item/param/{id}")
	@ResponseBody
	public String getItemParamData(@PathVariable Long id){
		return itemInfoService.getItemParamData(id);
	}
}
