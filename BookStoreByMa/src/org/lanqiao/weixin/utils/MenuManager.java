package org.lanqiao.weixin.utils;

import java.net.MalformedURLException;

import org.junit.Test;

import net.sf.json.JSONObject;

public class MenuManager {

	@Test
	public void delete(){
		String at = WxUtils.getAccessToken();
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+at;
		try {
			JSONObject jsonObject = WxUtils.httpRequest(urlStr, null);
			if(((Integer)(jsonObject.get("errcode")))==0){
				System.out.println("删除成功。");
			}else{
				System.out.println("删除失败");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void create(){
		String at = WxUtils.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+at;
		String data= WxUtils.getMenu();
		JSONObject jsonObject;
		try {
			jsonObject = WxUtils.httpRequest(url, data);
			if(((Integer)(jsonObject.get("errcode")))==0){
				System.out.println("创建成功。");
			}else{
				System.out.println("创建失败");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
