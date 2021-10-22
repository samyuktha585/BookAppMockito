package com.bookapp.service;

import java.util.List;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.EmptyCartException;
import com.bookapp.model.Book;

public interface ICartService {
	List<Book> showCart() throws EmptyCartException; //empty,null,list,exception
	void addtoCart(Book book)  throws BookNotFoundException;
	boolean removeFromCart(Book book) throws BookNotFoundException;
	List<Book> showCart1();
		

}
