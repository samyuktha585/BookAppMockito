package com.acheron.newtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.acheron.training.Calculator;

@RunWith(JUnitPlatform.class)
public class CalculatorTest1 {
	Calculator calc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("before all test");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("after all test cases");
	}

	@BeforeEach
	public void setUp() throws Exception {
		// creates a new instance for every test cases
		calc = new Calculator();
	}

	@AfterEach
	void tearDown() throws Exception {
		// make the object ready for gc
		calc = null;
	}
@Tag("greet")
	@Test
	@DisplayName("Testing sum")
	@Disabled //ignore the test cases
	void testSum() {
		System.out.println("inside the test case for sum");
		int actualResult = calc.sum(10, 20);
		assertEquals(30, actualResult, "not correct");

	}

	@Test
	@DisplayName("Testing product")
	public void testMultiply() {
		long expectedResult = 250;
		long result = calc.multiply(10, 25);
		assertEquals(expectedResult, result);
	}
	@Test
	void testGreet()
	{
		assertEquals("WELCOME PRIYA",calc.greet("priya"),"not same");
	}

}
