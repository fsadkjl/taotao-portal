package com.wjl.portal.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.portal.service.ContentService;

@Controller
public class IndexController {
	@Value("${ssoUrl}")
	private String SSOURL;
	@Value("${ssoAddrUrl}")
	private String SSOADDRURL;
	
	@Autowired
	private ContentService contentService;
	/**
	 * 加载首页的同时向taotao-rest请求服务，查询大广告列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	/**
	 * 首页点击退出的时候的Controller
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpServletRequest request){
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
//System.out.println("cookies:"+cookie.getName());
//System.out.println("cookies:"+cookie.getPath());
			if (cookie.getName().equals("TT_TOKEN")) {
				token = cookie.getValue();
			}
		}
		token = token.replace("\"", "").trim();
		String string = HttpClientUtil.doGet(SSOURL+"/user/logout/"+token);
//System.out.println("portal:"+string);
		TaotaoResult taotaoResult = TaotaoResult.format(string);
		if (taotaoResult.getStatus() == 200) {
			//退出后自动跳转到登录页面
			return "redirect:"+SSOADDRURL+"/page/login";
		}
		return null;
	}
	
//	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST, 
//			produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String testPost(String username, String password) {
//		String result = "username:" + username + "\tpassword:" + password;
//		System.out.println(result);
//		return "username:" + username + ",password:" + password;
//	}
}
