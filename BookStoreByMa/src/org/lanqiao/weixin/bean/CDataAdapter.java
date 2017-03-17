package org.lanqiao.weixin.bean;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDataAdapter extends XmlAdapter<String, String> {

	@Override
	public String marshal(String str) throws Exception {
		return "<![CDATA[" + str + "]]>";
	}

	// 从xml到javabean的适配方法
	@Override
	public String unmarshal(String str) throws Exception {
		return str;
	}

}
