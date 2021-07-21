package anotherProject;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;

public class Searcher implements FileVisitor {
	
	private final PathMatcher matcher;
	private ArrayList<String> filePaths = new ArrayList<String>();
	
	public Searcher(String ext) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:"+ ext);	
	}
	
	public void judgeFile(Path file) throws IOException{
		Path name  = file.getFileName();
		if(name != null && matcher.matches(name))
		{
			filePaths.add(file.toRealPath().toString());
		}
	}
	
	public FileVisitResult postVisitDirectory(Object dir,IOException exc)throws IOException{
		return FileVisitResult.CONTINUE;
	}
	
	public FileVisitResult preVisitDirectory(Object dir,BasicFileAttributes attrs) throws IOException{
		return FileVisitResult.CONTINUE;
	}
	public FileVisitResult visitFile(Object file,BasicFileAttributes attrs)throws IOException{
		judgeFile((Path) file);
		return FileVisitResult.CONTINUE;
	}
	
	public FileVisitResult visitFileFailed(Object file,IOException exc)throws IOException{
		return FileVisitResult.CONTINUE;
	}
	
	public ArrayList<String> getFilePaths(){
		return filePaths;
	}
}
