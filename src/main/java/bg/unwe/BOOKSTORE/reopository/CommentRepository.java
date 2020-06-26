package bg.unwe.BOOKSTORE.reopository;

import bg.unwe.BOOKSTORE.model.Comment;

import java.util.List;

public interface CommentRepository
{
  Comment getCommentById(Integer id);

  void saveComment(Comment comment);

  void deleteComment(Integer id);

  List<Comment> getAllCommentsByBookId(Integer bookId);
}
