package com.warouss.java;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new RegexTest().test1();
		System.out.println("");
		new RegexTest().test2();

	}
	
	private void test1() {
		System.out.println("START test1() -------------------------------------");
		String st = "4-125.20;684684-22.50;44565-1.00;846464-55.20";
		System.out.println(st.matches("^(\\d+-\\d+\\.?\\d\\d;)*(\\d+-\\d+\\.?\\d\\d)?$"));
		
		String st2 = " HD ";
		System.out.println(st2+" : "+st2.matches("^SD|HD|3D$"));
	}
	
	private void test2() {
		System.out.println("START test2() -------------------------------------");
		String exp = "\\b(static|public)\\b";
		String target = "static public staticpublic void main()";
		System.out.println("pattern=\""+exp+"\"");
		System.out.println("target=\""+target+"\"");
		Pattern p = Pattern.compile(exp);
		Matcher m = p.matcher(target);
 
		while(m.find()){
			System.out.println(m.group()+" : start="+m.start()+", end="+m.end());
		}
	}

}
