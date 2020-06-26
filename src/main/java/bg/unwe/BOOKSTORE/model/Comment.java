package bg.unwe.BOOKSTORE.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "COMMENT_SEQ", initialValue = 1000, allocationSize = 1)
public class Comment
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
  private Integer     id;
  @Column(name = "content", nullable = false)
  private String content;
  @Column(name = "time", nullable = false)
  private LocalDateTime localDateTime;

  @ManyToOne(targetEntity = Book.class)
  @JoinColumn(name = "books_id", referencedColumnName = "id")
  private Book    book;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "users_id", referencedColumnName = "id")
  private User user;

  public Comment()
  {
  }

  public Comment(Integer id, String content, LocalDateTime time, Book book_id, User user_id)
  {
    this.id = id;
    this.content= content;
    this.localDateTime = time;
    this.book=book_id;
    this.user=user_id;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public LocalDateTime getLocalDateTime()
  {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime)
  {
    this.localDateTime = localDateTime;
  }

  public Book getBook()
  {
    return book;
  }

  public void setBook(Book book)
  {
    this.book = book;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }
}
