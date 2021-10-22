package com.bookapp.service;
import com.bookapp.model.Book;
import java.util.List;

public interface IBookService {
	List<Book> getByAuthor(String author);
	List<Book> getBookbyLessPrice(double price);
	Book getById(int bookid);
	

}
