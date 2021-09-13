package com.cognixia.jump.model;

import java.util.Arrays;
import java.util.Iterator;

public class Test {
	
	
	
	
	public static void main (String [] args) {
		
		int intArray[] = {0, 1 , 2, 3, 4};
		
		String stringArray[] = {"Hi"};  
		
		System.out.println("Here");
		System.out.println(Arrays.toString(stringArray));
		
//		int array[] = new int[5];
		
		
		intArray[0] = 5;
		
		
		// if u want to change things
		
		for(int i = 0; i < intArray.length; i++) {
			
			intArray[i] = i;
			
		}
		
		System.out.println("changing array indices ");
		
		
		for(int i = 0; i < intArray.length; i++) {
			
			
		}
		
		
		
		System.out.println("\n\nString pool and equals example ");

		String str="Good";
		str=str+" Morning";
		
		System.out.println(str);
		
		
		
		String a = "a";
		String b = "a";
		System.out.println("a = 'a', b = 'a");

		System.out.println("a == b");
		System.out.println(a == b);
		
		System.out.println();

		String c = new String("a");
		System.out.println("a = 'a', new c = 'a");

		System.out.println("a == c");
		System.out.println(a == c);
		System.out.println();
		
		System.out.println("a.equals(c)");
		System.out.println(a.equals(c));
		

		
		

//		
//		for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]);
//
//		}

	}
	
	

}
