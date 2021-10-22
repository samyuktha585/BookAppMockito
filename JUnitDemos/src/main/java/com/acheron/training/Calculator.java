package com.acheron.training;

public class Calculator {
	public int sum(int x,int y)
	{
		System.out.println("in add method");
		return x+y;
	}
	public int multiply(int x,int y)
	{
		System.out.println("in product method");
		return x*y;
	}
	public String greet(String name)
	{
		return ("welcome "+name).toUpperCase();
		//return "Welcome ".toUpperCase()+name.toUpperCase();
	}
	

}
