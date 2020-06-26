package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Book;
import oracle.jdbc.OracleDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.testng.Assert.*;

public class BookRepositoryImplTest
{

  @Autowired
  BookRepository bookRepository;
  @BeforeMethod
  public void setUp()
  {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
    dataSource.setUrl ("jdbc:oracle:thin:@//83.228.124.173:6223/nadezhda_hristeva");
    dataSource.setUsername ("nadezhda_hristeva");
    dataSource.setPassword ("dbpass");
    dataSource.setDriverClass (OracleDriver.class);
    bookRepository = new BookRepositoryImpl (new JdbcTemplate (dataSource));
  }

  @Test
  public void testGetAllBooks()
  {
    List<Book> list = bookRepository.getAllBooks ();
    assertFalse (list.isEmpty ());
  }

  @Test
  public void testSaveBook()
  {
    Book book = new Book ();
    book.setAuthor ("Test Author");
    book.setPrice (20.20);
    book.setQuantity (10);
    book.setTitle ("Test Title");
    book.setYear (2000);
    book.setCurrency ("BGN");
    bookRepository.saveBook (book);
  }
  @Test
  public void testGetBookById()
  {
    Integer id = 1000;
    Book book = bookRepository.getBookById (id);
    assertNotNull (book);
  }

  @Test
  public void testDeleteBook()
  {
    Integer id = 1000;
    bookRepository.deleteBook (id);
  }
  @Test
  public void testUpdatePrice()
  {
    Integer id = 1020;
    Book book = bookRepository.getBookById (id);
    book.setPrice (30.00);
    bookRepository.updatePrice (book);
  }
  @Test
  public void testUpdateQuantity()
  {
    Integer id = 1020;
    Book book = bookRepository.getBookById (id);
    book.setQuantity (20);
    bookRepository.updateQuantity (book);
  }

  @Test
  public void testGetBookByTitle()
  {
    String title = "Test Title";
    Book book = bookRepository.getBookByTitle (title);
    assertNotNull (book);
  }
  @Test
  public void testGetBooksByAuthor()
  {
    String author = "Test Author";
    List<Book> books = bookRepository.getAllBooksByAuthor (author);
    assertNotNull (books);
  }
}