package org.lanqiao.weixin.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.lanqiao.weixin.bean.CDataAdapter;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsMessage {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String ToUserName;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String FromUserName;
	private String CreateTime;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String MsgType = "news";
	private String ArticleCount;
	@XmlElementWrapper(name="Articles")
	@XmlElement(name="item")
	private List<Article> Articles;

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

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.ArticleCount=articles.size()+"";
		Articles = articles;
	}

	public NewsMessage(String toUserName, String fromUserName, String createTime, String msgType, String articleCount,
			List<Article> articles) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		ArticleCount = articleCount;
		Articles = articles;
	}

	public NewsMessage() {
		super();
	}

}
