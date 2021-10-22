package com.bookapp.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.acheron.demos.Book;
import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.InvalidDataException;
import com.bookapp.repository.IBookRepository;
import com.bookapp.service.BookServiceImpl;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	@Mock
	IBookRepository repository;
	@InjectMocks
	BookServiceImpl bookService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	List<Book> bookList;
	Book book1, book2, book3, book4, book5;

	@BeforeEach
	void setUp() throws Exception {
		bookService = new BookServiceImpl();
		bookService.setBookRepository(repository);
		book1 = new Book("Java", "Kathy", "Tech", 900);
		book2 = new Book("Spring", "Kathy", "Tech", 800);
		book3 = new Book("Leadership", "sam", "kids", 900);
		book4 = new Book("scare crow", "Candy", "Self", 500);
		book5 = new Book("Miracle", "Kathy", "Self", 800);
		bookList = Arrays.asList(book1, book2, book3, book4, book5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("test add book")
	@Test
	void testAddBook() throws Exception {
		// to call the method of the mock class
		when(repository.addBook(book1)).thenReturn(1);

		// call the method to be tested
		assertEquals(1, bookService.addBook(book1), "not equal");

	}

	@DisplayName("test add book error")
	@Test
	void testErrorAddBook() throws Exception {
//to call the method of the mock class
		when(repository.addBook(book1)).thenThrow(Exception.class);
		assertThrows(InvalidDataException.class, () -> bookService.addBook(book1));

	}
	@DisplayName("test update book ") 
	@Test
	void testUpdateBook() {
		doNothing().when(repository).updateBook(1,900);
		assertEquals("updated",bookService.updateBook(1, 900));
	}
	
	@DisplayName("test update book error ")
	@Test
	void testUpdateErrorBook() {
		//doNothing().when(repository).updateBook(1,900); this line 
		assertEquals("invalid data",bookService.updateBook(-1, 900),"not same");
	}
	
	@DisplayName("test delete book ")
	@Test
	void testDeleteBook() {
		doNothing().when(repository).deleteBook(1);
		assertTrue(bookService.deleteBook(1));
	}
	@DisplayName("test invalid delete book ")
	@Test
	void testInvalidDeleteBook() {
		//doNothing().when(repository).deleteBook(1);
		assertFalse(bookService.deleteBook(-1));
	}
	@DisplayName("test author ")
	@Test
	void testGetByAuthor() {
		String author = "Kathy";
		when(repository.findByAuthor(author)).thenReturn(Arrays.asList(book5,book2,book1));
		assertEquals(3,bookService.getByAuthor(author).size());
		List<Book> dummyBook = Arrays.asList(book1,book5,book2);
		assertEquals(dummyBook,bookService.getByAuthor(author));
	}
	@DisplayName("test author empty ")
	@Test
	void testEmptyGetByAuthor() {
		String author = "Kathy";
		when(repository.findByAuthor(author)).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class,()->bookService.getByAuthor(author));
	}
	@DisplayName("test category ")
	@Test
	void testGetByCategory() {
		String category = "Tech";
		when(repository.findByCategory(category)).thenReturn(Arrays.asList(book5,book2,book1));
		assertEquals(3,bookService.getByCategory(category).size());
		List<Book> dummyBook = Arrays.asList(book1,book5,book2);
		assertEquals(dummyBook,bookService.getByCategory(category));
	}
	
	@DisplayName("test category empty ")
	@Test
	void testEmptyGetByCategory() {
		String category = "Tech";
		when(repository.findByCategory(category)).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class,()->bookService.getByCategory(category));
	}
	@DisplayName("test Less Price ")
	@Test
 		void testGetByLessPrice() throws BookNotFoundException {
			double price=900;
			when(repository.findByLessPrice(price)).thenReturn(Arrays.asList(book1,book3));
			assertEquals(2,bookService.getByLessPrice(price).size());
			List<Book> dummybook = Arrays.asList(book1,book3);
			assertEquals(dummybook,bookService.getByLessPrice(price));
		}
	@DisplayName("test Less Price ")
	@Test
 		void testEmptyByLessPrice() throws BookNotFoundException {
			double price=900;
			when(repository.findByLessPrice(price)).thenReturn(new ArrayList<Book>());
			assertThrows(BookNotFoundException.class,()->bookService.getByLessPrice(price));
		}
 
	@DisplayName("test id ")
	@Test
	void testGetById() {
	int bookid=1;
	when(repository.findById(bookid)).thenReturn(book1);
	assertEquals(book1,bookService.getById(bookid));
}
	@DisplayName("test id empty ") 
	@Test
	void testEmptyGetById() {
	int bookid=10;
	when(repository.findById(bookid)).thenReturn(null);
	assertThrows(BookNotFoundException.class,()->bookService.getById(bookid));
	
}
}