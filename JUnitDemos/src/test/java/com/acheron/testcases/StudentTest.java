package com.acheron.testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.acheron.exception.InvalidException;
import com.acheron.training.StudentDetails;

class StudentTest {
	StudentDetails studentDetails;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		studentDetails = new StudentDetails();
	}

	@AfterEach
	void tearDown() throws Exception {
		studentDetails = null;
	}

	@Test
	@DisplayName("Testing positive marks")
	void testTotalMarks() throws InvalidException {
		assertEquals(250,studentDetails.totalMarks(90,80,80),"not correct");
		
	}
	@Test
	@DisplayName("Testing negitive marks")
	void testNegMarks()
	{
		assertThrows(InvalidException.class,()->
		{
			studentDetails.totalMarks(90,-82,45);
		});
	}
	@Test
	@DisplayName("Testing negitive marks")
	void testGreaterMarks()
	{
		assertThrows(InvalidException.class,()->
		{
			studentDetails.totalMarks(190,182,45);
		});
	}
	@Test
	void testAgrade()
	{
		int[] marks = new int[] {98,93,90};
		assertEquals("A",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	void testBgrade()
	{
		int[] marks = new int[] {80,82,80};
		assertEquals("B",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	void testCgrade()
	{
		int[] marks = new int[] {80,76,70};
		assertEquals("C",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	void testDgrade()
	{
		int[] marks = new int[] {60,40,45};
		assertEquals("D",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	@DisplayName("testing fail marks")
	void testFail()
	{
		int[] marks = new int[] {31,45,50};
		assertEquals("Fail",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	@DisplayName("Testing greater marks")
	void testGreatergrade()
	{
		int[] marks = new int[] {101,50,50};
		assertEquals("wrong values",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	@DisplayName("Testing wrong values")
	void testWrongGrade()
	{
		int[] marks = new int[] {15,-50,50};
		assertEquals("wrong values",studentDetails.getGrades(marks),"not equal");
	}

	@Test
	@DisplayName("Testing negitive marks")
	void testNegGrade()
	{
		int[] marks = new int[] {-90,50,50};
		assertEquals("wrong values",studentDetails.getGrades(marks),"not equal");
	}
	@Test
	@DisplayName("Testing negitive grade")
	void testNegitiveGrade()
	{
		int[] marks = new int[] {-90,50,50};
		assertDoesNotThrow(()->studentDetails.getGrades(marks),"negitive grade");
	}
	@Test
	@DisplayName("Testing not null")
	void testNull()
	{
		int[] marks = null;
		//assertNotNull(studentDetails.getGrades(marks));
		//assertThrows(NullPointerException.class,()->studentDetails.getGrades(marks));
		//studentDetails.getGrades(marks);
		assertDoesNotThrow(()->studentDetails.getGrades(marks),"test zero");
	}
	
	@Test
	@DisplayName("Testing not null")
	void testNotNull()
	{
		int[] marks = null;
		//assertNotNull(studentDetails.getGrades(marks));
		//assertThrows(NullPointerException.class,()->studentDetails.getGrades(marks));
		//studentDetails.getGrades(marks);
		assertDoesNotThrow(()->studentDetails.getGrades(marks),"test zero");
	}
	@Test
	@DisplayName("Testing zero grade")
	void testZeroGrade()
	{
		int[] marks = new int[] {0,100};
		assertDoesNotThrow(()->studentDetails.getGrades(marks),"negitive grade");
	}
	
	
	
}
