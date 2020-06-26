package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.reopository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Book> getAllBooks()
  {
    return bookRepository.getAllBooks ();
  }

  @Override
  public void saveBook(Book book)
  {
    bookRepository.saveBook (book);
  }

  @Override
  public Book getBookById(Integer id)
  {
    return bookRepository.getBookById (id);
  }

  @Override
  public void deleteBook(Integer id)
  {
    bookRepository.deleteBook (id);
  }

  @Override
  public void updatePrice(Book book)
  {
    bookRepository.updatePrice (book);
  }

  @Override
  public void updateQuantity(Book book)
  {
    bookRepository.updateQuantity (book);
  }

  @Override
  public Book getBookByTitle(String title)
  {
    return bookRepository.getBookByTitle (title);
  }

  @Override
  public List<Book> getAllBooksByAuthor(String author)
  {
    return bookRepository.getAllBooksByAuthor (author);
  }
}