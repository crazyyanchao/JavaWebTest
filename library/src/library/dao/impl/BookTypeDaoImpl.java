package library.dao.impl;

import java.util.HashMap;
import java.util.Map;

import library.dao.BookTypeDao;
import library.model.BookType;
import library.util.PageInfo;

import org.springframework.stereotype.Repository;

@Repository("bookTypeDao")
public class BookTypeDaoImpl extends BaseDaoImpl<BookType> implements
		BookTypeDao {

	@Override
	public PageInfo<BookType> queryAllByParentId(int page, int parentId) {
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("bookTypeId", "asc");
		String condition;
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (parentId == 0) {
			condition = "from BookType clazz where clazz.parent.bookTypeId is null";
		} else {
			condition = "from BookType clazz where clazz.parent.bookTypeId=:parentId";
			parameter.put("parentId", parentId);
		}
		return queryAll(page, orderBy, condition, parameter, page == 0 ? 10
				: 50, 0);
	}
}