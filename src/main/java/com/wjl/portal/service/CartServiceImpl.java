package com.wjl.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.CookieUtils;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.common.util.JsonUtils;
import com.wjl.pojo.TbItem;
import com.wjl.portal.pojo.ItemForCart;
/**
 * 购物车Service
 * @author wujiale
 * 2017-11-02 下午10:06:46
 */
@Service
public class CartServiceImpl implements CartService{
	@Value("${COOKIENAME}")
	private String COOKIENAME;
	@Value("${restUrl}")
	private String RESTURL;

	/**
	 * 购物车添加商品Service
	 */
	@Override
	public TaotaoResult addItemToCart(Integer num,Long id,HttpServletRequest request,HttpServletResponse response) {
		ItemForCart itemForCart = null;
		//先查询购物车中有没有这个商品 有的话数量+1 没有的话 写到cookie中
		List<ItemForCart> list = getItemFromCart(request);
		for (ItemForCart item : list) {
			//如果有就加1
			if (item.getId().equals(id)) {
//System.out.println(item.getNum());
				item.setNum(item.getNum()+num);
//System.out.println(item.getNum());
				itemForCart = item;
			}
		}
		if (itemForCart == null) {
			itemForCart = new ItemForCart();
			TbItem tbItem = null;
			//根据id查询商品信息
			String json = HttpClientUtil.doGet(RESTURL+"/rest/itemInfo/"+id);
			if (StringUtils.isNotBlank(json)) {
				TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
				if (result != null && result.getStatus() == 200) {
					tbItem = (TbItem) result.getData();
				}
			}
			itemForCart.setId(id);
			itemForCart.setTitle(tbItem.getTitle());
			itemForCart.setNum(num);
			itemForCart.setPrice(tbItem.getPrice());
			String image = tbItem.getImage();
			if (StringUtils.isNotBlank(image) && image.contains(",")) {
				itemForCart.setImage(image.substring(0, image.indexOf(",")));
			}else{
				itemForCart.setImage(image);
			}
			list.add(itemForCart);
		}
		CookieUtils.setCookie(request, response, COOKIENAME, JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

	/**
	 * 从cookie中取得商品的Service
	 */
	private List<ItemForCart> getItemFromCart(HttpServletRequest request) {
		String string = CookieUtils.getCookieValue(request, COOKIENAME, true);
		if (StringUtils.isBlank(string)) {
			return new ArrayList<ItemForCart>();
		}
		try {
			List<ItemForCart> list = JsonUtils.jsonToList(string, ItemForCart.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ItemForCart>();

	}

	/**
	 * 购物车显示商品信息Service
	 */
	@Override
	public List<ItemForCart> getCartItemList(HttpServletRequest request) {
		List<ItemForCart> list = getItemFromCart(request);
		return list;
	}

	/**
	 * 购物车修改商品数量Service
	 */
	@Override
	public TaotaoResult updateCartNum(Long id, Integer num, HttpServletRequest request,HttpServletResponse response) {
		//先查询购物车中有没有这个商品 有的话数量+1 没有的话 写到cookie中
		List<ItemForCart> list = getItemFromCart(request);
		for (ItemForCart item : list) {
			//如果有就加1
			if (item.getId().equals(id)) {
				item.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, COOKIENAME, JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

	/**
	 * 购物车删除单个商品Service
	 */
	@Override
	public TaotaoResult deleteItemFromCart(Long id, HttpServletRequest request, HttpServletResponse response) {
		List<ItemForCart> list = getItemFromCart(request);
		for (ItemForCart item : list) {
			if (item.getId().equals(id)) {
				list.remove(item);
				break;
			}
		}
		CookieUtils.setCookie(request, response, COOKIENAME, JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

	/**
	 * 清空购物车
	 */
	@Override
	public TaotaoResult deleteCookie(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, COOKIENAME);
		return null;
	}

}
