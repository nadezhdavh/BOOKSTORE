package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Rating;
import bg.unwe.BOOKSTORE.model.User;
import oracle.jdbc.OracleDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class RatingRepositoryImplTest
{
  @Autowired
  RatingRepository ratingRepository;
  @BeforeMethod
  public void setUp()
  {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
    dataSource.setUrl ("jdbc:oracle:thin:@//83.228.124.173:6223/nadezhda_hristeva");
    dataSource.setUsername ("nadezhda_hristeva");
    dataSource.setPassword ("dbpass");
    dataSource.setDriverClass (OracleDriver.class);
    ratingRepository = new RatingRepositoryImpl (new JdbcTemplate (dataSource));
  }
  @Test
  public void testSaveRating()
  {
    Book book = new Book ();
    book.setId (1200);
    User user = new User();
    user.setId (123);
    Rating rating = new Rating ();
    rating.setRating (8);
    rating.setBook (book);
    rating.setUser (user);
    ratingRepository.saveRating (rating);
  }

  @Test
  public void testGetAllRatingsForOneBook()
  {
    List<Rating> ratings = ratingRepository.getAllRatingsForOneBook (1200);
  }

  @Test
  public void testDeleteRating()
  {
    Integer id = 10000;
    ratingRepository.deleteRating (id);
  }
}