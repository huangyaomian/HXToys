package cn.hx.appium.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.TextUtils;
import net.sf.json.JSONObject;

public class ConnectionHttp {
	// 获取服务器反馈的所有内容以String返回
	public static String getPostAllMessage(String qurl, JSONObject obj)
			throws Exception {
		final String message;
		// 连接服务器
		URL url = new URL(qurl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-type", "application/json");
		connection.setRequestMethod("POST");
		// post信息
		// 连接,也可以不用明文connect，使用下面的connection.getOutputStream()会自动connect
		OutputStream os = connection.getOutputStream();
		System.out.println(obj.toString());
		os.write(obj.toString().getBytes("utf-8"));
		os.flush();
		os.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			System.out.println(lines);
			sb.append(lines);
		}
		message = sb.toString();
		reader.close();
		if (!TextUtils.isEmpty(message)) {
			return message;
		} else {
			return null;
		}
	}

	// 获取服务器反馈的所有内容以String返回
	public static String getGetAllMessage(String qurl)
			throws Exception {
		final String message;
		// 连接服务器
		URL url = new URL(qurl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-type", "application/json");
//		connection.setRequestMethod("POST");
		connection.connect();
		// post信息
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			System.out.println(lines);
			sb.append(lines);
		}
		message = sb.toString();
		reader.close();
		if (!TextUtils.isEmpty(message)) {
			return message;
		} else {
			return null;
		}
	}

	//放回Post信息中是否含有Message
	public static boolean isMessageForPostJion(String qurl, JSONObject obj,
			String message) throws Exception {
		String allMessage = getPostAllMessage(qurl, obj);
		if (!TextUtils.isEmpty(allMessage) && allMessage != null) {
			return allMessage.contains(message);
		} else {
			return false;
		}
	}
	//获取对应键值的值，以String返回
	public static String getPostValuseString(String qurl, JSONObject obj , String key) throws Exception {
		String value;
		JSONObject jsonObj;
		jsonObj = JSONObject.fromObject(getPostAllMessage(qurl, obj));
		value = jsonObj.get(key).toString();
		return value;
	}

	public static String getPostValuseString(String qurl, String key) throws Exception {
		String value;
		JSONObject jsonObj;
		jsonObj = JSONObject.fromObject(getGetAllMessage(qurl));
		value = jsonObj.get(key).toString();
		return value;
	}

	public static void main(String[] args) throws Exception {
		//		JSONObject obj = new JSONObject();
		//		System.out.println(ConnectionHttp.getPostValuseString(
		//				"http://120.24.224.208:28081/manager/user/page_query", obj,
		//				"body"));
		//		List<String> list=new ArrayList<>();

		//		String ob = ConnectionHttp.getPostValuseString("http://www2.hxinside.com:9998/uc_r3/user/getFirstMobileLoginCheckCode?terminal=android&version=1.01&data=Lvb14DzdaRXnsWCOz6%2FsMQ%3D%3D","serNo");
		//		System.out.println(ob);
//		String url="Mw1QuYMfinSgx+HON0O5xg==";
//		String urlEncodee= URLEncoder.encode(url);
//		System.out.println(urlEncodee);
		String rui = "http://192.168.2.108:7991/toys/user/getFirstMobileLoginCheckCode?terminal=android&version=1.01&data=vgCGlZ2Hhuwv6LyD1EG50Q%3D%3D";
		getGetAllMessage(rui);

	}

}
