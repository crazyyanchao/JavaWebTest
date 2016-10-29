package library.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.BaseDao;
import library.query.LibraryQuery;
import library.util.ConstantUtil;
import library.util.PageInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> clz;
	protected SessionFactory sessionFactory;

	@Override
	public void save(T t) {
		getCurrentSession().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	@Override
	public void delete(int id) {
		getCurrentSession().delete(queryById(id));
	}

	private String getClassName() {
		String name = (((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments())[0].toString();
		int index = name.lastIndexOf(".");
		name = name.substring(index + 1);
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryById(int id) {
		return (T) getCurrentSession().load(getClz(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryById(String id) {
		return (T) getCurrentSession().load(getClz(), id);
	}

	@Override
	public void update(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public int queryCount(String condition, Map<String, Object> parameter) {
		StringBuffer sql = new StringBuffer();
		String className = getClassName();
		sql.append("select count(*)");
		if (condition != null && condition.contains("from")) {
			int index = condition.indexOf("from");
			sql.append(condition.substring(index));
		} else {
			sql.append("from ").append(className).append(" clazz ");
		}
		Session session = getCurrentSession();
		Query query = session.createQuery(sql.toString());
		if (parameter != null) {
			for (String str : parameter.keySet()) {
				query.setParameter(str, parameter.get(str));
			}
		}
		return ((Long) query.list().get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() {
		List<T> datas = (List<T>) getCurrentSession().createQuery(
				"from " + getClassName()).list();
		return datas;
	}

	@Override
	public PageInfo<T> queryAll(int page) {
		return queryAll(page, null);
	}

	@Override
	public PageInfo<T> queryAll(int page, Map<String, String> orderby) {
		return queryAll(page, orderby, null, null);
	}

	@Override
	public PageInfo<T> queryAll(int page, String condition,
			Map<String, Object> parameter) {
		return queryAll(page, null, condition, parameter);
	}

	@Override
	public PageInfo<T> queryAll(int page, Map<String, String> orderby,
			String condition, Map<String, Object> parameter) {
		return queryAll(page, orderby, condition, parameter,
				ConstantUtil.COUNT_PER_PAGE, ConstantUtil.WIDTH_PER_PAGE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<T> queryAll(int page, Map<String, String> orderby,
			String condition, Map<String, Object> parameter, int pageSize,
			int indexWidth) {
		StringBuffer sql = new StringBuffer();
		String className = getClassName();
		System.out.println(condition);
		if (condition != null && condition.toLowerCase().contains("from")) {
			System.out.println("null");
			sql.append(condition);
		} else {
			sql.append("from ").append(className).append(" clazz");
		}
		if (orderby != null) {
			sql.append(" order by ");
			for (String order : orderby.keySet()) {
				sql.append("clazz.").append(order).append(" ")
						.append(orderby.get(order)).append(",");
			}
			sql.deleteCharAt(sql.length() - 1);
		}

		Session session = getCurrentSession();
		System.out.println(sql);
		Query query = session.createQuery(sql.toString());
		if (parameter != null) {
			for (String str : parameter.keySet()) {
				query.setParameter(str, parameter.get(str));
			}
		}

		int startPage = (page - 1) * pageSize;
		int lastPage = queryCount(condition, parameter);
		lastPage = (lastPage - 1) / pageSize + 1;
		List<T> lists = (List<T>) query.setFirstResult(startPage)
				.setMaxResults(pageSize).list();
		PageInfo<T> pageInfo = new PageInfo<T>(page, lastPage, indexWidth);
		pageInfo.setDatas(lists);
		System.out.println(lists.size());
		return pageInfo;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClz() {
		if (clz == null)
			clz = (Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		return clz;
	}

	@Override
	public PageInfo<T> queryAll(LibraryQuery query) {
		return queryAll(query.getPage());
	}

}
