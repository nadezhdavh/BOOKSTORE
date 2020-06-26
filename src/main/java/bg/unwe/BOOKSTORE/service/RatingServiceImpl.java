package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Rating;
import bg.unwe.BOOKSTORE.reopository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService
{
  @Autowired
  RatingRepository ratingRepository;

  @Override
  public void saveRating(Rating rating)
  {
    ratingRepository.saveRating (rating);
  }

  @Override
  public List<Rating> getAllRatingsForOneBook(Integer bookId)
  {
    return ratingRepository.getAllRatingsForOneBook (bookId);
  }

  @Override
  public void deleteRating(Integer id)
  {
    ratingRepository.deleteRating (id);
  }

  @Override
  public Double getRating(Integer bookId)
  {
    List<Rating> bookRating = ratingRepository.getAllRatingsForOneBook (bookId);
    double allRatings = bookRating.stream().mapToDouble(a -> a.getRating()).sum();
    double average = allRatings / bookRating.size();
    return average;
  }
}
