package com.wjl.portal.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.common.util.JsonUtils;
import com.wjl.pojo.TbItemDesc;
import com.wjl.pojo.TbItemParamItem;
import com.wjl.portal.pojo.TbItemCustom;
/**
 * 点击图片获取商品相关信息的@Service
 */
@Service
public class ItemInfoServiceImpl implements ItemInfoService{
	//itemInfoURL=http://localhost:8081/rest/itemInfo/
	@Value("${itemInfoURL}")
	private String itemInfoURL;
	@Value("${itemDescURL}")
	private String itemDescURL;
	@Value("${itemParamDataURL}")
	private String itemParamDataURL;
	
	@Override
	public TbItemCustom getItemInfo(Long id) {
		//向rest服务中查询商品详细信息
		String string = HttpClientUtil.doGet(itemInfoURL+id);
		if (StringUtils.isNotBlank(string)) {
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(string, TbItemCustom.class);
			if (taotaoResult.getStatus() == 200) {
				return (TbItemCustom) taotaoResult.getData();
			}
		}
		return null;
	}
	
	@Override
	public String getItemDesc(Long id) {
		//向rest服务中查询商品描述
		String string = HttpClientUtil.doGet(itemDescURL+id);
		if (StringUtils.isNotBlank(string)) {
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(string, TbItemDesc.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemDesc tbItemDesc = (TbItemDesc) taotaoResult.getData();
				return tbItemDesc.getItemDesc();
			}
		}
		return null;
	}

	@Override
	public String getItemParamData(Long id) {
		//向rest服务中查询商品规格参数
		String string = HttpClientUtil.doGet(itemParamDataURL+id);
		if (StringUtils.isNotBlank(string)) {
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(string, TbItemParamItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemParamItem tbItemParamItem = (TbItemParamItem) taotaoResult.getData();
				String paramData = tbItemParamItem.getParamData();
				//生成html
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for(Map m1:jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for(Map m2:list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
						sb.append("            <td>"+m2.get("v")+"</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				//返回html片段
				return sb.toString();
			}
		}
		return null;
	}

}
