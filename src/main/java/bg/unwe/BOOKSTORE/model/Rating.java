package bg.unwe.BOOKSTORE.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ratings")
@SequenceGenerator(name = "RATING_SEQ", initialValue = 10000, allocationSize = 1)
public class Rating
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ")
  private Integer     id;
  @Column(columnDefinition = "integer default 0")
  @NotNull
  @Range(min = 1, max = 10)
  private Integer rating;
  @ManyToOne(targetEntity = Book.class)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book    book;

  @OneToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Rating()
  {
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
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
