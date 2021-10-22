package com.bookapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.model.Book;

public class OrderDetails {
	IBookService bookService;
	Function<Integer,String> fun;

	public List<Book> showBooks(String author) throws BookNotFoundException {
		List<Book> bookByAuthor = bookService.getByAuthor(author);
		List<Book> finalBooks = new ArrayList<>();
		if(bookByAuthor!=null)
		{
		if(bookByAuthor.isEmpty())
		{
			throw new BookNotFoundException("book not found!!");
		}
		
	    finalBooks = bookByAuthor.stream().limit(3).collect(Collectors.toList());
		}
		return finalBooks;
	}
	
	public String orderBook(int bookid) throws BookNotFoundException {
		Book book = bookService.getById(bookid);
		System.out.println(book);
		if(book==null)
		{
			throw new BookNotFoundException("Book Not Available!!!");
				
		}
		return book.getTitle() + " ordered successfully";
		
		
	}

	public double getTotalOrdersByDiscount(double price, int discountedamount) throws BookNotFoundException {
		List<Book> bookList = bookService.getBookbyLessPrice(price);
		if(bookList.isEmpty())
		{
			throw new BookNotFoundException("author not found");
		}
		double totalamount =bookList.stream().mapToDouble(book->book.getPrice()-100).sum();
		/*
		 * for (Book book : bookList) { double newprice = book.getPrice() -
		 * discountedamount; totalamount += newprice;
		 */
		
					
		return totalamount;
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}
	
}
