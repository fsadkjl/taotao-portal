package com.wjl.portal.service;

import com.wjl.portal.pojo.TbItemCustom;

public interface ItemInfoService {
	TbItemCustom getItemInfo(Long id);
	String getItemDesc(Long id);
	String getItemParamData(Long id);
}
