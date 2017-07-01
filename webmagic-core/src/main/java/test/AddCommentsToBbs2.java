package test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class AddCommentsToBbs2 {

/**
* @param args
*/

private Boolean isProxy;

public AddCommentsToBbs2(Boolean isProxy) {
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

if (isProxy) {
httpClient.getHostConfiguration().setProxy(bbsUrl, 80);
UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
"xxxxx", "00000");
httpClient.getState().setProxyCredentials(AuthScope.ANY, creds);
}

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

//System.out.println("ss");

if ("".equals(returnBody)) {
System.out.println("登陆成功");
// 获得登陆后的 Cookie
Cookie[] cookies = httpClient.getState().getCookies();
String tmpcookies = "";
for (Cookie c : cookies) {
tmpcookies += c.toString() + ";";
System.out.println("cookie="+tmpcookies);
}

String tempFormhash = "";
String code="";
String tempUrl=bbsUrl+"/forum.php?mod=forumdisplay&fid="+fid;
System.out.println("请求路径是："+tempUrl);
GetMethod getMethod = new GetMethod(tempUrl);
getMethod.setRequestHeader("cookie",tmpcookies);
getMethod.setRequestHeader("Referer",bbsUrl);
httpClient.executeMethod(getMethod);
String returnBody2 = getMethod.getResponseBodyAsString();

Pattern p1 = Pattern
.compile("input type=\"hidden\" name=\"formhash\" value=\"(.*)\"");
Matcher m1 = p1.matcher(returnBody2);
//System.out.println(returnBody2);
if (m1.find()) {
tempFormhash = m1.group(1);
}
System.out.println("AddCommentsToBbs.loginBbs() tempFormhash = " + tempFormhash);

Pattern p2 = Pattern.compile("");
Matcher m2 = p2.matcher(returnBody2);
//System.out.println(returnBody2);

code="gbk";
System.out.println("字符编号是：" + code);

PostMethod sendMessageMethod = new PostMethod(bbsUrl
+ "/forum.php");// 设置一个post请求
HttpMethodParams sendParams = sendMessageMethod.getParams();
sendParams.setContentCharset(code); // 设置编码
List sendNameValues = new ArrayList();// 设置参数列表

sendNameValues.add(new NameValuePair("mod", "post"));
sendNameValues.add(new NameValuePair("action", "newthread"));
sendNameValues.add(new NameValuePair("fid", fid));
sendNameValues.add(new NameValuePair("extra", ""));
sendNameValues.add(new NameValuePair("wysiwyg", "1"));

sendNameValues.add(new NameValuePair("topicsubmit", "yes"));
sendNameValues.add(new NameValuePair("view", "my"));
sendNameValues.add(new NameValuePair("type", "thread"));
sendNameValues.add(new NameValuePair("filter", "save"));

sendNameValues.add(new NameValuePair("infloat", "yes"));
sendNameValues
.add(new NameValuePair("handlekey", "fastnewpost"));

sendNameValues.add(new NameValuePair("typeid", "170"));
sendNameValues.add(new NameValuePair("formhash", tempFormhash));
sendNameValues.add(new NameValuePair("subject",
"现在是天天加班啊！~"));
sendNameValues
.add(new NameValuePair("message",
"现在是天天加班啊！~"));

sendNameValues.add(new NameValuePair("usesig", "1"));
sendNameValues.add(new NameValuePair("posttime", Long.toString(System.currentTimeMillis())));
sendNameValues.add(new NameValuePair("topicsubmit","topicsubmit"));

sendMessageMethod.setRequestBody((NameValuePair[]) sendNameValues
.toArray(new NameValuePair[sendNameValues.size()]));// 添加参数列表
sendMessageMethod.setRequestHeader("cookie",tmpcookies);
//sendMessageMethod.setRequestHeader("Referer",bbsUrl);
httpClient.executeMethod(sendMessageMethod);
String returnSendBody =sendMessageMethod.getResponseBodyAsString();
System.out.println("namevalue="+sendNameValues);
if("".equals(returnSendBody)){
System.out.println("发帖成功");
}else{
System.out.println("发帖失败");
System.out.println(returnSendBody);
}
System.out
.println("AddCommentsToBbs.loginBbs()———————="+tempFormhash);

} else {
Pattern p1 = Pattern.compile("(.*)");
Matcher m1 = p1.matcher(returnBody);
if (m1.find()) {
System.out.println("提示信息是：" + m1.group(1));
}
System.out.println("登陆失败");
}
} catch (HttpException e) {
e.printStackTrace();
} catch (IOException e) {

e.printStackTrace();
}
}

public static void main(String[] args) {
new AddCommentsToBbs2(false)
//.loginBbs("http://0830bbs.com","aaab","123456","40");
.loginBbs("http://zend-pongyou.rhcloud.com/bbs","a","123","2");
}

}