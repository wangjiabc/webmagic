package tool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class login {
	
	//登陆dz论坛
	public Cookie[] loginBbs(String bbsUrl, String userName, String userPassword) {
		HttpClient httpClient = new HttpClient();// 定义一个客户端
		Cookie[] cookies=null;
		List headers = new ArrayList();
		headers.add(new Header("User-Agent",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
		httpClient.getHostConfiguration().getParams().setParameter(
		"http.default-headers", headers);
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(
		CookiePolicy.BROWSER_COMPATIBILITY);
		// 解决cookie报错信息
		DefaultHttpParams.getDefaultParams().setParameter(
		"http.protocol.cookie-policy",
		CookiePolicy.BROWSER_COMPATIBILITY);

		System.out.println("—————–这是模拟登陆论坛的代码———————");
		// 链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
		10000);
		// 读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(18000);

		// 开始准备数据进行登陆————————–开始
		PostMethod postMethod = new PostMethod(bbsUrl + "/member.php");// 设置一个post请求
		HttpMethodParams params = postMethod.getParams();
		params.setContentCharset("UTF-8"); // 设置编码
		List nameValues = new ArrayList();// 设置参数列表

		nameValues.add(new NameValuePair("mod", "logging"));
		nameValues.add(new NameValuePair("action", "login"));
		nameValues.add(new NameValuePair("loginsubmit", "yes"));
		nameValues.add(new NameValuePair("infloat", "yes"));
		nameValues.add(new NameValuePair("lssubmit", "yes"));
		nameValues.add(new NameValuePair("fastloginfield", "username"));
		nameValues.add(new NameValuePair("username", userName));
		nameValues.add(new NameValuePair("password", userPassword));
		nameValues.add(new NameValuePair("cookietime", "2592000"));

		nameValues.add(new NameValuePair("quickforward", "yes"));
		nameValues.add(new NameValuePair("handlekey", "ls"));

		postMethod.setRequestBody((NameValuePair[]) nameValues.toArray(new NameValuePair[nameValues.size()]));// 添加参数列表

		// 开始准备数据进行登陆————————–结束

		try {

		httpClient.executeMethod(postMethod);
		String returnBody = postMethod.getResponseBodyAsString();


	if ("".equals(returnBody)) {
		System.out.println("登陆成功");
		// 获得登陆后的 Cookie
		cookies = httpClient.getState().getCookies();
		
		return cookies;
	   }
	 }catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	  }
		return cookies;
	}

	
}
