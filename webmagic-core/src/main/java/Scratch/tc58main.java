package Scratch;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class tc58main implements PageProcessor {
	public static final String URL_LIST = "http://luzhou.58.com/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
	
	 private Site site = Site
	            .me()
	            .setDomain("http://luzhou.58.com/")
	            .setRetryTimes(3)
	            .setSleepTime(10000)
	            .setUserAgent(
	                    "Mozilla/5.0");

	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		System.out.println("pageurl="+page.getUrl());
        //列表页
    	List<String> requestsUrl=page.getHtml().xpath("//*[@id=\"sidebar-right\"]/ul/li/strong").links().regex(URL_LIST).all();
    	for (String s : requestsUrl) {
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                continue;
            }
            System.out.println("ss="+s);
        	new tc58().start(s);

        }
    	}

	 @Override
	    public Site getSite() {
	    	site.setDomain("luzhou.58.com");
	        return site;
	    }
	
	 public static void main(String[] args) {
	        Spider.create(new tc58main()).addUrl("http://luzhou.58.com/job.shtml?PGTID=0d100000-0094-4b81-08f9-d2148ec9cf8a&ClickID=9")
	        .thread(1).start();
	    }

	
	 
}
