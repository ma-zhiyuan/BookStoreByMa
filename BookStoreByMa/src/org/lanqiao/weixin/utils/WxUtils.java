package org.lanqiao.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.util.MyUtils;

import net.sf.json.JSONObject;

public class WxUtils {
	public static final String TOKEN = "ma";
	public static final String APPID = "wx231e65dc1e1b1d98";
	public static final String APPSECRET = "f2588f68dd9a8c9bf711ddae2acf5942";
	//使用一个容器来存储用户的工作模式,键是用户的openid，值是对应的功能
	public static final Map<String, Integer> mode = new HashMap<String, Integer>();
	//用户绑定账号模式
	public static final int MODE_BIND=1;
	
	public static String getMenu() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = WxUtils.class.getClassLoader().getSystemResourceAsStream("menu.txt");
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String str = null;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 发送带数据的http请求，
	 * 
	 * @param urlStr
	 * @param data
	 * @return
	 * @throws MalformedURLException
	 */
	public static JSONObject httpRequest(String urlStr, String data) throws MalformedURLException {
		URL url = new URL(urlStr);
		OutputStream os = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// 开启连接
			URLConnection urlConnection = url.openConnection();
			if (data != null) {
				// 允许传出数据
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(false);
				
				// 获得输出流准备传出数据
				os = urlConnection.getOutputStream();
				// 传入数据
				os.write(data.getBytes());
				os.close();
			}
			// 准备接收数据
			is = urlConnection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String str = null;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
			return jsonObject;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取Access Token
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		urlStr = urlStr.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

		JSONObject jsonObject;
		try {
			jsonObject = httpRequest(urlStr, null);
			Object objStr = jsonObject.get("access_token");
			return (String) objStr;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean verify(String signature, String timestamp, String nonce) {
		String[] strs = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(strs);
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s);
		}
		String sha1 = MyUtils.sha1(sb.toString());
		if (sha1.equals(signature.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

	public static Map<String, String> parseMsg(InputStream is) {
		Map<String, String> msgMap = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			for (Element e : elements) {
				msgMap.put(e.getName(), e.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return msgMap;
	}

	public static String convertToXml(Object obj) {
		String result = null;
		String encoding = "utf-8";
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			// 去掉生成xml的默认报文头。
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			// 转换所有的适配字符，包括xml实体字符&lt;和&gt;，xml实体字符在好多处理xml
			// 的框架中是处理不了的，除非序列化。
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getBasePath(String uri) {
		return "http://www.ifma.me/BookStoreByMa/" + uri;
	}

	public static String getUrl(Book b) {
		return getBasePath("book/bookDetail?id=") + b.getId();
	}
	
	public static String getSnsapiBaseUrl(String uri){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";
		url=url.replace("APPID", WxUtils.APPID);
		url=url.replace("REDIRECT_URI", uri);
		url=url.replace("SCOPE", "snsapi_base");
		return url;
	}

	public static JSONObject getAccessTokenOpenId(String code) {
		String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
		url=url.replace("APPID", WxUtils.APPID);
		url=url.replace("SECRET", WxUtils.APPSECRET);
		url=url.replace("CODE", code);
		try {
			JSONObject jsonObject = httpRequest(url,null);
			return jsonObject;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
