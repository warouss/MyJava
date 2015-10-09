package com.warouss.apache.commons.lang;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class LangTest {

	public static void main(String[] args) {
		 new LangTest().testValidate();

		// new LangTest().testBoolean();

		// new LangTest().testSystem();

//		new LangTest().testOverrideMethods();
		
//		new LangTest().testRandomString();
		
//		new LangTest().testException();

	}
	

	private void testValidate() {
		final String methodName = "testBuildDate";
		start(methodName);

		Integer i = 10;

		try {
			Validate.inclusiveBetween(10, 20, i, "%d is not inclusiveBetween",
					i);
			out(i + " is inclusiveBetween");
		} catch (Exception e) {
			err(e);
		}

		try {
			Validate.exclusiveBetween(10, 20, i, "%d is not inclusiveBetween",
					i);
			out(i + " is exclusiveBetween");
		} catch (Exception e) {
			err(e);
		}

		try {
			Range r = Range.between(10, 20);
			Validate.isTrue(r.contains(30), "%d is not in %s", i, r.toString());
			out(String.format("%d in %s", i, r.toString()));
		} catch (Exception e) {
			err(e);
		}

		end(methodName);
	}

	private void testBoolean() {
		final String methodName = "testBoolean";
		start(methodName);

		out(BooleanUtils.toStringTrueFalse(true));
		out(BooleanUtils.toStringOnOff(false));
		out(BooleanUtils.toStringYesNo(true));
		out(BooleanUtils.toInteger(true));
		out(BooleanUtils.toBoolean(0));
		out(BooleanUtils.toBoolean(1));
		out(BooleanUtils.toBoolean("true"));
		out(BooleanUtils.toBoolean("off"));
		end(methodName);
	}

	private void testSystem() {
		final String methodName = "testSystem";
		start(methodName);
		out("JavaHome=" + SystemUtils.getJavaHome());
		out("JavaIoTmpDir=" + SystemUtils.getJavaIoTmpDir());
		out("UserDir=" + SystemUtils.getUserDir());
		out("UserHome=" + SystemUtils.getUserHome());
		end(methodName);
	}

	private void testOverrideMethods() {
		final String methodName = "testToString";
		start(methodName);
		Product p1 = new Product(10005L, "Product01", 10.99);
		out("Product.toString : "+p1);
		out("Product.hashCode : "+p1.hashCode());
		Product p2 = new Product(10005L, "Product02", 20.99);
		out("Product.equals : "+p1.equals(p2));
		
		Item i1 = new Item(10005L, "Item01", 10.99);
		out("Item.toString : "+i1);
		out("Item.hashCode : "+i1.hashCode());
		Item i2 = new Item(10005L, "Item01", 10.99);
		out("Item.equals : "+i1.equals(i2));
		
		end(methodName);
	}

	private void testRandomString() {
		final String methodName = "testRandom";
		start(methodName);
		out("Alphabetic   : "+RandomStringUtils.randomAlphabetic(10));
		out("Numeric      : "+RandomStringUtils.randomNumeric(10));
		out("Alphanumeric : "+RandomStringUtils.randomAlphanumeric(10));
		out("Ascii        : "+RandomStringUtils.randomAscii(10));
		end(methodName);
	}
	
	private void testException() {
		final String methodName = "testException";
		start(methodName);
		try {
			a();
		} catch (Exception e) {
			List<Throwable> list = ExceptionUtils.getThrowableList(e);
			for (Throwable t : list) {
				System.err.println(t.getClass()+" : "+t.getMessage());
			}
		}
		end(methodName);
	}
	
	private void a() throws Exception {
		try {
			b();
		} catch (Exception e) {
			throw new Exception("Global exception", e);
		}
	}
	
	private void b() throws IllegalArgumentException {
		try {
			c();
		} catch (Exception e) {
			throw new IllegalArgumentException("Argument exception", e);
		}
	}
	
	private void c() {
		throw new NullPointerException("The root exception");
	}

	// **********************************************************

	private void start(String methodeName) {
		System.out.println("Début " + methodeName);
	}

	private void end(String methodeName) {
		System.out.println("Fin " + methodeName);
	}

	private void out(Object msg) {
		System.out.println("\t" + msg);
	}

	private void err(Exception e) {
		System.err.println("\t" + e.getMessage());
	}

}
