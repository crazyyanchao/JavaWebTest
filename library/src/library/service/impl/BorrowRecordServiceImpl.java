package library.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.BorrowRecordDao;
import library.model.BorrowRecord;
import library.query.LibraryQuery;
import library.service.BorrowRecordService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("borrowRecordService")
public class BorrowRecordServiceImpl implements BorrowRecordService {
	private BorrowRecordDao borrowRecordDao;

	@Override
	public void update(BorrowRecord borrowRecord) {
		borrowRecordDao.update(borrowRecord);
	}

	@Override
	public void add(BorrowRecord borrowRecord) {
		borrowRecordDao.save(borrowRecord);
	}

	@Override
	public void delete(BorrowRecord borrowRecord) {
		borrowRecordDao.delete(borrowRecord);
	}

	@Override
	public void delete(int id) {
		borrowRecordDao.delete(id);
	}

	@Override
	public BorrowRecord queryById(int id) {
		return borrowRecordDao.queryById(id);
	}

	@Override
	public List<BorrowRecord> queryAll() {
		return borrowRecordDao.queryAll();
	}

	public BorrowRecordDao getBorrowRecordDao() {
		return borrowRecordDao;
	}

	@Resource
	public void setBorrowRecordDao(BorrowRecordDao borrowRecordDao) {
		this.borrowRecordDao = borrowRecordDao;
	}

	@Override
	public PageInfo<BorrowRecord> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		Map<String, String> map = query.getProperties();
		PageInfo<BorrowRecord> pageInfo = new PageInfo<BorrowRecord>(0, 0, 0);
		switch (query.getKey()) {
		case "new":
			pageInfo = borrowRecordDao.queryAllByBorrowTime(page);
			break;
		case "bookId":
			int bookId = map.get("bookId") == null ? 0 : Integer.parseInt(map
					.get("bookId"));
			pageInfo = borrowRecordDao.queryAllByBookId(page, bookId);
			break;
		default:
			pageInfo = borrowRecordDao.queryAll(page);
			break;
		}
		return pageInfo;
	}

}
