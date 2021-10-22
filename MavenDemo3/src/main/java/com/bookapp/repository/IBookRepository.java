package com.bookapp.repository;

import java.util.List;

import com.acheron.demos.Book;

public interface IBookRepository {
	Integer addBook(Book book);
	void updateBook(int bookid,double price);
    void deleteBook(int bookid);
    
    List<Book>findByAuthor(String author);
    List<Book>findByCategory(String category);
    List<Book>findByLessPrice(double price);
    Book findById(int bookid);

}
