package library.dao;

import library.model.Book;
import library.util.PageInfo;

public interface BookDao extends BaseDao<Book> {

	public PageInfo<Book> queryAllByImportTime(int page);

	public PageInfo<Book> queryAllByBorrowedCount(int page);

	public PageInfo<Book> queryAllByBookName(int page, String value);

	public PageInfo<Book> queryAllByAuthor(int page, String value);

	public PageInfo<Book> queryAllByBookId(int value);

	public PageInfo<Book> queryAllByOnline(int page, boolean online);
}
