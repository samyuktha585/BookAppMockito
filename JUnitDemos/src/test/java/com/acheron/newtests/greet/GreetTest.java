package com.acheron.newtests.greet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
@Tag("greeter")
class GreetTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing message")
	void testNull() {
		List<String> mylist = null;
		assertNull(mylist);
	}
	@Test
	@DisplayName("Testing not null")
	void testNotNull() {
		List<String> mylist = new ArrayList<>();
		assertNotNull(mylist); //test case will be success
		//assertNull(mylist);//test case will fail
	}
	@Test
	@DisplayName("Testing string")
	void testSame() {
		String username = "priya";
		//String nusername = new String("priya"); //string reference created in memeory pool
		String nusername = "priya";
		assertSame(nusername,"priya","references are not same");
	}
	@Test
	@DisplayName("Testing not null")
	void testArray() {
		int[] arr1 = {10,20};
		int[] arr2 = {10,20};
		assertArrayEquals(arr1,arr2,"both are not equal");
	}
	@Test
	@DisplayName("Testing not null")
	void testEquality() {
		String username = "priya";
		assertEquals(username,"priya","same literal value");

	}


}
