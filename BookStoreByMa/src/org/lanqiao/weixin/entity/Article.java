package org.lanqiao.weixin.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.lanqiao.weixin.bean.CDataAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String Title;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String Description;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String PicUrl;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Article(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}

	public Article() {
		super();
	}

}
