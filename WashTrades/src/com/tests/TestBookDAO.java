package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.BookDAO;
import com.dao.BookDAOImpl;
import com.pojos.Book;

class TestBookDAO {

	@Test
	void testAddBook() {
		BookDAOImpl dao=new BookDAOImpl();//create an object of the class to be tested
		Book book=new Book(9991,"name","author");
		int r=dao.addBook(book);
		
		assertEquals(1, r); 
		}
	
	@Test
	public void testFindAllBooks() {
		BookDAOImpl dao=new BookDAOImpl();
		List<Book> list=dao.findAllBooks();
		//minimum three assertions;
		Book actual=list.get(4);
		assertEquals(102, actual.getIsbn());
		assertEquals("name3",actual.getName());
		assertEquals("auth1243",actual.getAuthor());
		}
	
	@Test
	public void testFindAllByAuthor() {
		BookDAO dao=new BookDAOImpl();
		List<Book> list=dao.findAllByAuthor("auth1");
		assertEquals(6, list.size());
	}
	
	@Test
	public void testFindAllByAuthor_negative() {
		BookDAOImpl dao=new BookDAOImpl();
		List<Book> list=dao.findAllByAuthor("notexistslol");
		assertEquals(0, list.size());
	}
	
	@Test
	public void testFindAllByName() {
		BookDAOImpl dao=new BookDAOImpl();
		List<Book> list=dao.findAllByName("name1");
		assertEquals(13, list.size());
		}
	
	@Test
	public void testFindAllByName_negative() {
		BookDAOImpl dao=new BookDAOImpl();
		List<Book> list=dao.findAllByName("reddy");
		assertEquals(0, list.size());
		}
	
	@Test
	public void testGetByIsbn() {
		BookDAOImpl dao=new BookDAOImpl();
		Book book=dao.getBOOkISBN(550);
		assertEquals("name abc", book.getName());
		
	}
	
	@Test
	public void testGetByIsbn_negative() {
		BookDAOImpl dao=new BookDAOImpl();
		Book book=dao.getBOOkISBN(789);
		assertEquals(null, book);
		
	}
	
	@Test
	public void testUpdateBook() {
		BookDAOImpl dao=new BookDAOImpl();
		assertEquals(true,dao.updateBook(550,"harshith"));
		assertEquals("harshith",dao.getBOOkISBN(550).getAuthor());
		
	}
	
	@Test
	public void testUpdateBook_negative() {
		BookDAOImpl dao=new BookDAOImpl();
		assertEquals(false,dao.updateBook(444,"harshith"));
		
	}
	
	@Test
	public void testDeleteByIsbn() {
		BookDAOImpl dao=new BookDAOImpl();
		assertEquals(9991,dao.deleteByIsbn(9991).getIsbn());
		assertEquals(null,dao.getBOOkISBN(9991));
		
	}
	
	@Test
	public void testDeleteByIsbn_negative() {
		BookDAOImpl dao=new BookDAOImpl();
		assertEquals(null,dao.deleteByIsbn(9992));
		
	}
	
	

}
