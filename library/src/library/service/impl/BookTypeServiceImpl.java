package library.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.BookTypeDao;
import library.model.BookType;
import library.query.LibraryQuery;
import library.service.BookTypeService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("bookTypeService")
public class BookTypeServiceImpl implements BookTypeService {
	private BookTypeDao bookTypeDao;

	@Override
	public void update(BookType bookType) {
		bookTypeDao.update(bookType);
	}

	@Override
	public void add(BookType bookType) {
		if (bookType.getParent() == null
				|| bookType.getParent().getBookTypeId() == 0) {
			bookType.setParent(null);
		} else {
			BookType parent = bookTypeDao.queryById(bookType.getParent()
					.getBookTypeId());
			bookType.setParent(parent);
		}
		bookTypeDao.save(bookType);
	}

	@Override
	public void delete(BookType bookType) {
		bookTypeDao.delete(bookType);
	}

	@Override
	public void delete(int id) {
		bookTypeDao.delete(id);
	}

	@Override
	public BookType queryById(int id) {
		return bookTypeDao.queryById(id);
	}

	@Override
	public List<BookType> queryAll() {
		return bookTypeDao.queryAll();
	}

	public BookTypeDao getBookTypeDao() {
		return bookTypeDao;
	}

	@Resource
	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	@Override
	public PageInfo<BookType> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		Map<String, String> map = query.getProperties();
		PageInfo<BookType> pageInfo = new PageInfo<BookType>(0, 0, 0);
		switch (query.getKey()) {
		case "parentId":
			String str = map.get("parentId");
			System.out.println(str + "parentId");
			int parentId = str == null || "" == str ? 0 : Integer.parseInt(str);
			pageInfo = bookTypeDao.queryAllByParentId(page, parentId);
			break;
		default:
			pageInfo = bookTypeDao.queryAll(page);
			System.out.println("default");
			break;
		}
		for (BookType bookType : pageInfo.getDatas()) {
			System.out.println(bookType.getBookTypeName());
		}
		return pageInfo;
	}
}
