package com.bookapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.acheron.demos.Book;
import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.repository.BookDAO;
import com.bookapp.repository.BookRepositoryImpl;
import com.bookapp.repository.IBookRepository;

public class BookServiceImpl implements IBoookService {
	IBookRepository bookRepository = new BookRepositoryImpl();
	@Override
	public Integer addBook(Book book) {
		Integer bookid = bookRepository.addBook(book);
		return bookid;
	}

	@Override
	public void updateBook(int bookid, double price) {
	
		bookRepository.updateBook(bookid, price);
	}

	@Override
	public void deleteBook(int bookid) {
		bookRepository.deleteBook(bookid);
		
	}

	@Override
	public List<Book> getByAuthor(String author) {
		List<Book> bookByAuthor = bookRepository.findByAuthor(author);
		if(bookByAuthor.isEmpty())
		throw  new BookNotFoundException("author not found");
		return bookByAuthor.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}

	@Override
	public List<Book> getByCategory(String category) {
		List<Book> bookByCategory = bookRepository.findByCategory(category);
		if(bookByCategory.isEmpty())
		throw  new BookNotFoundException("category not found");
		return bookByCategory.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}	
	@Override
	public List<Book> getByLessPrice(double price) {
		List<Book> bookByPrice = bookRepository.findByLessPrice(price);
		if(bookByPrice.isEmpty())
			throw  new BookNotFoundException("Not available in this price...");
		return bookByPrice.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}

	@Override
	public Book getById(int bookid) {
		Book book = bookRepository.findById(bookid);
		if(book==null)
		throw  new BookNotFoundException("id not found");
	return book;
	}

	

}
