package test;
import java.util.ArrayList;
import java.util.List;

import tool.User;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
public class  sss implements PageProcessor {
     public static final String URL_LIST ="http://rc\\.lyrc\\.net/Companyzp\\.aspx\\?Page=[1-9]{1,3}";
     public static final String URL_POST="/Person_Lookzl/id-[0-9]{4}\\.html";
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     static int size=1;

     private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        List<String> urls = page.getHtml().css("div#paging").links().regex("/Companyzp\\.aspx\\?Page=").all();
        if(page.getUrl().regex(URL_LIST).match()){

             page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());

             page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
             page.addTargetRequests(urls);
        }
        else{
            System.out.println("第"+size+"条");
            size++;
            User user =new User();
            String key="0";//keyword
            String name =page.getHtml().xpath("//*[@width='61%']/table/tbody/tr[1]/td[2]/text()").get();//用户名
            String sex= page.getHtml().xpath("//*[@width='61%']/table/tbody/tr[1]/td[4]/text()").get();//性别
            String minzu=page.getHtml().xpath("//*[@width='61%']/table/tbody/tr[2]/td[4]/text()").get().toString();//民族
            String location= page.getHtml().xpath("//*[@width='61%']/table/tbody/tr[3]/td[4]/text()").get();//所在地
            String identity=page.getHtml().xpath("//*td[@width='283']/text()").get();//身份学历
            String school=page.getHtml().xpath("//*td[@width='201']/text()").get();//学校
            String major=page.getHtml().xpath("//*[@width='90%']/tbody/tr[2]/td[4]/text()").get();//专业
            String work_experience=page.getHtml().xpath("//td[@width='773']/table/tbody/tr/td/table[6]/tbody/tr[2]/td[2]/text()").get();//工作经验
            String hope_position=page.getHtml().xpath("//td[@width='773']/table/tbody/tr/td/table[8]/tbody/tr[5]/td[2]/text()").get();//希望求职岗位
            String hope_palce=page.getHtml().xpath("//td[@width='773']/table/tbody/tr/td/table[8]/tbody/tr[4]/td[2]/text()").get();//希望工作地点
            String hope_salary=page.getHtml().xpath("//td[@width='773']/table/tbody/tr/td/table[8]/tbody/tr[2]/td[2]/text()").get();//希望待遇
            String work_type=page.getHtml().xpath("//td[@width='773']/table/tbody/tr/td/table[8]/tbody/tr[1]/td[2]/text()").get();
            user.setHope_palce(name);
            user.setHope_palce(hope_palce);
            user.setHope_position(hope_position);
            user.setHope_salary(hope_salary);
            user.setIdentity(identity); 
            user.setKey(key);
            user.setLocation(location);
            user.setMajor(major);
            user.setMinzu(minzu);
            user.setName(name);
            user.setSchool(school);
            user.setSex(sex);
            user.setWork_experience(work_experience);
            user.setWork_type(work_type);
            System.out.println(user.toString());
            System.out.println();

        }
        // 部分三：从页面发现后续的url地址来抓取
    }
    @Override
    public Site getSite() {

        return site;
    }

    public static void main(String args[]) {
        long startTime, endTime;
        startTime =System.currentTimeMillis();
        System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
        Spider.create(new sss()).addUrl("http://rc.lyrc.net/Companyzp.aspx?Page=1")
        .addPipeline(new FilePipeline("C:\\Users\\WangJing\\Desktop\\aaaaaaaaaaaa"))//.addPipeline(new ConsolePipeline)
            .thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + size + "篇文章，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");

    }

}