package test;

public class NumberOperators {

	
	public static void main(String[] args) {
		byte negativeByte = -8;
		System.out.println("negativeByte: int: "+negativeByte);
		byte positiveByte = 8;
		System.out.println("positiveByte: "+positiveByte);
		//arithmetic
		int shiftLeftNegativeByte = negativeByte << 1;
		System.out.println("shiftLeft NegativeByte: "+shiftLeftNegativeByte);
		int shiftLeftPositiveByte = positiveByte << 1;
		System.out.println("shiftLeft PositiveByte: "+shiftLeftPositiveByte);
		
		int shiftRightNegativeByte = negativeByte >> 1;
		System.out.println("shiftRight NegativeByte: "+shiftRightNegativeByte);
		int shiftRightPositiveByte = positiveByte >> 1;
		System.out.println("shiftRight PositiveByte: "+shiftRightPositiveByte);
		//logical
		int shiftRightLogicalNegativeByte = negativeByte >>> 1;
		System.out.println("shiftRightLogical NegativeByte: "+shiftRightLogicalNegativeByte);
		int shiftRightLogicalPositiveByte = positiveByte >>> 1;
		System.out.println("shiftRightLogical PositiveByte: "+shiftRightLogicalPositiveByte);		

	}

}