package bg.unwe.BOOKSTORE.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Currency;

@Entity
@Table(name = "books")
@SequenceGenerator(name = "BOOK_SEQ", initialValue = 1000, allocationSize = 1)
public class Book
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
  private Integer     id;
  @Column(name = "title", nullable = false)
  @Size(min = 2, max = 60, message = "Title between 1 and 60 characters")
  private String  title;
  @Column(name = "author", nullable = false)
  @Size(min = 2, max = 40, message = "The author's name - between 1 and 40 characters!")
  private String  author;
  @Column(name = "year", nullable = false)
  @Positive(message = "Provide positive year!")
  private Integer year;
  @Column(name = "price", nullable = false)
  @Positive
  private Double price;
  @Column(name = "currency", nullable = false)
  private Currency    currency;
  @Column(name = "quantity", nullable = false)
  @Positive
  private Integer quantity;


  public Book()
  {
  }

  public Book(@Size(min = 2, max = 60, message = "Title between 1 and 60 characters") String title, @Size(min = 2, max = 40, message = "The author's name - between 1 and 40 characters!") String author, @Positive(message = "Provide positive year!") Integer year, @Positive Double price, @Positive Integer quantity)
  {
    this.title = title;
    this.author = author;
    this.year = year;
    this.price = price;
    this.quantity = quantity;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity(Integer quantity)
  {
    this.quantity = quantity;
  }

  public String getCurrency()
  {
    return currency.getCurrencyCode ();
  }

  public void setCurrency(String currency)
  {
    this.currency = Currency.getInstance (currency);
  }
}
