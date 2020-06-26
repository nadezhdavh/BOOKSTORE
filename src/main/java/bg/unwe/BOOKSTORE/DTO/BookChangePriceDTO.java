package bg.unwe.BOOKSTORE.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookChangePriceDTO
{
  @NotNull
  @Positive
  private Double   price;

  public BookChangePriceDTO()
  {
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }
}
