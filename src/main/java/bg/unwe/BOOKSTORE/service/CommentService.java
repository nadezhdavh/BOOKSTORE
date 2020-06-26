package bg.unwe.BOOKSTORE.service;

import bg.unwe.BOOKSTORE.model.Comment;

import java.util.List;

public interface CommentService
{
  Comment getCommentById(Integer id);

  void saveComment(Comment comment);

  void deleteComment(Integer id);

  List<Comment> getAllCommentsByBookId(Integer bookId);
}
