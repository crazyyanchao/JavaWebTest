package library.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.BookDao;
import library.dao.BookTypeDao;
import library.model.Book;
import library.model.BookType;
import library.query.LibraryQuery;
import library.service.BookService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService {
	private BookDao bookDao;
	private BookTypeDao bookTypeDao;

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	public void add(Book book) {
		book.setImportTime(new Date());
		BookType bookType = bookTypeDao.queryById(book.getBookId());
		book.setBookType(bookType);
		book.setOnline(true);
		bookDao.save(book);
	}

	@Override
	public void delete(Book book) {
		bookDao.delete(book);
	}

	@Override
	public void delete(int id) {
		bookDao.delete(id);
	}

	@Override
	public Book queryById(int id) {
		return bookDao.queryById(id);
	}

	// 最新书籍 借阅排行

	@Override
	public List<Book> queryAll() {
		return bookDao.queryAll();
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	@Resource
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> queryNewBooks(int i) {
		List<Book> books = new ArrayList<Book>();
		Book book = new Book();
		book.setAuthor("aaaaa");
		book.setBookName("bbbbb");
		books.add(book);
		books.add(book);
		books.add(book);
		books.add(book);
		books.add(book);
		return books;
	}

	public BookTypeDao getBookTypeDao() {
		return bookTypeDao;
	}

	@Resource
	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	@Override
	public PageInfo<Book> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		Map<String, String> map = query.getProperties();
		PageInfo<Book> pageInfo = new PageInfo<Book>(0, 0, 0);
		switch (query.getKey()) {
		case "new":// 新书上架
			pageInfo = bookDao.queryAllByImportTime(page);
			pageInfo.setDatas(queryNewBooks(10));
			System.out.println("new");
			break;
		case "rank":// 借阅排行
			pageInfo = bookDao.queryAllByBorrowedCount(page);
			break;
		case "bookName":// 书名
			String bookName = map.get("bookName");
			pageInfo = bookDao.queryAllByBookName(page, bookName);
			break;

		case "author":// 作者
			String author = map.get("author");
			pageInfo = bookDao.queryAllByAuthor(query.getPage(), author);
			break;
		case "online":// 上线书籍
			pageInfo = bookDao.queryAllByOnline(query.getPage(),true);
			break;
		case "outline":// 下线书籍
			pageInfo = bookDao.queryAllByOnline(query.getPage(), false);
			break;
		case "bookId":// 书籍编号
			int bookId = map.get("bookId") == null ? 0 : Integer.parseInt(map
					.get("bookId"));
			pageInfo = bookDao.queryAllByBookId(bookId);
			break;
		case "search":
			pageInfo = bookDao.queryAll(query);
			break;
		default:
			pageInfo = bookDao.queryAll(page);
			System.out.println("default");
			break;
		}
		for (Book book : pageInfo.getDatas()) {
			System.out.println(book.getBookName());
		}
		return pageInfo;
	}
}
