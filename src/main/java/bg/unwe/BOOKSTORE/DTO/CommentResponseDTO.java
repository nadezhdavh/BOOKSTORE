package bg.unwe.BOOKSTORE.DTO;

import java.time.LocalDateTime;

public class CommentResponseDTO
{
  private String content;
  private LocalDateTime dateTime;
  private String username;

  public CommentResponseDTO()
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

  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

}
