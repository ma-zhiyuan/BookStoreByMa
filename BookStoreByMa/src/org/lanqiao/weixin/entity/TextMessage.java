package org.lanqiao.weixin.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.lanqiao.weixin.bean.CDataAdapter;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextMessage {

	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String ToUserName;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String FromUserName;
	private String CreateTime;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String MsgType="text";
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String Content;

	public TextMessage(String toUserName, String fromUserName, String createTime, String msgType, String content) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
	}

	@Override
	public String toString() {
		return "TextMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + ", Content=" + Content + "]";
	}

	public TextMessage(String createTime, String content) {
		super();
		CreateTime = createTime;
		Content = content;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public TextMessage() {
		super();
	}

}
