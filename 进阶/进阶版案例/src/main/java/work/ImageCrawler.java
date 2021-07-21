package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class ImageCrawler {
	
	private static String tempPath = "c:/temp/image";
	private static String BLANK = "";
	// phantomjs��������
	private static String binPath = "";// ��������ַ
	private static String jsPath = "";// js�����ַ �����ļ���ȱ��screenjs
	
	String url;
	
	public ImageCrawler(String url)
	{
		this.url = url;
	}
	
	public void run()
	{
		try
		{
			captureScreen();
		}
		catch( Exception e)
		{
			
		}
	}
	
	public void captureScreen() throws Exception{
		String imagePath = tempPath+"/"+System.currentTimeMillis()+(new Random().nextInt(1000))+".png";
		//java��ʹ��Runtime��Process�������ⲿ����
		Process process = Runtime.getRuntime().exec(cmd(imagePath,url));
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String tmp = "";
		while((tmp = reader.readLine())!= null)
		{}
		
		int exitVal = process.waitFor();
		if(exitVal == 0) {
		}
		reader.close();
		if(process != null)
		{
			process.destroy();
			process = null;
		}
		
	}
	
	//cmd����
	public static String cmd(String imgagePath , String url)
	{
    	String str = binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    	System.out.println(str);
        return binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
	}
	
	//�ر�����
	public static void close(Process process,BufferedReader bufferedReader) throws IOException{
		if(bufferedReader != null)
		{
			bufferedReader.close();
		}
		if(process != null)
		{
			process.destroy();
			process = null;
		}
	}
	
	public static void main(String[] args) throws Exception{
		String url = "http://www.baidu.com/";
		new ImageCrawler(url).captureScreen();
	}
}
