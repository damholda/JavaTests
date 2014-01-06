package test;

public class Threads {
	
	class A {
		public A() {
			// 
		}
		int a;
		
		public void setNumber(){
			a = 2;
		}
		public int getNumber(){
			return a;
		}
		public String print(){
			return "Class: "+this.getClass().getName()+" return a="+a;
		}
	}
	
	class B extends A{
		
	}
	
	class C extends A{
		
	}
	
//	public static void main(String[] args) {
//		Threads t = new Threads();
//		A a = t.new A();
//		B b = t.new B();
//		C c = t.new C();
//		
//		b = (B)a;
//	}

}
