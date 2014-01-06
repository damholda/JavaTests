package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

public class JodaTimeTest {
	
	@Test
	public void periodTest() {
		DateTime start = DateTime.now();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
		DateTime end = DateTime.now();
		
		long time = end.getMillis() - start.getMillis();
		
		long aTime = 121135;
		long seconds = aTime/1000;
		
		long rSec = seconds%60;
		long min = seconds/60;
		
		long rMin = min%60;
		long hour = min/60;
		
		System.out.println(time);
		System.out.println("Total sec: "+seconds);
		System.out.println("sec: "+rSec);
		System.out.println("Total min: "+min);
		System.out.println("min: "+rMin);
		System.out.println("Hours: "+hour);
	}

}
