package com.wjl.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wjl.portal.pojo.ItemForCart;
import com.wjl.portal.service.CartService;

/**
 * 购物车的Controller
 * @author wujiale
 * 2017-11-02 下午11:24:53
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	@Value("${portalUrl}")
	private String PORTALURL;
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 购物车添加商品Service
	 */
	@RequestMapping("/add/{id}")
	public String addItemToCart(@RequestParam(defaultValue = "1")Integer num,@PathVariable Long id,HttpServletRequest request,HttpServletResponse response){
		cartService.addItemToCart(num, id, request, response);
		//这里不直接返回/cart/add页面是因为 如果用户刷新页面会增加购物车数量
		//所以直接重定向吧 
		//redirect:http://localhost:8082/cart/cartSuccess.html
		return "redirect:"+PORTALURL+"/cartSuccess.html";
	}
	
	@RequestMapping("/cartSuccess")
	public String success(){
		return "cartSuccess";
	}
	
	/**
	 * 购物车显示商品信息Service
	 */
	@RequestMapping("/cart")
	public String showcart(HttpServletRequest request,Model model){
		List<ItemForCart> list = cartService.getCartItemList(request);
		model.addAttribute("cartList", list);
		return "cart";
	}
	
	/**
	 * 购物车修改商品数量Service
	 */
	@RequestMapping(value="/update/num/{id}/{num}",method=RequestMethod.POST)
	public String updateCartNum(@PathVariable Long id,@PathVariable Integer num,HttpServletRequest request,HttpServletResponse response){
		cartService.updateCartNum(id,num,request,response);
		return "cart";
	}
	
	/**
	 * 购物车删除商品Service
	 */
	@RequestMapping("/delete/{id}")
	public String deleteItemFromCart(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response){
		cartService.deleteItemFromCart(id,request,response);
		return "redirect:"+PORTALURL+"/cart.html";
	}
	
}
