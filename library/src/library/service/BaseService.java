package library.service;

import java.util.List;

import library.query.LibraryQuery;
import library.util.PageInfo;

public interface BaseService<T> {
	public void add(T t);

	public void update(T t);

	public void delete(T t);

	public void delete(int id);

	public T queryById(int id);

	public List<T> queryAll();

	public PageInfo<T> queryAll(LibraryQuery query);

}
