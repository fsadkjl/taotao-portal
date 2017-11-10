package com.wjl.portal.service;

import com.wjl.pojo.TbUser;

public interface InterceptorService {
	TbUser getUserByToken(String token);
}
