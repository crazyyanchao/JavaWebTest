package library.action;

import javax.annotation.Resource;

import library.model.PermissionUrl;
import library.query.LibraryQuery;
import library.service.PermissionUrlService;
import library.util.ConstantUtil;
import library.util.PageInfo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("permissionUrlAction")
@Scope("prototype")
public class PermissionUrlAction extends ActionSupport {

	private static final long serialVersionUID = -1339315927260946841L;
	private PermissionUrlService permissionUrlService;
	private PermissionUrl permissionUrl;
	private LibraryQuery query;
	private PageInfo<PermissionUrl> pageInfo;

	public String add() {
		permissionUrlService.add(permissionUrl);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_PermissionUrl");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		ActionContext.getContext().put("urls", ConstantUtil.URLS);
		return SUCCESS;
	}

	public String show() {
		PermissionUrl url = permissionUrlService.queryById(permissionUrl
				.getPermissionUrlId());
		ActionContext.getContext().put("permissionUrl", url);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_PermissionUrl");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String showAll() {
		PageInfo<PermissionUrl> pageInfo = permissionUrlService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String update() {
		permissionUrlService.update(permissionUrl);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_PermissionUrl");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		ActionContext.getContext().put("urls", ConstantUtil.URLS);
		PermissionUrl url = permissionUrlService.queryById(permissionUrl
				.getPermissionUrlId());
		ActionContext.getContext().put("permissionUrl", url);
		return SUCCESS;
	}

	public PermissionUrlService getPermissionUrlService() {
		return permissionUrlService;
	}

	@Resource
	public void setPermissionUrlService(
			PermissionUrlService permissionUrlService) {
		this.permissionUrlService = permissionUrlService;
	}

	public PermissionUrl getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(PermissionUrl permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

	public PageInfo<PermissionUrl> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<PermissionUrl> pageInfo) {
		this.pageInfo = pageInfo;
	}

}
