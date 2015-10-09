package com.warouss.apache.commons.codec;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class CodecTest {

	public static void main(String[] args) {
		new CodecTest().testString();
		
		

	}
	
	private void testString() {
		String motDePass = "passw0rd";
		System.out.println("md5(default) : "+new String(DigestUtils.md5(motDePass)));
		System.out.println("md5(ASCII)   : "+StringUtils.newStringUsAscii(DigestUtils.md5(motDePass)));
		System.out.println("md5(UTF8)    : "+StringUtils.newStringUtf8(DigestUtils.md5(motDePass)));
		System.out.println("md5Hex       : "+DigestUtils.md5Hex(motDePass));
		System.out.println("-----------------");
		System.out.println("sha1(default) : "+new String(DigestUtils.sha1(motDePass)));
		System.out.println("sha1(ASCII)   : "+StringUtils.newStringUsAscii(DigestUtils.sha1(motDePass)));
		System.out.println("sha1(UTF8)    : "+StringUtils.newStringUtf8(DigestUtils.sha1(motDePass)));
		System.out.println("sha1Hex       : "+DigestUtils.sha1Hex(motDePass));
	}
	
	private void testFile() {
		
	}

}
