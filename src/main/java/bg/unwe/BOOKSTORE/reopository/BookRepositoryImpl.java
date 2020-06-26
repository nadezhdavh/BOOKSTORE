package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository
{
  @Autowired
  private JdbcTemplate template;

  public BookRepositoryImpl(JdbcTemplate jdbcTemplate)
  {
    this.template = jdbcTemplate;
  }

  @Override
  public List<Book> getAllBooks()
  {
    String sql = "SELECT * FROM BOOKS";
    List<Book> books = template.query (sql, BeanPropertyRowMapper.newInstance (Book.class));
    return books;
  }

  @Override
  public void saveBook(Book book)
  {
   String sql = "INSERT INTO BOOKS (id, author, currency, price, quantity, title, year) VALUES (BOOK_SEQ.nextval, ?, ?, ?, ?, ?, ?)";
   template.update (sql, book.getAuthor (), book.getCurrency (), book.getPrice (), book.getQuantity (), book.getTitle (), book.getYear ());
  }

  @Override
  public Book getBookById(Integer id)
  {
    String sql = "SELECT * FROM BOOKS WHERE ID = ?";
    Object[] args = {id};
    Book book = template.queryForObject (sql, args, BeanPropertyRowMapper.newInstance (Book.class));
    return book;
  }

  @Override
  public void deleteBook(Integer id)
  {
    String sql = "DELETE FROM BOOKS WHERE ID = ?";
    template.update (sql, id);
  }

  @Override
  public void updatePrice(Book book)
  {
    String sql = "UPDATE BOOKS SET price=:price WHERE id=:id";
    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(book);
    NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate (template);
    named.update (sql,param);
  }

  @Override
  public void updateQuantity(Book book)
  {
    String sql = "UPDATE BOOKS SET quantity=:quantity WHERE id=:id";
    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(book);
    NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate (template);
    named.update (sql,param);
  }

  @Override
  public Book getBookByTitle(String title)
  {
    String sql = "SELECT * FROM BOOKS WHERE TITLE = ?";
    Object[] args = {title};
    Book book = template.queryForObject (sql, args, BeanPropertyRowMapper.newInstance (Book.class));
    return book;
  }

  @Override
  public List<Book> getAllBooksByAuthor(String author)
  {
    String sql = "SELECT * FROM BOOKS WHERE AUTHOR = ?";
    Object[] args = {author};
    List<Book> books = template.query(sql, args, BeanPropertyRowMapper.newInstance (Book.class));
    return books;
  }
}
