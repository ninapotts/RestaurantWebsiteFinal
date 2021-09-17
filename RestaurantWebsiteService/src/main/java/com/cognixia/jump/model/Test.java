
//Author: Jakob Evans
//Date: Sept 13 2021

package com.cognixia.jump.model;

public class Test {
	
	
	
	
	public static void main (String [] args) {
		
		
		
		// I just want to say that was my first live coding 
		// review I've ever done.
		// That was the most embarrassing moment of my life for me.
		
		// When asked the question, I approached it without thinking of sorting
		// at all. The term "sorting" for some reason did not pop into my 
		// head when thinking about the problem, I believe this was due 
		// to first time interview anxiety.
		
		// so I decided to find the shortest string, print it and then
		// delete it out of the list. ( which would be an inefficient solution)
		
		//  Sadly, all you had to do was sort the array.
		
		// I didn't use any resources for this solution, but I know you don't
		// know that for sure.
		// I studied the complexity of all sort algorithms and have a 
		// pretty good understanding of when to use each of them.
		
		// I wish I could have a better excuse for what happened today, 
		// sadly all I can blame it on is freezing up. 
		

		
		
		String temp [] = {"hello", "hi" , "hdd", "gdddddd", "eaaa"}; 
		
		

		// simple bubble sort, which is pretty inefficient
		for(int i = 1; i < temp.length; i++) {
			
			for(int j = 0; j < temp.length; j++) {

				// check if the current is bigger than the next,
				// if it is then swap them
				if(temp[i].length() < temp[j].length()) {
					String temporary = temp[j];
					
					
					temp[j] = temp[i];
					
					temp[i] = temporary;	
				}
			}
			
			
		}
		
		for(int i = 0; i < temp.length; i++) {

			System.out.println(temp[i]);
		
		}
		
		
	

	
	}
}
		
		
	
		

			

	

	
	
	


