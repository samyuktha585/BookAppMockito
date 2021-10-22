package com.bookapp.service;

import java.util.List;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.EmptyCartException;
import com.bookapp.model.Book;

public class CartDetails {
	ICartService cartService;
	 public void setCartService(ICartService cartService) {
		 this.cartService=cartService;
		 
	 }
	 //not handling exception,thrown to calling class use asserThrows
	 public double payBooksIncart() throws EmptyCartException{
		 List<Book> bookList =cartService.showCart();
		 double totalamount =bookList.stream().mapToDouble(book->book.getPrice()).sum();
		 return totalamount;
	 }
	 
	 //handling the exception using try catch,so test using assertEqual or any other method
	 //nothing,exception
	 public String addToCart(Book book) {
		 try {
		 cartService.addtoCart(book);
		 return "added successfully";
		 }
		 catch(BookNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		 return"book not add";
	 }
	//true,exception 
	 public String removeFromCart(Book book) throws BookNotFoundException {
		 boolean result =cartService.removeFromCart(book);
		 if(result)
		 return "removed succesfully";
		 else
			 return "book not removed";
	 }
	 public String greet(String name)
	 {
		 return "welcome" +name;
	 }

}
