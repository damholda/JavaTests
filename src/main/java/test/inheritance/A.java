package test.inheritance;

public class A {
	
	private static int staticInt;
	private final int finalInt;
	private int normalInt;
	
	@SuppressWarnings("static-access")
	public A(int staticInt, int finalInt, int normalInt) {
		this.staticInt = staticInt;
		this.finalInt = finalInt;
		this.normalInt = normalInt;
	}
	
	public static int getStaticInt() {
		return staticInt;
	}

	public int getFinalInt() {
		return finalInt;
	}

	public int getNormalInt() {
		return normalInt;
	}
	
	public String toString() {
		return getClass()+
				", s:"+staticInt+
				", f:"+finalInt+
				", n:"+normalInt;
	};

}
