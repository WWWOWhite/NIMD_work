package work;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class mainPart {

	static Set<String> urls = new CopyOnWriteArraySet<String>();
	static ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
	static AtomicInteger count = new AtomicInteger(0);
	
	public static boolean getHtml(String url)
	{
		if(count.get() >= 5)
		{
			//���Ƶ�ַ��������
			pool.shutdown(); //���ٽ����µ��������󣬻�ȴ����������
			return false;
		}
		else if(urls.contains(url))
		{
			return false; //�ظ��ĵ�ַ��������
		}
		else
		{
			count.incrementAndGet();
			urls.add(url);
			//��ÿ��url��ץȡ��̬html��image
			pool.execute(new HtmlCrawler(url));
			pool.execute(new ImageCrawler(url));
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception
	{
		mainPart.getHtml("https://www.baidu.com/");
		
		while(true)
		{
			Thread.sleep(3000);
			int count = mainPart.pool.getActiveCount();
			if(0 == count)
			{
				System.out.println("there is no tasks");
				break;
			}
		}
	}
}
