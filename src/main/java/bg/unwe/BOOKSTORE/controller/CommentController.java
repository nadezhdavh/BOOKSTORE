package bg.unwe.BOOKSTORE.controller;

import bg.unwe.BOOKSTORE.DTO.CommentDTO;
import bg.unwe.BOOKSTORE.DTO.CommentResponseDTO;
import bg.unwe.BOOKSTORE.model.Book;
import bg.unwe.BOOKSTORE.model.Comment;
import bg.unwe.BOOKSTORE.model.User;
import bg.unwe.BOOKSTORE.service.BookService;
import bg.unwe.BOOKSTORE.service.CommentService;
import bg.unwe.BOOKSTORE.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/books/{bookId}/comments")
public class CommentController
{
  private CommentService commentService;
  private BookService    bookService;
  private UserService    userService;

  public CommentController(CommentService commentService, BookService bookService, UserService userService)
  {
    this.commentService = commentService;
    this.bookService = bookService;
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Void> addComment(@PathVariable @NotNull Integer bookId,
                                         @RequestBody @Valid CommentDTO commentDTO)
  {
    Principal loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    Book book = bookService.getBookById (bookId);
    User user = userService.getUser (loggedInUser.getName ());
    if(user.getEnabled ().equals (Boolean.FALSE)){
      return ResponseEntity.notFound ().build ();
    }
    Comment comment = new Comment ();
    comment.setContent (commentDTO.getContent ());
    comment.setLocalDateTime (LocalDateTime.now ());
    comment.setUser (user);
    comment.setBook (book);
    commentService.saveComment (comment);
    return ResponseEntity.ok ().build ();
  }
  @GetMapping()
  public ResponseEntity<List<CommentResponseDTO>> getAllBookComment(@PathVariable @NotNull Integer bookId)
  {
      Comparator<CommentResponseDTO> comparatorByTime = (a, b) -> b.getDateTime ().compareTo(a.getDateTime());
      List<CommentResponseDTO> sortedComments = new ArrayList<> ();
      List<Comment> commentsFromDb = commentService.getAllCommentsByBookId (bookId);
      for (Comment c : commentsFromDb
      ) {
        CommentResponseDTO response = new CommentResponseDTO ();
            response.setUsername(c.getUser().getUsername());
            response.setContent(c.getContent());
            response.setDateTime (c.getLocalDateTime ());
        sortedComments.add(response);
      }
      Collections.sort(sortedComments, comparatorByTime);
      return ResponseEntity.ok(sortedComments);
    }
  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> removeComment(@PathVariable @NotNull Integer bookId, @PathVariable @NotNull Integer commentId)
  {
    Comment comment = commentService.getCommentById (commentId);
    commentService.deleteComment (comment.getId ());
    return ResponseEntity.ok().build ();
  }
}
