package ${package}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${package}.dao.${className}Dao;
import ${package}.model.${className};
import ${package}.service.${className}Service;
import ${package}.util.PageInfo;


@Service("${variable}Service")
public class ${className}ServiceImpl implements ${className}Service {
	private ${className}Dao ${variable}Dao;

	@Override
	public void update(${className} ${variable}) {
		${variable}Dao.update(${variable});
	}

	@Override
	public void add(${className} ${variable}) {
		${variable}Dao.save(${variable});
	}

	@Override
	public void delete(${className} ${variable}) {
		${variable}Dao.delete(${variable});
	}

	@Override
	public void delete(int id) {
		${variable}Dao.delete(id);
	}

	@Override
	public ${className} queryById(int id) {
		return ${variable}Dao.queryById(id);
	}
	
	@Override
	public PageInfo<${className}> queryAll(String query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		PageInfo<${className}> pageInfo = new PageInfo<${className}>(0, 0, 0);
		switch (query.getKey()) {
		case "new":// ÐÂ¹«¸æ
			break;
		default:
			pageInfo = announcementDao.queryAll(page);
			break;
		}
		return pageInfo;
	}

	@Override
	public List<${className}> queryAll() {
		return ${variable}Dao.queryAll();
	}

	public ${className}Dao get${className}Dao() {
		return ${variable}Dao;
	}

	@Resource
	public void set${className}Dao(${className}Dao ${variable}Dao) {
		this.${variable}Dao = ${variable}Dao;
	}

}
