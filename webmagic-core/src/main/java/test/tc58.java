package test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

public class tc58 implements PageProcessor {
	
	public static final String URL_LIST = "[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

	    private Site site = Site
	            .me()
	            .setDomain("http://luzhou.58.com/")
	            .setRetryTimes(3)
	            .setSleepTime(100000)
	            .setUserAgent(
	                    "Mozilla/5.0");


	    @Override
	    public void process(Page page) {
	    	System.out.println("pageurl="+page.getUrl());
	  //    System.out.println("xpth="+page.getHtml().xpath("//*[@id=\"list_con\"]/li"));
	    	//列表页
	            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"list_con\"]/li").links().regex(URL_LIST).all(),10);
	            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"infolist\"]/dl/dt").links().regex(URL_LIST).all(),10);
	            
              //文章页
	            page.putField("title", page.getHtml().xpath("//*[@class=\"pos_title\"]/outerHtml()").toString());
	            page.putField("content1", page.getHtml().xpath("//*[@class=\"subitem_con company_baseInfo\"]/outerHtml()").toString());	       	 
	            page.putField("content2", page.getHtml().xpath("//*[@class=\"item_con pos_info\"]/outerHtml()").toString());
	                 //    page.putField("date",
	          //                      page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().substring(3,page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().length()));
	            String title=page.getHtml().xpath("//*[@class=\"pos_title\"]/outerHtml()").toString();
	             
	             if(title==null){
	                	page.setSkip(true);
	               }
	    }


	    @Override
	    public Site getSite() {
	    	site.setDomain("luzhou.58.com");
	        return site;
	    }


	    public void start(String url) {
	    	 Spider.create(new tc58()).addUrl(url)
		        .addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa")).thread(3).start();
		}
	    
	    public static void main(String[] args) {
	        Spider.create(new tc58()).addUrl("http://luzhou.58.com/zplvyoujiudian/")
	        .addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa")).thread(3).start();
	    }
}
