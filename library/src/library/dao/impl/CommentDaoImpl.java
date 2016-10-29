package library.dao.impl;

import java.util.HashMap;
import java.util.Map;

import library.dao.CommentDao;
import library.model.Comment;
import library.util.PageInfo;

import org.springframework.stereotype.Repository;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {
	@Override
	public PageInfo<Comment> queryAllByCommentTime(int page) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("commentTime", "desc");
		Map<String, Object> parameter = new HashMap<String, Object>();
		String condition = "from Comment clazz";
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Comment> queryAllByBookId(int page, int bookId) {
		System.out.println(bookId);
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("commentId", "desc");
		String condition = "from Comment clazz where clazz.book.bookId=:bookId";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bookId", bookId);
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Comment> queryAllByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


}