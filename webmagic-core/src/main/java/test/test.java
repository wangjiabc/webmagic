package test;

import java.util.HashMap;
import java.util.Map;

import tool.testUrl;




public class test {
	 public static void main(String args[]) {  
		 Map<String, String> map=new HashMap<>();
		 map.put("aaa", "aaa");
		 map.put("jiazhengbaojiexin", "4444");
		 map.put("bbb", "aaa");
		 map.put("ccc", "aaa");
		 
		 map.put("ddd", "aaa");
		 map.put("fff", "aaa");
		 map.put("eee", "aaa");
		 
		 map.put("aaa", "aaa");
 map.put("ccc", "aaa");
		 
		 map.put("zhuanye", "sdsds");
		 map.put("fff", "aaa");
		 map.put("eee", "aaa");
		 
		 map.put("aaa", "aaa");
		 String url="http://luzhou.58.com/zhuanye/30457499678397x.shtml?psid=143322286196566960445646755&entinfo=30457499678397_j&ytdzwdetaildj=0";
         String aString=testUrl.match(map, url);
         System.out.println(aString);
	 }
}
