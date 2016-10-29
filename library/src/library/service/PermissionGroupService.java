package library.service;

import java.util.List;

import library.model.PermissionGroup;

public interface PermissionGroupService extends BaseService<PermissionGroup> {
	public List<PermissionGroup> queryAll();
}
