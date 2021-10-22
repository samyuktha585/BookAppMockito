package com.acheron.training;

import java.util.Arrays;

import com.acheron.exception.InvalidException;

public class StudentDetails {
	public int totalMarks(int marks1, int marks2, int marks3) throws InvalidException {
		if (marks1 < 0 || marks2 < 0 || marks3 < 0)

			throw new InvalidException("number should be greater than 0");
		if (marks1 > 100 || marks2 > 100 || marks3 > 100)

			throw new InvalidException("number should be greater than 0");
		return marks1 + marks2 + marks3;

	}

	public String getGrades(int[] marks) {
		int total = 0;
		double average=0;
		if(marks!= null) {
		//System.out.println(marks[0]);
		Arrays.sort(marks);
		for (int mark : marks) {
			if(mark>100 || mark<00) 
				return "wrong values";
				if(mark<40)
					return "Fail";
				
			total += mark;
		}
		
		average = total / marks.length;
		}
		if (average >= 90 && average<=100) {
			return "A";
		} else if (average >= 80 && average <= 90) {
			return "B";
		} else if (average > 70 && average <=80) {
			return "C";
		} 
		else {
			return "D";
		}
	}
}
