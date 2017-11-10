package com.wjl.portal.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.pojo.TbUser;
/**
 * 拦截未登录用户的拦截器
 * @author wujiale
 * 2017-11-02 下午4:27:42
 */
@Service
public class InterceptorServiceImpl implements InterceptorService{
	@Value("${ssoUrl}")
	private String SSOURL;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//调用sso系统的服务，根据token取用户信息
			if (StringUtils.isNotBlank(token)) {
				token = token.replace("\"", "").trim();
			}
			String json = HttpClientUtil.doGet(SSOURL+"/user/token/"+ token);
			//把json转换成TaotaoREsult
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
