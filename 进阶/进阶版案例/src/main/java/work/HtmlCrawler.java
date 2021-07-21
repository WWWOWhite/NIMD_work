package work;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlCrawler implements Runnable{
	
	private static String tempPath = "C:/temp/html"; //图片保存目录
	String url;
	
	public HtmlCrawler(String url)
	{
		this.url = url;
	}
	
	public void run()
	{
		try
		{
			String html = new HttpUtil().getHtml(url); //读取html
			List<String> urls = getUrl(html);	//解析html中的url
			if(urls != null && urls.size()>0)
			{
				for(String url : urls)
				{
					if(url.startsWish("http"))
					{
						boolean result = 
					}
				}
			}
		}
	}

}
