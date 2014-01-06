package test.inheritance;

public class B extends A {
	
	private static int staticInt;
	private final int finalInt;
	private int normalInt;

	@SuppressWarnings("static-access")
	public B(int staticInt, int finalInt, int normalInt) {
		super(staticInt, finalInt, normalInt);
		this.staticInt = staticInt+10;
		this.finalInt = finalInt+10;
		this.normalInt = normalInt+10;
	}
	
//	@Override
	public static int getStaticInt() {
		return staticInt;
	}
	
	@Override
	public int getFinalInt() {
		return finalInt;
	}

//	@Override
	public int getNormalInt() {
		return normalInt;
	}
	
//	public String toString() {
//		return getClass()+
//				", s:"+staticInt+
//				", f:"+finalInt+
//				", n:"+normalInt;
//	};

}
