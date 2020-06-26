package bg.unwe.BOOKSTORE.controller;

import bg.unwe.BOOKSTORE.DTO.RatingDTO;
import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Rating;
import bg.unwe.BOOKSTORE.model.User;
import bg.unwe.BOOKSTORE.service.BookService;
import bg.unwe.BOOKSTORE.service.RatingService;
import bg.unwe.BOOKSTORE.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Validated
@RestController
@RequestMapping("/api/v1/ratings/{bookId}")
public class RatingController
{
  private  RatingService ratingService;
  private  BookService   bookService;
  private  UserService   userService;

  public RatingController(RatingService ratingService, BookService bookService, UserService userService)
  {
    this.ratingService = ratingService;
    this.bookService = bookService;
    this.userService = userService;
  }
  @PostMapping()
  public ResponseEntity<Void> addRating(@PathVariable Integer bookId, @RequestBody @Valid RatingDTO ratingDTO)
  {
    Principal loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    Book book = bookService.getBookById (bookId);
    User user = userService.getUser(loggedInUser.getName ());
    if(user.getEnabled ().equals (Boolean.FALSE)){
      return ResponseEntity.notFound ().build ();
    }
    Rating rating = new Rating ();
    rating.setRating (ratingDTO.getRating ());
    rating.setBook (book);
    rating.setUser (user);

    ratingService.saveRating (rating);
    return ResponseEntity.ok ().build ();
  }

}
