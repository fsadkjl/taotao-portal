package com.wjl.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjl.pojo.TbUser;
import com.wjl.portal.pojo.ItemForCart;
import com.wjl.portal.pojo.Order;
import com.wjl.portal.service.CartService;
import com.wjl.portal.service.OrderService;

/**
 * 用户点击生成订单用的Controller
 * @author wujiale
 * 2017-11-04 上午1:14:01
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 展示点击购物车中去结算按钮的页面
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,HttpServletResponse response,Model model){
		List<ItemForCart> list = cartService.getCartItemList(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	/**
	 * 用户点击创建订单
	 */
	@RequestMapping("/create")
	public String createOrder(Order order,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			TbUser user = (TbUser) request.getAttribute("user");
			order.setBuyerNick(user.getUsername());
			order.setUserId(user.getId());
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			cartService.deleteCookie(request, response);
			return "success";
		} catch (Exception e) {
			model.addAttribute("message", "抱歉！创建订单出错！");
			return "error/exception";
		}
	}
}
