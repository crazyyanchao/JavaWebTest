package library.dao.impl;

import java.util.HashMap;
import java.util.Map;

import library.dao.BookDao;
import library.model.Book;
import library.query.LibraryQuery;
import library.util.PageInfo;

import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public PageInfo<Book> queryAllByImportTime(int page) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("importTime", "desc");
		Map<String, Object> parameter = new HashMap<String, Object>();
		String condition = "from Book clazz";
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Book> queryAllByBorrowedCount(int page) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("borrowedCount", "desc");
		Map<String, Object> parameter = new HashMap<String, Object>();
		String condition = "from Book clazz";
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Book> queryAllByBookName(int page, String value) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookId", "desc");
		String condition = "from Book clazz where clazz.bookName like:bookName";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bookName", "%" + value + "%");
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Book> queryAllByAuthor(int page, String value) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookId", "desc");
		String condition = "from Book clazz where clazz.author like:author";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("author", "%" + value + "%");
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}

	@Override
	public PageInfo<Book> queryAllByBookId(int value) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookId", "desc");
		String condition = "from Book clazz where clazz.bookId=:bookId";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bookId", value);
		return queryAll(0, orderBy, condition, parameter, 10, 0);
	}

	@Override
	public PageInfo<Book> queryAllByOnline(int page, boolean online) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookId", "desc");
		String condition = "from Book clazz where clazz.online=:online";
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("online", online);
		return queryAll(0, orderBy, condition, parameter, 10, 0);
	}

	@Override
	public PageInfo<Book> queryAll(LibraryQuery query) {
		return super.queryAll(query);
	}

}