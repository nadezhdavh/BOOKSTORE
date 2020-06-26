package bg.unwe.BOOKSTORE.DTO;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BuyBookDTO
{
  @NotNull
  @Positive
  private Integer quantity;

  public BuyBookDTO()
  {
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
