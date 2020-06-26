package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Rating;

import java.util.List;

public interface RatingService
{
  void saveRating(Rating rating);

  List<Rating> getAllRatingsForOneBook(Integer bookId);

  void deleteRating(Integer id);

  Double getRating(Integer bookId);
}
