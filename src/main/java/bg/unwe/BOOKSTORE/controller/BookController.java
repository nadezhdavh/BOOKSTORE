package bg.unwe.BOOKSTORE.controller;

import bg.unwe.BOOKSTORE.DTO.*;
import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Comment;
import bg.unwe.BOOKSTORE.service.BookService;
import bg.unwe.BOOKSTORE.service.CommentService;
import bg.unwe.BOOKSTORE.service.RatingService;
import bg.unwe.BOOKSTORE.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController
{

  private BookService    bookService;
  private RatingService  ratingService;
  private CommentService commentService;
  private UserService    userService;

  public BookController(BookService bookService, RatingService ratingService, CommentService commentService, UserService userService)
  {
    this.bookService = bookService;
    this.ratingService = ratingService;
    this.commentService = commentService;
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTOWithComments> getBook(@PathVariable @NotNull Integer id)
  {
    Book book = bookService.getBookById (id);
    Comparator<CommentResponseDTO> comparatorByTime = (a, b) -> b.getDateTime ().compareTo(a.getDateTime ());
    BookDTOWithComments bookForClient = new BookDTOWithComments ();
    List<CommentResponseDTO> comments = new ArrayList<> ();
    List<Comment> c = commentService.getAllCommentsByBookId (book.getId ());
    for (Comment comment : c) {
      CommentResponseDTO commentDTO = new CommentResponseDTO ();
      commentDTO.setUsername (comment.getUser ().getUsername ());
      commentDTO.setContent (comment.getContent ());
      commentDTO.setDateTime (comment.getLocalDateTime ());
      comments.add (commentDTO);
      Collections.sort(comments, comparatorByTime);
    }
    bookForClient.setYear (book.getYear ());
    bookForClient.setAuthor (book.getAuthor ());
    bookForClient.setTitle (book.getTitle ());
    bookForClient.setPrice (book.getPrice ());
    bookForClient.setCurrency (book.getCurrency ());
    bookForClient.setQuantity (book.getQuantity ());
    bookForClient.setRating (ratingService.getRating (book.getId ()));
    bookForClient.setCommentList (comments);
    bookForClient.setCountComments (comments.size ());

    return ResponseEntity.ok (bookForClient);
  }
  @GetMapping("/title")
  public ResponseEntity<BookDTOWithComments> getBookByTitle(@RequestParam String title)
  {
    Book book = bookService.getBookByTitle (title);
    Comparator<CommentResponseDTO> comparatorByTime = (a, b) -> b.getDateTime ().compareTo(a.getDateTime ());
    BookDTOWithComments bookForClient = new BookDTOWithComments ();
    List<CommentResponseDTO> comments = new ArrayList<> ();
    List<Comment> c = commentService.getAllCommentsByBookId (book.getId ());
    for (Comment comment : c) {
      CommentResponseDTO commentDTO = new CommentResponseDTO ();
      commentDTO.setUsername (comment.getUser ().getUsername ());
      commentDTO.setContent (comment.getContent ());
      commentDTO.setDateTime (comment.getLocalDateTime ());
      comments.add (commentDTO);
      Collections.sort(comments, comparatorByTime);
    }
    bookForClient.setYear (book.getYear ());
    bookForClient.setAuthor (book.getAuthor ());
    bookForClient.setTitle (book.getTitle ());
    bookForClient.setPrice (book.getPrice ());
    bookForClient.setCurrency (book.getCurrency ());
    bookForClient.setQuantity (book.getQuantity ());
    bookForClient.setRating (ratingService.getRating (book.getId ()));
    bookForClient.setCommentList (comments);
    bookForClient.setCountComments (comments.size ());

    return ResponseEntity.ok (bookForClient);
  }

  @PostMapping(value = "/new")
  public ResponseEntity<Void> addBook(@Valid @RequestBody BookRequestDTO bookDTO)  //adding a object Book to the repo
  {
    Book book = new Book ();
    book.setTitle (bookDTO.getTitle ());
    book.setAuthor (bookDTO.getAuthor ());
    book.setYear (bookDTO.getYear ());
    book.setPrice (bookDTO.getPrice ());
    book.setCurrency (bookDTO.getCurrency ());
    book.setQuantity (bookDTO.getQuantity ());
    bookService.saveBook (book);
    return ResponseEntity.ok ().build ();
  }

  @PostMapping(value = "/{id}/buy")
  public ResponseEntity<BookDTO> buy(@PathVariable @NotNull Integer id, @Valid @RequestBody BuyBookDTO buyBookDTO)
  {

    Book book = bookService.getBookById (id);
    int result = book.getQuantity () - buyBookDTO.getQuantity ();
    if(result < 0){
      return ResponseEntity.badRequest ().build ();
    }
    else {
      book.setQuantity (result);
      bookService.updateQuantity (book);
    return ResponseEntity.ok ().build ();
    }
  }
  @PostMapping(value = "/{id}/price")
  public ResponseEntity<BookDTO> changePrice(@PathVariable @NotNull Integer id, @Valid @RequestBody BookChangePriceDTO priceDTO)
  {
    Book book = bookService.getBookById (id);
    book.setPrice (priceDTO.getPrice ());
    bookService.updatePrice (book);
    return ResponseEntity.ok ().build ();
  }

  @GetMapping()
  public List<BookDTO> getAllBooks()
  {
    List<BookDTO> bookDTOS = new ArrayList<> ();
    List<Book> books = bookService.getAllBooks ();
    for (Book b : books){
      BookDTO bookDTO = new BookDTO ();
      bookDTO.setTitle (b.getTitle ());
      bookDTO.setAuthor (b.getAuthor ());
      bookDTO.setYear (b.getYear ());
      bookDTO.setPrice (b.getPrice ());
      bookDTO.setCurrency (b.getCurrency ());
      bookDTO.setQuantity (b.getQuantity ());
      bookDTO.setRating (ratingService.getRating (b.getId ()));

      bookDTOS.add (bookDTO);
    }
    return bookDTOS;
  }
  @GetMapping(value = "/byAuthor")
  public List<BookDTO> getAllBooksByAuthor(@RequestParam String author)
  {
    List<BookDTO> bookDTOS = new ArrayList<> ();
    List<Book> books = bookService.getAllBooksByAuthor (author);
    for (Book b : books){
      BookDTO bookDTO = new BookDTO ();
      bookDTO.setTitle (b.getTitle ());
      bookDTO.setAuthor (b.getAuthor ());
      bookDTO.setYear (b.getYear ());
      bookDTO.setPrice (b.getPrice ());
      bookDTO.setCurrency (b.getCurrency ());
      bookDTO.setQuantity (b.getQuantity ());
      bookDTO.setRating (ratingService.getRating (b.getId ()));

      bookDTOS.add (bookDTO);
    }
    return bookDTOS;
  }

}
