package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Book;

import java.util.List;

public interface BookRepository {

  List<Book> getAllBooks();

  void saveBook(Book account);

  Book getBookById(Integer id);

  void deleteBook(Integer id);

  void updatePrice(Book book);

  void updateQuantity(Book book);

  Book getBookByTitle(String title);

  List<Book> getAllBooksByAuthor(String author);
}
