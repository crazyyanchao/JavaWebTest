package library.dao;

import library.model.BookType;
import library.util.PageInfo;

public interface BookTypeDao extends BaseDao<BookType>{

	public PageInfo<BookType> queryAllByParentId(int page, int parentId);
}
