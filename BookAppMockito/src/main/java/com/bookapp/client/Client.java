package com.bookapp.client;

import com.acheron.demos.Book;
import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.service.BookServiceImpl;
import com.bookapp.service.IBoookService;

public class Client {

	public static void main(String[] args) throws Exception {
		IBoookService bookService = new BookServiceImpl();

		Book book = new Book("Java", "Abc", "tech", 500);

		bookService.addBook(book);

		System.out.println("Book By Author");  

		try {
			bookService.getByAuthor("sam").forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Book By Category");

		try {
			bookService.getByCategory("tech").forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Book By Price");

		try {
			bookService.getByLessPrice(900).forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Book By Id");

		try {
			Book nbook = bookService.getById(1);
			System.out.println(nbook);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}

}
}