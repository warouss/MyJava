package com.warouss.google.commons.hash;

import java.nio.charset.Charset;

import com.google.common.hash.Hashing;

public class HashTest {

	public static void main(String[] args) {
		String password = "passw0rd";
		System.out.println("MD5 : "+Hashing.md5().hashString(password, Charset.defaultCharset()).toString());
		System.out.println("SHA256 : "+Hashing.sha256().hashString(password, Charset.defaultCharset()).toString());
	}
}
