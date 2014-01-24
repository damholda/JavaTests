package test;

import org.junit.Test;

public class NumberOperators {

	@Test
	public void main() {
		byte negativeByte = -8;
		System.out.println("negativeByte: "+negativeByte);
		byte positiveByte = 8;
		System.out.println("positiveByte: "+positiveByte);
		
		int shiftLeftNegativeByte = negativeByte << 1;
		System.out.println("shiftLeft NegativeByte: "+shiftLeftNegativeByte);
		int shiftLeftPositiveByte = positiveByte << 1;
		System.out.println("shiftLeft PositiveByte: "+shiftLeftPositiveByte);
		
		int shiftRightNegativeByte = negativeByte >>> 1;
		System.out.println("shiftRight NegativeByte: "+shiftRightNegativeByte);
		int shiftRightPositiveByte = positiveByte >>> 1;
		System.out.println("shiftRight PositiveByte: "+shiftRightPositiveByte);
		
		int shiftRightArtNegativeByte = negativeByte >> 1;
		System.out.println("shiftRightArt NegativeByte: "+shiftRightArtNegativeByte);
		int shiftRightArtPositiveByte = positiveByte >> 1;
		System.out.println("shiftRightArt PositiveByte: "+shiftRightArtPositiveByte);
		

	}

}