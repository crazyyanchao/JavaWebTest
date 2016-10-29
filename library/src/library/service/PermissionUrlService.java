package library.service;

import java.util.List;
import java.util.Map;

import library.model.PermissionUrl;

public interface PermissionUrlService extends BaseService<PermissionUrl> {
	public List<PermissionUrl> queryPermissionUrlsByIds(String ids);

	public Map<String, String> queryAllUrls();
}
