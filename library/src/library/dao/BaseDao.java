package library.dao;

import java.util.List;
import java.util.Map;

import library.query.LibraryQuery;
import library.util.PageInfo;

public interface BaseDao<T> {
	/**
	 * 
	 */
	public void save(T t);

	public void saveOrUpdate(T t);

	public void delete(T t);

	public void delete(int id);

	public void update(T t);

	public T queryById(int id);

	public T queryById(String id);

	public int queryCount(String condition, Map<String, Object> parameter);

	public List<T> queryAll();

	public PageInfo<T> queryAll(LibraryQuery query);

	public PageInfo<T> queryAll(int page);

	public PageInfo<T> queryAll(int page, Map<String, String> orderby);

	public PageInfo<T> queryAll(int page, String condition,
			Map<String, Object> parameter);

	public PageInfo<T> queryAll(int page, Map<String, String> orderby,
			String condition, Map<String, Object> parameter);

	public PageInfo<T> queryAll(int page, Map<String, String> orderby,
			String condition, Map<String, Object> parameter, int pageSize,
			int indexWidth);

}
