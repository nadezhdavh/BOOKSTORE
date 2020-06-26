package bg.unwe.BOOKSTORE.DTO;

import javax.validation.constraints.*;
import java.util.Currency;

public class BookRequestDTO
{
  @NotNull(message = "Provide author's name!")
  @Size(min = 1, max = 40, message = "The author's name - between 1 and 40 characters!")
  private String   author;
  @NotNull(message = "Provide title!")
  @Size(min = 1, max = 60, message = "Title between 1 and 60 characters")
  private String   title;
  @NotNull(message = "Provide year!")
  private Integer  year;
  @NotNull
  @Positive
  private Double   price;
  @NotNull
  private Currency currency;
  @NotNull
  @PositiveOrZero
  private Integer  quantity;

  public BookRequestDTO()
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
