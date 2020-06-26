package bg.unwe.BOOKSTORE.DTO;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class RatingDTO
{
  @NotNull
  @Range(min = 1, max = 10)
  private Integer rating;

  public RatingDTO()
  {
  }

  public RatingDTO(Integer rating)
  {
    this.rating = rating;
  }

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }
}
