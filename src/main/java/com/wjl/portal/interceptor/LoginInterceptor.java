package com.wjl.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wjl.common.util.CookieUtils;
import com.wjl.pojo.TbUser;
import com.wjl.portal.service.InterceptorService;

/**
 * 拦截未登录用户的拦截器
 * @author wujiale
 * 2017-11-02 下午4:27:42
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Value("${ssoAddrUrl}")
	private String SSOADDRURL;
	
	@Autowired
	private InterceptorService interceptorService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在Handler执行之前处理
		//判断用户是否登录
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		//根据token换取用户信息，调用sso系统的接口。
		TbUser user = interceptorService.getUserByToken(token);
		//取不到用户信息
		if (null == user) {
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect(SSOADDRURL+"/page/login?redirect="+request.getRequestURL());
			//返回false
			return false;
		}
		//取到用户信息，放行
		request.setAttribute("user", user);
		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
