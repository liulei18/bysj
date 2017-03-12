package com.edu.zzti.ass.core.util;

import java.util.UUID;

public class UuidHelper {
	public static String getUuid(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-","");
		return uuid;
		
	}
	public static void main(String[] args) {
		System.out.println(getUuid());
		System.out.println(getUuid().length());
	}

}
