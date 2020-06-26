package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Comment;
import bg.unwe.BOOKSTORE.model.User;
import oracle.jdbc.OracleDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.*;

public class CommentRepositoryImplTest
{


  @Autowired
  CommentRepository commentRepository;
  @BeforeMethod
  public void setUp()
  {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
    dataSource.setUrl ("jdbc:oracle:thin:@//83.228.124.173:6223/nadezhda_hristeva");
    dataSource.setUsername ("nadezhda_hristeva");
    dataSource.setPassword ("dbpass");
    dataSource.setDriverClass (OracleDriver.class);
    commentRepository = new CommentRepositoryImpl (new JdbcTemplate (dataSource));
  }
  @Test
  public void testGetCommentById()
  {
    Integer id = 100;
    Comment comment = commentRepository.getCommentById (id);
    assertNotNull (comment);
  }

  @Test
  public void testSaveComment()
  {
    Book book = new Book ();
    book.setId (1200);
    User user = new User();
    user.setId (123);
    Comment comment = new Comment ();
    comment.setContent ("Test Content");
    comment.setLocalDateTime (LocalDateTime.now ());
    comment.setBook (book);
    comment.setUser (user);
    commentRepository.saveComment (comment);
  }

  @Test
  public void testDeleteComment()
  {
    Integer id = 100;
    commentRepository.deleteComment (id);
  }

  @Test
  public void testGetAllCommentsByBookId()
  {
    Book book = new Book();
    book.setId (1200);
    List<Comment> commentList = commentRepository.getAllCommentsByBookId (book.getId ());
    assertNotNull (commentList);
  }
}