package com.fdmgroup.lambda;

public class Client {

	public static void main(String[] args) {
		//ICab cab1 = () -> System.out.println("From lambda"); //reference to function
		//cab1.bookCab(); // didn't have to instantiate a Cab object to access the method.
		//ICab cab1 = (x) -> System.out.println("From lambda " + x);
		//cab1.bookCab(5);
		
		ICab cab1 = (x) -> x;
		System.out.println(cab1.bookCab(5));
		
	}

}
