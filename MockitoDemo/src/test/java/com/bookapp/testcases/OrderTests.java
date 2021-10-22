package com.bookapp.testcases;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.service.IBookService;
import com.bookapp.service.OrderDetails;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class OrderTests {
	@Mock
	IBookService bookService; // creating mock object
	@InjectMocks
	OrderDetails orderDetails; // =new OrderDeatails();
	List<Book> bookList;
	Book book1, book2, book3, book4, book5; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderDetails = new OrderDetails();
		orderDetails.setBookService(bookService);
		 book1 = new Book("Java", "Kathy", 900, 1);
		 book2 = new Book("Spring", "Kathy", 800, 2);
		 book3 = new Book("Leadership", "Kathy", 1800, 3);
		 book4 = new Book("Java", "Candy", 500, 4);
		 book5 = new Book("Miracle", "Hal", 800, 5);
		bookList = Arrays.asList(book1, book2, book3, book4, book5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@DisplayName("testing with proper list")
	@Test
	void testShowBooks() throws BookNotFoundException {
		when(bookService.getByAuthor("Kathy")).thenReturn(Arrays.asList(book5));
		// Mockito.when(bookService.getByAuthor("Kathy")).thenReturn(Arrays.asList(book1,book2));
		List<Book> bookByAuthor = orderDetails.showBooks("Kathy");
		assertEquals(1, bookByAuthor.size(), "not equal");

	}
	@DisplayName("testing with empty list")
	@Test
	void testEmptyBooks() throws BookNotFoundException {
		String author = "priya";
		when(bookService.getByAuthor(author)).thenReturn(new ArrayList<Book>());
		// List<Book> booksByAuthor = details.showBooks(author);
		assertThrows(BookNotFoundException.class, () -> orderDetails.showBooks(author));

	}
	@DisplayName("testing with null list")
	@Test
	void testNullBooks() {
		String author="priya";
		when(bookService.getByAuthor(author)).thenReturn(null);
		assertDoesNotThrow(()->orderDetails.showBooks(author));

	}
	@Test
	@DisplayName("Testing with Empty List")
	void testNullTEmptyBooks() throws BookNotFoundException {
		String author = "Priya";
		Mockito.when(bookService.getByAuthor(author)).thenReturn(null);
		assertEquals(new ArrayList<>(), orderDetails.showBooks(author), "List Not Empty");
	}
	
	
	@DisplayName("filter books")
	@Test
	void testLimitBooks() throws BookNotFoundException {
		String author = "Kathy";
		when(bookService.getByAuthor(author)).thenReturn(Arrays.asList(book1, book2));
		List<Book> bookList = orderDetails.showBooks(author);
		assertEquals(2, bookList.size(), "not equal");

	}
	@Test
	@DisplayName("book availability")
	void testBookPresent() throws BookNotFoundException {
		int bookid=1;
		when(bookService.getById(bookid)).thenReturn(book1);
		String status = orderDetails.orderBook(bookid);
		assertEquals("Java ordered successfully",status,"not equal");
	}
	@Test
	@DisplayName("not present")
	void testBookNotPresent() {
		int bookid=10;
		when(bookService.getById(bookid)).thenReturn(null);
		assertThrows(BookNotFoundException.class, () -> orderDetails.orderBook(bookid));
	}
	@DisplayName("test discount")
	@Test
	public void testDiscount() throws BookNotFoundException {
		double price = 9000;
		int discountamount = 100;
		Mockito.when(bookService.getBookbyLessPrice(price)).thenReturn(Arrays.asList(book3));
		assertEquals(1700, orderDetails.getTotalOrdersByDiscount(price, discountamount), "not correct");
		
	}

	
}
