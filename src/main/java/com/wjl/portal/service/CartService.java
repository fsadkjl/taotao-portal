package com.wjl.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.portal.pojo.ItemForCart;

public interface CartService {
	TaotaoResult addItemToCart(Integer num,Long id,HttpServletRequest request ,HttpServletResponse response);
	List<ItemForCart> getCartItemList(HttpServletRequest request);
	TaotaoResult updateCartNum(Long id,Integer num,HttpServletRequest request,HttpServletResponse response);
	TaotaoResult deleteItemFromCart(Long id,HttpServletRequest request,HttpServletResponse response);
	TaotaoResult deleteCookie(HttpServletRequest request,HttpServletResponse response);
}
