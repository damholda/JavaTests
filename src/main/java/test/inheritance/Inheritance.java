package test.inheritance;

public class Inheritance {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		A a = new A(1, 2, 3);
		A a2 = new B(4, 5, 6);
		B b = new B(7, 8, 9);
		
		System.out.println("----------------------------");
		System.out.println("Super class:");
		System.out.println("Values: "+A.getStaticInt()+" "+B.getStaticInt()+", "+
				a.getStaticInt()+" "+a.getFinalInt()+" "+a.getNormalInt());
		System.out.println(a);
		System.out.println("----------------------------");
		System.out.println("Child with super class type:");
		System.out.println("Values: "+A.getStaticInt()+" "+B.getStaticInt()+", "+
				a2.getStaticInt()+" "+a2.getFinalInt()+" "+a2.getNormalInt());
		System.out.println(a2);
		System.out.println("----------------------------");
		System.out.println("Child class:");
		System.out.println("Values: "+A.getStaticInt()+" "+B.getStaticInt()+", "+
				b.getStaticInt()+" "+b.getFinalInt()+" "+b.getNormalInt());
		System.out.println(b);
		
	}
}
