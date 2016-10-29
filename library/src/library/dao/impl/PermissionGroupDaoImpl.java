package library.dao.impl;

import library.dao.PermissionGroupDao;
import library.model.PermissionGroup;

import org.springframework.stereotype.Repository;

@Repository("permissionGroupDao")
public class PermissionGroupDaoImpl extends BaseDaoImpl<PermissionGroup>
		implements PermissionGroupDao {
}
