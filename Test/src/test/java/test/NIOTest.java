package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class NIOTest {

	@Test
	public void testMoveLogFiles() {
		
		File sourceDir = new File("C:/test/files/");
		File targetDir = new File("C:/test/filesToMove/");
		try {
			NIO.moveLogFiles(sourceDir, targetDir);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		
		System.out.println("Completed.");
	}

}
