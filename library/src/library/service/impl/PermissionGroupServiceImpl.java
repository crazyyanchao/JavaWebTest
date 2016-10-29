package library.service.impl;

import java.util.List;

import javax.annotation.Resource;

import library.dao.PermissionGroupDao;
import library.model.PermissionGroup;
import library.query.LibraryQuery;
import library.service.PermissionGroupService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("permissionGroupService")
public class PermissionGroupServiceImpl implements PermissionGroupService {
	private PermissionGroupDao permissionGroupDao;

	@Override
	public void update(PermissionGroup permissionGroup) {
		System.out.println(permissionGroup.getPermissionUrls().size());
		permissionGroupDao.update(permissionGroup);
	}

	@Override
	public void add(PermissionGroup permissionGroup) {
		permissionGroupDao.save(permissionGroup);
	}

	@Override
	public void delete(PermissionGroup permissionGroup) {
		permissionGroupDao.delete(permissionGroup);
	}

	@Override
	public void delete(int id) {
		permissionGroupDao.delete(id);
	}

	@Override
	public PermissionGroup queryById(int id) {
		return permissionGroupDao.queryById(id);
	}
	@Override
	public PageInfo<PermissionGroup> queryAll(LibraryQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PermissionGroup> queryAll() {
		return permissionGroupDao.queryAll();
	}

	@Resource
	public void setPermissionGroupDao(PermissionGroupDao permissionGroupDao) {
		this.permissionGroupDao = permissionGroupDao;
	}

	public PermissionGroupDao getPermissionGroupDao() {
		return permissionGroupDao;
	}

	

}
