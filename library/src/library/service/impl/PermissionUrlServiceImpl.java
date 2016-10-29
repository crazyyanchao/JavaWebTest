package library.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import library.dao.PermissionUrlDao;
import library.model.PermissionUrl;
import library.query.LibraryQuery;
import library.service.PermissionUrlService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("permissionUrlService")
public class PermissionUrlServiceImpl implements PermissionUrlService {
	private PermissionUrlDao permissionUrlDao;

	@Override
	public void update(PermissionUrl permissionUrl) {
		System.out.println(permissionUrl.getPermissionUrlId());
		System.out.println(permissionUrl.getAction());
		permissionUrlDao.update(permissionUrl);
	}

	@Override
	public void add(PermissionUrl permissionUrl) {
		permissionUrlDao.save(permissionUrl);
	}

	@Override
	public void delete(PermissionUrl permissionUrl) {
		permissionUrlDao.delete(permissionUrl);
	}

	@Override
	public void delete(int id) {
		permissionUrlDao.delete(id);
	}

	@Override
	public PermissionUrl queryById(int id) {
		return permissionUrlDao.queryById(id);
	}

	public PermissionUrlDao getPermissionUrlDao() {
		return permissionUrlDao;
	}

	@Resource
	public void setPermissionUrlDao(PermissionUrlDao permissionUrlDao) {
		this.permissionUrlDao = permissionUrlDao;
	}

	@Override
	public PageInfo<PermissionUrl> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		Map<String, String> map = query.getProperties();
		PageInfo<PermissionUrl> pageInfo = new PageInfo<PermissionUrl>(0, 0, 0);
		switch (query.getKey()) {
		case "parentId":
		default:
			pageInfo = permissionUrlDao.queryAll(page);
			break;
		}
		for (PermissionUrl permissionUrl : pageInfo.getDatas()) {
			System.out.println(permissionUrl.getAction());
		}
		return pageInfo;

	}

	@Override
	public List<PermissionUrl> queryAll() {
		return permissionUrlDao.queryAll();
	}

	@Override
	public List<PermissionUrl> queryPermissionUrlsByIds(String ids) {
		String[] urlIds = ids.split(",");
		List<PermissionUrl> permissionUrls = new ArrayList<PermissionUrl>();
		PermissionUrl permissionUrl;
		for (String urlId : urlIds) {
			permissionUrl = permissionUrlDao.queryById(Integer.parseInt(urlId
					.trim()));
			permissionUrls.add(permissionUrl);
		}
		return permissionUrls;
	}

	@Override
	public Map<String, String> queryAllUrls() {
		List<PermissionUrl> permissionUrls = queryAll();
		Map<String, String> urls = new HashMap<String, String>();
		PermissionUrl permissionUrl;
		for (int i = 0; i < permissionUrls.size(); i++) {
			permissionUrl = permissionUrls.get(i);
			urls.put(permissionUrl.getAction(), permissionUrl.getDescription());
		}
		return urls;
	}
}
