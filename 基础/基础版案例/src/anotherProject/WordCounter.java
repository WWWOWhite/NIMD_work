package anotherProject;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class WordCounter {

	public static void main(String[] args) throws IOException{
		//����Ŀ¼��������չ��
		Path fileTree = Paths.get("C:/temp/");
		Searcher walk = new Searcher("*.txt");
		
		//���Ҹ�Ŀ¼�����е�txt�ļ�
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Files.walkFileTree(fileTree,opts,Integer.MAX_VALUE,walk);
		ArrayList<String> filePaths = walk.getFilePaths();
		
		//����ÿ���ļ��ĵ���
		HashMap<String,Word> totalMap = new HashMap<String,Word>();
		
		for(String str:filePaths)
		{
			HashMap<String,Word> partMap = new FileAnalyzer(str).getWordCount();
			if(partMap != null && partMap.size() > 0 )
			{
			//	combineMap(totalMap,partMap);
			}
		}
		
		ArrayList<Word> words = new ArrayList<Word>(totalMap.values());
		Collections.sort(words);
		
		System.out.println("�����");
		for(Word w :words)
		{
			System.out.println(w.getText() + "," + w.getTimes());
		}
	}
		

		public static void combineMap(HashMap<String, Word> total,HashMap<String, Word> part)
		{
			Iterator<Entry<String,Word>> iter = part.entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry<String, Word> entry = iter.next();
				//��ȡKEY
				String partKey = entry.getKey();
				//��ȡvalue
				Word partWord = entry.getValue();
				if(total.containsKey(partKey))
				{
					Word totalWord = total.get(partKey);
					totalWord.setTimes(totalWord.getTimes() + partWord.getTimes());
				}
				else
				{
					total.put(partKey, partWord);
				}
			}
		}
	
		
	
	
}
