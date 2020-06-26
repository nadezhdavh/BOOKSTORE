package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Book;

import java.util.List;


public interface BookService
{
  List<Book> getAllBooks();

  void saveBook(Book book);

  Book getBookById(Integer id);

  void deleteBook(Integer id);

  void updatePrice(Book book);

  void updateQuantity(Book book);

  Book getBookByTitle(String title);

  List<Book> getAllBooksByAuthor(String author);
}
