package library.dao;

import library.model.Comment;
import library.util.PageInfo;

public interface CommentDao extends BaseDao<Comment> {

	public PageInfo<Comment> queryAllByBookId(int page, int bookId);

	public PageInfo<Comment> queryAllByCommentTime(int page);

	public PageInfo<Comment> queryAllByUser(int userId);
}
