package org.lanqiao.admin.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.swetake.util.Qrcode;

@SuppressWarnings("rawtypes")
public class MyUtils {
	// 配置信息
	public static final Properties config = new Properties();

	// 数据源
	public static final ComboPooledDataSource ds = new ComboPooledDataSource();

	// Dbutils工具入口
	public static final QueryRunner qr = new QueryRunner();
	
	//事务处理
	public static final ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

	/**
	 * 获取父类的泛型类型
	 * 
	 * @param c
	 * @return
	 */
	public static Class getGenericClass(Class c) {
		ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
		Type t = (Type) pt.getActualTypeArguments()[0];
		Class clazz = (Class) t;
		return clazz;
	}

	public static String sha1(String pwd) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("sha1");
			byte[] digest = md5.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String md5(String pwd) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] digest = md5.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byteToHexStr(byte[] digest) {
		char[] c = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuffer sb = new StringBuffer();
		for(byte b:digest){
			sb.append(c[(b>>4)&15]);
			sb.append(c[b&15]);
		}
		return sb.toString();
	}

	public static int getRandom(){
		return(int)(Math.random()*1000000);
	}
	
	public static void encoderQRCoder(String content, HttpServletResponse response) {  
        try {  
            Qrcode handler = new Qrcode();  
            handler.setQrcodeErrorCorrect('M');  
            handler.setQrcodeEncodeMode('B');  
            handler.setQrcodeVersion(7);  
              
            byte[] contentBytes = content.getBytes("UTF-8");  
              
            BufferedImage bufImg = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);  
              
            Graphics2D gs = bufImg.createGraphics();  
            
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 140, 140);  
              
            //设定图像颜色：BLACK  
            gs.setColor(Color.BLACK);  
              
            //设置偏移量  不设置肯能导致解析出错  
            int pixoff = 2;  
            //输出内容：二维码  
            if(contentBytes.length > 0 && contentBytes.length < 124) {  
                boolean[][] codeOut = handler.calQrcode(contentBytes);  
                for(int i = 0; i < codeOut.length; i++) {  
                    for(int j = 0; j < codeOut.length; j++) {  
                        if(codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff,3, 3);  
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");  
            }  
              
            gs.dispose();  
            bufImg.flush();  
              
            //生成二维码QRCode图片  
            ImageIO.write(bufImg, "jpg", response.getOutputStream());  
              
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
