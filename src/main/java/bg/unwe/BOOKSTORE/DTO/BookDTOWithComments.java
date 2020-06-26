package bg.unwe.BOOKSTORE.DTO;

import java.util.ArrayList;
import java.util.List;

public class BookDTOWithComments extends BookDTO
{
  private Integer countComments;
  private List<CommentResponseDTO> commentList = new ArrayList<> ();

  public BookDTOWithComments()
  {
  }

  public List<CommentResponseDTO> getCommentList()
  {
    return commentList;
  }

  public void setCommentList(List<CommentResponseDTO> commentList)
  {
    this.commentList = commentList;
  }

  public Integer getCountComments()
  {
    return countComments;
  }

  public void setCountComments(Integer countComments)
  {
    this.countComments = countComments;
  }
}
