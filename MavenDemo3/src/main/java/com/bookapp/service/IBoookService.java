package com.bookapp.service;

import java.util.*;

import com.acheron.demos.Book;
import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdNotFoundException;


public interface IBoookService {
	Integer addBook(Book book);
	void updateBook(int bookid,double price);
    void deleteBook(int bookid);
    
    List<Book>getByAuthor(String author)throws BookNotFoundException;
    List<Book>getByCategory(String category)throws BookNotFoundException;
    List<Book>getByLessPrice(double price)throws BookNotFoundException;
    Book getById(int bookid)throws IdNotFoundException;
	
}
