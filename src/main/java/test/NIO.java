package test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class NIO {
	
	public static void moveLogFiles(File sourceDir, File targetDir) throws IOException {
		File [] files = sourceDir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".log");
		    }
		});
		for (File file : files) {
			File targetFile = new File(targetDir +"\\"+ file.getName());
			if (file.renameTo(targetFile)){
				System.out.println("File moved successfully.");
			}else{
				System.out.println("Failed to move file: "+file.getAbsolutePath());
			}
		}	
		
//		try{
//		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("")));
//		
//		}finally{
//			
//		}
	}

}
