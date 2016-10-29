package library.dao;

import library.model.BorrowRecord;
import library.util.PageInfo;

public interface BorrowRecordDao extends BaseDao<BorrowRecord> {

	public PageInfo<BorrowRecord> queryAllByBookId(int page, int bookId);

	public PageInfo<BorrowRecord> queryAllByBorrowTime(int page);
}
