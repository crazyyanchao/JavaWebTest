package library.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import library.model.PermissionGroup;
import library.model.PermissionUrl;
import library.query.LibraryQuery;
import library.service.PermissionGroupService;
import library.service.PermissionUrlService;
import library.util.ConstantUtil;
import library.util.PageInfo;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("permissionGroupAction")
@Scope("prototype")
public class PermissionGroupAction extends ActionSupport {

	private static final long serialVersionUID = -2218478716055853485L;
	private PermissionGroup permissionGroup;
	private PermissionGroupService permissionGroupService;
	private PermissionUrlService permissionUrlService;
	private String urls;
	private LibraryQuery query;
	private PageInfo<PermissionGroup> pageInfo;

	public String add() {
		permissionGroupService.add(permissionGroup);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_PermissionGroup");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String show() {
		PermissionGroup group = permissionGroupService
				.queryById(permissionGroup.getPermissionGroupId());
		ActionContext.getContext().put("permissionGroup", group);
		return SUCCESS;
	}

	public String showJson() {
		permissionGroup = permissionGroupService.queryById(permissionGroup
				.getPermissionGroupId());
		System.out.println("perfasdfdsafsad");
		return SUCCESS;
	}

	public String showAll() {
		List<PermissionGroup> groups = permissionGroupService.queryAll();
		List<PermissionUrl> permissionUrls = permissionUrlService.queryAll();
		ActionContext.getContext().put("permissionGroups", groups);
		ActionContext.getContext().put("permissionUrls", permissionUrls);
		return SUCCESS;
	}

	public String update() {
		System.out.println(urls);
		List<PermissionUrl> permissionUrls = permissionUrlService
				.queryPermissionUrlsByIds(urls);
		// for (PermissionUrl url : permissionUrls) {
		// url.addPermissionGroup(permissionGroup);
		// permissionUrlService.update(url);
		// }
		permissionGroup.resetPermissionUrls(permissionUrls);
		permissionGroupService.update(permissionGroup);

		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_PermissionGroup");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		PermissionGroup group = permissionGroupService
				.queryById(permissionGroup.getPermissionGroupId());
		List<PermissionUrl> permissionUrls = permissionUrlService.queryAll();
		List<Integer> urlIds = new ArrayList<Integer>();
		for (PermissionUrl url : group.getPermissionUrls()) {
			urlIds.add(url.getPermissionUrlId());
		}
		ActionContext.getContext().put("urlIds", urlIds);
		ActionContext.getContext().put("permissionGroup", group);
		ActionContext.getContext().put("permissionUrls", permissionUrls);
		return SUCCESS;
	}

	public String updateJson() {
		List<PermissionUrl> permissionUrls = permissionUrlService
				.queryPermissionUrlsByIds(urls);
		permissionGroup = permissionGroupService.queryById(permissionGroup
				.getPermissionGroupId());
		permissionGroup.resetPermissionUrls(permissionUrls);
		permissionGroupService.update(permissionGroup);
		return SUCCESS;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	@JSON(serialize = false)
	public PermissionGroupService getPermissionGroupService() {
		return permissionGroupService;
	}

	@Resource
	public void setPermissionGroupService(
			PermissionGroupService permissionGroupService) {
		this.permissionGroupService = permissionGroupService;
	}

	@JSON(serialize = false)
	public PermissionUrlService getPermissionUrlService() {
		return permissionUrlService;
	}

	@Resource
	public void setPermissionUrlService(
			PermissionUrlService permissionUrlService) {
		this.permissionUrlService = permissionUrlService;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

	public PageInfo<PermissionGroup> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<PermissionGroup> pageInfo) {
		this.pageInfo = pageInfo;
	}
}
