package test;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * @author ReverieNight@Foxmail.com
 *
 */
public class AniMusicProcessor implements PageProcessor{
	
	private Site site = Site.me().setSleepTime(1000).setRetryTimes(3);
	
	//列表页的正则表达式
	public static final String URL_LIST = "http://www\\.36dm\\.com/sort-4-\\d+\\.html";
	//详情页的正则表达式
    public static final String URL_POST = "http://www\\.36dm\\.com/show-\\w+\\.html";
	
	@Override
	public Site getSite() {
		return site;
	}
	
	@Override
	public void process(Page page) {
		//列表页
        if (page.getUrl().regex(URL_LIST).match()) {
        	List<String> l_post = page.getHtml().xpath("//div[@class=\"clear\"]").links().regex(URL_POST).all(); //目标详情
        	List<String> l_url = page.getHtml().links().regex(URL_LIST).all();	//所有的列表
            System.out.println("post="+l_post+"    url="+l_url);
        	//  page.addTargetRequests(l_post);
          //  page.addTargetRequests(l_url);
        //详情页
        } else {
        	String title = page.getHtml().xpath("//div[@class='location']").regex("\\[[\\S|\\s]+\\<").toString();	//匹配标题
            page.putField("title", title.substring(0, title.length() - 1).trim());
            page.putField("torrent", page.getHtml().xpath("//p[@class='original download']").links().toString().trim());	//匹配种子
            System.out.println();
        }	
	}
	
	public static void main(String[] args) {
		Spider.create(new AniMusicProcessor())
			.addUrl("http://www.36dm.com/sort-4-1.html")	//开始地址	
			.addPipeline(new ConsolePipeline())	//打印到控制台
			.addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa"))	//保存到文件夹
			.thread(5)	//开启5线程
			.run();
	}
	
}
