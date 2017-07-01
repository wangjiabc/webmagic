package test;

import org.apache.commons.httpclient.Cookie;

import tool.login;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;


public class bbs implements PageProcessor {

/*
     public static final String URL_LIST = "http://www.52pojie.cn/forum-67-\\d+\\.html";


    public static final String URL_POST = "http://www.52pojie.cn/thread-\\d+\\-1-1.html";


    private Site site = Site
            .me()
            .setDomain("www.52pojie.cn")
            .setRetryTimes(3)
            .setSleepTime(10000)
            .setUserAgent(
                    "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)");


    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
               page.addTargetRequests(page.getHtml().xpath("//*[@id=\"threadlisttableid\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            page.putField("title", page.getHtml().xpath("//*[@id=\"postlist\"]/table/tbody/tr/td[2]/h1/span/text()").toString());
            page.putField("content", page.getHtml().xpath("//td[contains(@id,'postmessage')]/outerHtml()").toString());
            page.putField("date",
                                page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().substring(3,page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().length()));
           
        }
    }


    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new Spider52pojiePageProcessor()).addUrl("http://www.52pojie.cn/forum-67-1.html")
        .addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa")).thread(3).run();
    }
*/
	
	
  //  public static final String URL_LIST = "http://zend-pongyou.rhcloud.com/bbs/forum.php?mod=forumdisplay&fid=\\d+";


 //   public static final String URL_POST = "http://zend-pongyou.rhcloud.com/bbs/forum.php?mod=viewthread&tid=\\d+\\&extra=page%3D1";

	 public static final String URL_LIST = "http://0830bbs.com/f-40-\\d+\\.html";


	  public static final String URL_POST = "http://0830bbs.com/t-\\d+\\-1-\\d+\\.html";

	
	
    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
               page.addTargetRequests(page.getHtml().xpath("//*[@id=\"threadlisttableid\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            page.putField("title", page.getHtml().xpath("//*[@id=\"postlist\"]/table/tbody/tr/td[2]/h1/span/text()").toString());
            page.putField("content", page.getHtml().xpath("//td[contains(@id,'postmessage')]/outerHtml()").toString());
            page.putField("date",
                                page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().substring(3,page.getHtml().xpath("//em[contains(@id,'authorposton')]/text()").toString().length()));
           
        }
    }


    @Override
    public Site getSite() {
    	login bbs=new login();
    //	Cookie[] cookies=bbs.loginBbs("http://0830bbs.com","aaab","123456"); 
    	Site site = Site
                .me()
             //   .setDomain("0830bbs.com")
                .setRetryTimes(3)
                .setSleepTime(10000)
                .setUserAgent(
                        "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)");

	/*	for (Cookie c : cookies) {
		System.out.println("cookie name="+c.getName()+" value="+c.getValue());
		 site.addCookie(c.getName(), c.getValue());
		}
		*/
    	System.out.println("site="+site);
        return site;
    }


    public static void main(String[] args) {
    	Spider.create(new bbs()).addUrl("http://0830bbs.com/f-40-1.html")
        .addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa"))
        .thread(3).start();
    	

    }
}
