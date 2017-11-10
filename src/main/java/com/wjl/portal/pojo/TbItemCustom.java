package com.wjl.portal.pojo;

import com.wjl.pojo.TbItem;
/**
 * 在商品详情页面使用 因为一个商品会有多个图片 所以扩展一下Item
 * @author wujiale
 * 2017-11-02 下午9:59:00
 */
public class TbItemCustom extends TbItem{
	
	public String[] getImages(){
		String image = this.getImage();
		if (image != null) {
			String[] strings = image.split(",");
			return strings;
		}
		return null;
	}
}
