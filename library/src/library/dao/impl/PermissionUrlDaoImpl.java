package library.dao.impl;

import library.dao.PermissionUrlDao;
import library.model.PermissionUrl;

import org.springframework.stereotype.Repository;

@Repository("permissionUrlDao")
public class PermissionUrlDaoImpl extends BaseDaoImpl<PermissionUrl> implements
		PermissionUrlDao {

}
