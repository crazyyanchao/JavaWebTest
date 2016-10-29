package library.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.BookDao;
import library.dao.CommentDao;
import library.model.Book;
import library.model.Comment;
import library.query.LibraryQuery;
import library.service.CommentService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;
	private BookDao bookDao;

	@Override
	public void update(Comment comment) {
		commentDao.update(comment);
	}

	@Override
	public void add(Comment comment) {
		Book book = bookDao.queryById(comment.getBook().getBookId());
		comment.setBook(book);
		commentDao.save(comment);
	}

	@Override
	public void delete(Comment comment) {
		commentDao.delete(comment);
	}

	@Override
	public void delete(int id) {
		commentDao.delete(id);
	}

	@Override
	public Comment queryById(int id) {
		return commentDao.queryById(id);
	}

	@Override
	public List<Comment> queryAll() {
		return commentDao.queryAll();
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	@Resource
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public PageInfo<Comment> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		Map<String, String> map = query.getProperties();
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(0, 0, 0);
		switch (query.getKey()) {
		case "new":
			pageInfo = commentDao.queryAllByCommentTime(query.getPage());
			break;
		case "bookId":
			int bookId = map.get("bookId") == null ? 0 : Integer.parseInt(map
					.get("bookId"));
			pageInfo = commentDao.queryAllByBookId(page, bookId);
			break;
		default:
			pageInfo = commentDao.queryAll(query.getPage());
			break;
		}
		return pageInfo;
	}
}
