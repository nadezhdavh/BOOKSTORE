package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Comment;
import bg.unwe.BOOKSTORE.reopository.BookRepository;
import bg.unwe.BOOKSTORE.reopository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService
{
  @Autowired
  CommentRepository commentRepository;

  @Override
  public Comment getCommentById(Integer id)
  {
    return commentRepository.getCommentById (id);
  }

  @Override
  public void saveComment(Comment comment)
  {
    commentRepository.saveComment (comment);
  }

  @Override
  public void deleteComment(Integer id)
  {
    commentRepository.deleteComment (id);
  }

  @Override
  public List<Comment> getAllCommentsByBookId(Integer bookId)
  {
    return commentRepository.getAllCommentsByBookId (bookId);
  }
}
