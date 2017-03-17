package org.lanqiao.weixin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.service.BookService;
import org.lanqiao.admin.service.UserService;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.bean.Page;
import org.lanqiao.shop.entity.Order;
import org.lanqiao.shop.entity.ShoppingItem;
import org.lanqiao.shop.service.OrderService;
import org.lanqiao.weixin.entity.Article;
import org.lanqiao.weixin.entity.NewsMessage;
import org.lanqiao.weixin.entity.TextMessage;
import org.lanqiao.weixin.utils.WxUtils;

public class WxService {

	public static String getResponseMsg(Map<String, String> msgMap) {
		//获取消息的类型
		String msgType = msgMap.get("MsgType");
		String toUserName = msgMap.get("ToUserName");
		String fromUserName  = msgMap.get("FromUserName");
		String createTime = System.currentTimeMillis()/1000+"";
		
		//这是一个文本消息
		if(msgType.equals("text")){
			//获取用户输入的内容
			String content = msgMap.get("Content");
			if(WxUtils.mode.get(fromUserName)!=null&&WxUtils.mode.get(fromUserName)==WxUtils.MODE_BIND){
				if(content.equals("e")){
					WxUtils.mode.remove(fromUserName);
					TextMessage tm = new TextMessage();
					String respMsg="您已退出用户绑定模式。";
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
				if(!content.contains("#")){
					TextMessage tm = new TextMessage();
					String respMsg="格式不正确，请重新输入：用户名#密码，退出回复e";
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
				String name = content.substring(0, content.indexOf("#"));
				String pwd = content.substring(content.indexOf("#")+1);
				//调用User的业务方法来绑定账号
				int i = new UserService().toBind(name,MyUtils.md5(pwd),fromUserName);
				TextMessage tm = new TextMessage();
				String respMsg=null;
				switch(i){
					case 0:
						respMsg="绑定成功。";
						WxUtils.mode.remove(fromUserName);
						break;
					case 1:
						respMsg="用户名不存在，请重新输入，回复e退出。";
						break;
					case 2:
						respMsg="密码不正确，请重新输入，回复e退出。";
						break;
					case 3:
						respMsg="您的微信号已绑定其它账号，请先解绑，回复e退出。";
						break;
					case 4:
						respMsg="您输入的账号已被其它微信号绑定，请先解绑，回复e退出。";
						break;
					default:
						respMsg="网络异常，请重试。";
				}
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}
			//调用BookService的方法获取一个Book的List
			BookService bs = new BookService();
			//通过BookService获取图书
			Page<Book> page = bs.getWeiXinBook(content);
			List<Book> books = page.getItems();
			System.out.println(books);
			//进行判断，看是否是书，如果有就回复图文消息
			//创建个Articles的List
			if(books.size()>0){
				//创建一个图文消息
				NewsMessage nm = new NewsMessage();
				nm.setCreateTime(createTime);
				nm.setFromUserName(toUserName);
				nm.setToUserName(fromUserName);
				List<Article> articles = new ArrayList<Article>();
				//使用for循环来为图文消息设置内容,一本书是一个Article对象，
				for(Book b:books){
					Article a = new Article();
					a.setDescription(b.getPublishCorp());
					a.setTitle(b.getName());
					a.setPicUrl(WxUtils.getBasePath(b.getSurface()));
					a.setUrl(WxUtils.getSnsapiBaseUrl(WxUtils.getUrl(b)));
					articles.add(a);
				}
				nm.setArticles(articles);
				String respXml = WxUtils.convertToXml(nm);
				return respXml;
			}else{
				//没书，就直接回复文本消息就可以了。
				TextMessage tm = new TextMessage();
				String respMsg="查无此书";
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}
		}else if(msgType.equals("location")){
			//调用其它业务方法获取返回的消息
			String respMsg = "您当前在："+msgMap.get("Label")+"\n"+
					"坐标：x "+msgMap.get("Location_X")+",y "+msgMap.get("Location_Y");
			//回复的消息
			TextMessage tm = new TextMessage();
			tm.setContent(respMsg);
			tm.setCreateTime(createTime);
			tm.setFromUserName(toUserName);
			tm.setToUserName(fromUserName);
			String respXml = WxUtils.convertToXml(tm);
			return respXml;
		}else if(msgType.equals("event")){
			String event = msgMap.get("Event");
			if(event.equals("subscribe")){
				String respMsg = "欢迎关注我的测试号。。。";
				TextMessage tm = new TextMessage();
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}else if(event.equals("unsubscribe")){
				//取消关注时回复的消息
			}else if(event.equals("LOCATION")){
				String respMsg = "位置信息已收到";
				TextMessage tm = new TextMessage();
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}else if(event.equals("CLICK")){
				String eventKey = msgMap.get("EventKey");
				//用户绑定账号
				if(eventKey.equals("11")){
					WxUtils.mode.put(fromUserName, WxUtils.MODE_BIND);
					String respMsg = "请回复用户名和密码，格式：用户名#密码,退出绑定回复e。";
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("12")){
					//调用业务方法获取余额
					User u = new UserService().getByOpenId(fromUserName);
					String respMsg = null;
					if(u==null){
						respMsg="您还没有绑定账号，请先绑定账号。";
					}else{
						respMsg = "您的余额："+u.getBalance()+"元";
					}
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("13")){
					User u = new UserService().getByOpenId(fromUserName);
					String respMsg = null;
					StringBuffer name = new StringBuffer();
					if(u==null){
						respMsg="您还没有绑定账号，请先绑定账号。";
					}else{
						OrderService os = new OrderService();
						List<Order> orders = os.getAll(u);
						u.setOrders(orders);
						for (Order order : orders) {
							List<ShoppingItem> items = order.getItems();
							for (ShoppingItem shoppingItem : items) {
								name.append("\t"+shoppingItem.getBook().getName()+"\n");
							}
						}
						respMsg = "您购买过：\n"+name;
					}
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("23")){
					String respMsg = "菜单三子菜单3被点了一下。。。。。。。。？？？？";
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
			}
		}
		return null;
	}

}
