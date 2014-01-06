package test;

public final class Singleton {

	private static volatile String resource;
//	private static volatile Singleton INSTANCE;
	
	public Singleton(){
	}
	
//	public void getInstance(){
//		if (INSTANCE )
//		
//		INSTANCE = new Singleton();
//	}
	public static String getResource(){
		if(resource == null){
			synchronized (Singleton.class) {
				if (resource == null){
					resource = new String();
				}
			}
		}
		return resource;
	}
}
