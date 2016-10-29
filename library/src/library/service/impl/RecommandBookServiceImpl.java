package library.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import library.dao.RecommandBookDao;
import library.model.RecommandBook;
import library.query.LibraryQuery;
import library.service.RecommandBookService;
import library.util.PageInfo;

@Service("recommandBookService")
public class RecommandBookServiceImpl implements RecommandBookService {
	private RecommandBookDao recommandBookDao;

	@Override
	public void update(RecommandBook recommandBook) {
		recommandBookDao.update(recommandBook);
	}

	@Override
	public void add(RecommandBook recommandBook) {
		recommandBookDao.save(recommandBook);
	}

	@Override
	public void delete(RecommandBook recommandBook) {
		recommandBookDao.delete(recommandBook);
	}

	@Override
	public void delete(int id) {
		recommandBookDao.delete(id);
	}

	@Override
	public RecommandBook queryById(int id) {
		return recommandBookDao.queryById(id);
	}

	@Override
	public PageInfo<RecommandBook> queryAll(LibraryQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommandBook> queryAll() {
		return recommandBookDao.queryAll();
	}

	public RecommandBookDao getRecommandBookDao() {
		return recommandBookDao;
	}

	@Resource
	public void setRecommandBookDao(RecommandBookDao recommandBookDao) {
		this.recommandBookDao = recommandBookDao;
	}

}
