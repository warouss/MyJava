package com.warouss.java;
/**
 * Si la Classe Pere à un constructeur A()
 * Tous les appels de constructeurs de B font appel implicitement au constructeur A() 
 *
 */
public class HeritageTest {

	public static void main(String[] args) {
		new HeritageTest().execute();
	}
	
	private void execute() {
		System.out.println("b1=new B() ");
		B b1=new B(); 
		System.out.println(">> b1.x="+b1.x);
		System.out.println("b2=new B(2003) ");
		B b2 =new B(2003); 
		System.out.println(">> b2.x="+b2.x);
		System.out.println("b3=new B(4000L) ");
		B b3 =new B(4000L); 
		System.out.println(">> b3.x="+b3.x);
		System.out.println("b4=new B(\"Bonjour\") ");
		B b4= new B("Bonjour");
		System.out.println(">> b4.x="+b4.x);
	}
	
	
	class A {
		public int x=0;
		public A() {
			System.out.println("\tENTRY A()");
			x=5;
		}
	}
	
	class B extends A {
		public B() {
			System.out.println("\tENTRY B()");
			x++;
		}
		
		public B(int i) {
			this();
			System.out.println("\tENTRY B("+i+") après this()");
			x=x+i;
		}
		
		public B(long l) {
			System.out.println("\tENTRY B("+l+")");
			x++;
			x=x+Long.valueOf(l).intValue();
		}
		
		public B(String s) {
			super();
			System.out.println("\tENTRY B("+s+") après super()");
			x--;
		}
	}
}
