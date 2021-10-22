package com.bookapp.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.EmptyCartException;
import com.bookapp.model.Book;
import com.bookapp.service.CartDetails;
import com.bookapp.service.ICartService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

class CartTest {
	@Mock
	ICartService cartService;

	@InjectMocks
	CartDetails details;

	List<Book> bookList;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	Book book1, book2, book3, book4, book5;

	@BeforeEach
	void setUp() throws Exception {
		details = new CartDetails();
		details.setCartService(cartService);

		book1 = new Book("java", "kathy", 1900, 1);
		book2 = new Book("Spring", "kathy", 800, 2);
		book3 = new Book("javac", "kathy", 900, 3);
		book4 = new Book("Leadership", "kathy", 1800, 4);
		book5 = new Book("miracle", "Hal", 500, 5);
		bookList = Arrays.asList(book1, book2, book3, book4, book5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing with pay book empty")
	void testPayBooksEmpty() throws EmptyCartException {
		when(cartService.showCart()).thenThrow(EmptyCartException.class);
		assertThrows(EmptyCartException.class, () -> details.payBooksIncart());
	}

	@Test
	@DisplayName("Testing pay books")
	void testPayBooks() throws EmptyCartException {
		// shows a generic object return type,this when takes
		doReturn(Arrays.asList(book2, book3, book4, book5)).when(cartService).showCart();
		double totalamount = details.payBooksIncart();
		assertEquals(4000, totalamount, "not equal");
	}

	@Test
	@DisplayName("Testing Add to cart")
	void testAddToCart() throws BookNotFoundException {
		// use doNothing for void methods
		doNothing().when(cartService).addtoCart(book1);
		// testing the method in the class under test
		assertEquals("added successfully", details.addToCart(book1));
	}

	@Test
	@DisplayName("ADD cart Empty")
	void testAddCartEmpty() throws BookNotFoundException {
		// is used with void method that throw
		doThrow(BookNotFoundException.class).when(cartService).addtoCart(book1);
		// testing the method in the class under test
		assertEquals("book not add", details.addToCart(book1));
	}

	@Test
	@DisplayName("Remove from cart")
	void testRemoveCart() throws BookNotFoundException {
		when(cartService.removeFromCart(book1)).thenReturn(true);
		assertEquals("removed succesfully", details.removeFromCart(book1), "same");
	} 
	@Test
	@DisplayName("Testing Remove cart")
	void testFalseRemoveCart() throws BookNotFoundException {
		when(cartService.removeFromCart(book1)).thenReturn(false);
		assertEquals("book not removed", details.removeFromCart(book1), "same");
	}
	@Test
	@DisplayName("Testing Remove from cart .error")
	void testErrorRemoveCart() throws BookNotFoundException {
		when(cartService.removeFromCart(book1)).thenThrow(BookNotFoundException.class);
		assertThrows(BookNotFoundException.class,()->details.removeFromCart(book1));
	}

}
