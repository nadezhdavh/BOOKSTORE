package bg.unwe.BOOKSTORE.DTO;

import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Currency;

public class BookDTO
{
  private String   author;
  private String   title;
  private Integer  year;
  private Double  rating;
  private Double   price;
  private Currency currency;
  private Integer  quantity;

  public BookDTO()
  {
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public String getCurrency()
  {
    return currency.getCurrencyCode ();
  }

  public void setCurrency(String currency)
  {
    this.currency = Currency.getInstance (currency);
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity(Integer quantity)
  {
    this.quantity = quantity;
  }
}
