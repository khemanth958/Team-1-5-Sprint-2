package Util;

import java.security.MessageDigest;

public class PasswordUtil {
	public static String hashPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {

		}
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);
		for (byte b : mdArray) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
	
	
	public static void main(String args[])
	{
		
		String s="123";
		String a=hashPassword(s);
		System.out.println(a);
		String x=hashPassword(s);
		
		if(x.equals(a))
		{
			System.out.println("suki");
		}
			
	}
}
