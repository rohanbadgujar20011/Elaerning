package com.elearning.util;

public class PrivateKey {
	static String privateKey;
	public static String privateKey(String email)
	{
		String name=email.substring(0, email.lastIndexOf('@'));
		
		 int randomPIN = (int)(Math.random()*9000)+1000;

	        //Store integer in a string
        		
 		System.out.println(name);
 		
 		privateKey= name+Integer.toString(randomPIN);
		
 		
 		
 		
		return privateKey;
	}
	 public static void main(String[] args) {
		 privateKey("asas@fdfd");
	}
}
