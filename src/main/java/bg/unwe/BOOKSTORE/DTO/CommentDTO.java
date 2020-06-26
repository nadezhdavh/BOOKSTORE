package bg.unwe.BOOKSTORE.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDTO
{
  @NotNull
  @Size(min = 2, max = 255)
  private String content;

  public CommentDTO()
  {
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }
}
