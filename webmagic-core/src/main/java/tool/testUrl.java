package tool;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testUrl {
   private final static Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/");
  
   public static String getHost(String url){
		  if(url==null||url.trim().equals("")){
		   return "";
		  }
		  String host = "";
		  
		  Matcher matcher = p.matcher(url);  
		  if(matcher.find()){
		   host = matcher.group();  
		  }
		  return host;
		 }
   
   public static String match(Map<String, String> map,String url) {
	     String path=getHost(url);
	     Iterator<Map.Entry<String, String>> entries=map.entrySet().iterator();
	     String name=null;
	     int i=0;
	     while (entries.hasNext()) {
	    	 Map.Entry<String, String> entry=entries.next();
	    	 Pattern p =  Pattern.compile(entry.getKey());
	    	 Matcher matcher = p.matcher("/"+url+"/");  
			  if(matcher.find()){
			   name = entry.getValue();
			//   break;
			  }
			  System.out.println("i="+i);
			  i++;
		   }
	     return name;
    }
}
