package com.bookapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acheron.demos.Book;

public class BookRepositoryImpl implements IBookRepository{
	static Connection connection;
	Integer bookid=0;
	@Override
	public Integer addBook(Book book) {
		String insertQuery = "insert into book(title,author,category,price)values(?,?,?,?)";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			//statement.setInt(4, book.getBookId());
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setDouble(4, book.getPrice());
			statement.setString(3, book.getCategory());
			statement.execute();
			//get auto generated
			ResultSet rs = statement.getGeneratedKeys();
			while(rs.next())
			{
				bookid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			BookDAO.closeConnection();
		}
		return null;
	}

	@Override
	public void updateBook(int bookid, double price) {

		List<Book> getBooksbyId = new ArrayList<>();
		String sql = "update book set price = ? where id = ?";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		try {
			
			connection = BookDAO.openConnection();
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, price);
			statement.setInt(2, bookid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				Book book = new Book();
				book.setBookId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCategory(rs.getString(4));
				
				book.setPrice(rs.getDouble(5));
				getBooksbyId.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void deleteBook(int bookid) {
		int result = 0;
		String sql = "delete from book where bookid=?";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		int rs = 0;
		try {
			//String updsql = "update Book set price=? where bookId=?";
			connection = BookDAO.openConnection();
			statement = connection.prepareStatement(sql);
			//statement.setDouble(1, price);
			statement.setInt(1, bookid);
			rs = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BookDAO.closeConnection();
		}
		
	}

	@Override
	public List<Book> findByAuthor(String author) {
		List<Book> getBooksbyAuthor = new ArrayList<>();
		String sql = "select * from book where author=?";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		Book book = null;
		try {
			
			//connection = BookDAO.openConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, author);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				book = new Book();
				book.setBookId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCategory(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				getBooksbyAuthor.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getBooksbyAuthor;
	}

	@Override
	public List<Book> findByCategory(String category) {
		List<Book> booksbyCategory= new ArrayList<>();
		String sql = "select * from book where category=?";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		Book book =null;
		try {
			
			connection = BookDAO.openConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, category);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				 book = new Book();
				book.setBookId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCategory(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				booksbyCategory.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return booksbyCategory;
	}

	

	@Override
	public Book findById(int bookid) {
		Book book  = new Book();
		String sql = "select * from Book where bookid=?";
		Connection connection = BookDAO.openConnection();
		PreparedStatement statement = null;
		try {
			
			connection = BookDAO.openConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bookid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				book.setBookId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCategory(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public List<Book> findByLessPrice(double price) {
		Connection connection = BookDAO.openConnection();
		String selectQuery = "select * from book where price>=?";
		// prepared statemnet . set values and excute query
		List<Book> bookList = new ArrayList<>();
		PreparedStatement statement = null;
		Book book = null;
		try {
			statement = connection.prepareStatement(selectQuery);
			statement.setDouble(1, price);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				 book = new Book();
				book.setBookId(rs.getInt("bookid"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setCategory(rs.getString("category"));
				book.setPrice(rs.getDouble("price"));
				bookList.add(book);
			}
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			BookDAO.closeConnection();
		}

		return bookList;
	}

}
