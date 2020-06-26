package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository
{
  @Autowired
  private JdbcTemplate template;
  @Autowired
  UserRepository userRepository;
  @Autowired
  BookRepository bookRepository;
  public CommentRepositoryImpl(JdbcTemplate jdbcTemplate)
  {
    this.template = jdbcTemplate;
  }

  @Override
  public Comment getCommentById(Integer id)
  {
    String sql = "SELECT * FROM COMMENTS WHERE ID = ?";
    Object[] args = {id};
    Comment comment = template.queryForObject (sql, args, BeanPropertyRowMapper.newInstance (Comment.class));
    return comment;
  }

  @Override
  public void saveComment(Comment comment)
  {
    String sql = "INSERT INTO COMMENTS (id, content, time, books_id, users_id) VALUES (COMMENT_SEQ.nextval, ?, ?, ?, ?)";
    template.update (sql, comment.getContent (), Timestamp.valueOf(comment.getLocalDateTime ()), comment.getBook ().getId (), comment.getUser ().getId ());
  }

  @Override
  public void deleteComment(Integer id)
  {
    String sql = "DELETE FROM COMMENTS WHERE ID = ?";
    template.update (sql, id);
  }

  @Override
  public List<Comment> getAllCommentsByBookId(Integer bookId)
  {
    Object[] args = {bookId};
    String sql = "SELECT * FROM COMMENTS C\n" +
        "INNER JOIN BOOKS B\n" +
        "ON c.books_id = b.id\n" +
        "WHERE b.id = ?";
    return template.query (sql, args, (resultSet, i) ->
        new Comment (
            resultSet.getInt ("id"),
            resultSet.getString ("content"),
            resultSet.getTimestamp ("time").toLocalDateTime (),
            bookRepository.getBookById (resultSet.getInt ("books_id")),
            userRepository.getUser (resultSet.getInt ("users_id"))));
  }
}
