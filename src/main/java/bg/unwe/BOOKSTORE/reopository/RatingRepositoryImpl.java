package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Comment;
import bg.unwe.BOOKSTORE.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingRepositoryImpl implements RatingRepository
{
  @Autowired
  private JdbcTemplate template;

  public RatingRepositoryImpl(JdbcTemplate jdbcTemplate)
  {
    this.template = jdbcTemplate;
  }
  @Override
  public void saveRating(Rating rating)
  {
    String sql = "INSERT INTO RATINGS (id, rating, book_id, user_id) VALUES (RATING_SEQ.nextval, ?, ?, ?)";
    template.update (sql, rating.getRating (), rating.getBook ().getId (), rating.getUser ().getId ());
  }

  @Override
  public List<Rating> getAllRatingsForOneBook(Integer bookId)
  {
    Object[] args = {bookId};
    String sql = "SELECT * FROM RATINGS R\n" +
        "INNER JOIN BOOKS B\n" +
        "ON r.book_id = b.id\n" +
        "WHERE b.id = ?";
    List<Rating> ratings = template.query (sql, args, BeanPropertyRowMapper.newInstance (Rating.class));
    return ratings;
  }

  @Override
  public void deleteRating(Integer id)
  {
    String sql = "DELETE FROM RATINGS WHERE ID = ?";
    template.update (sql, id);
  }
}
