package library.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import library.dao.BorrowRecordDao;
import library.model.BorrowRecord;
import library.util.PageInfo;

@Repository("borrowRecordDao")
public class BorrowRecordDaoImpl extends BaseDaoImpl<BorrowRecord> implements
		BorrowRecordDao {

	@Override
	public PageInfo<BorrowRecord> queryAllByBorrowTime(int page) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("borrowRecordId", "asc");
		String condition;
		Map<String, Object> parameter = new HashMap<String, Object>();
		return null;
	}

	@Override
	public PageInfo<BorrowRecord> queryAllByBookId(int page, int bookId) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookTypeId", "asc");
		String condition;
		Map<String, Object> parameter = new HashMap<String, Object>();
		return null;
	}
}