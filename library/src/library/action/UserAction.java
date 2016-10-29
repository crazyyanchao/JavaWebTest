package library.action;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import library.model.User;
import library.query.LibraryQuery;
import library.service.UserService;
import library.util.ConstantUtil;
import library.util.PageInfo;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 9143073377030736794L;
	private User user;
	private UserService userService;
	private LibraryQuery query;
	private PageInfo<User> pageInfo;

	public String add() {
		userService.add(user);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_User");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		userService.update(user);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_User");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		User obj = userService.queryById(user.getUserId());
		ActionContext.getContext().put("user", obj);
		return SUCCESS;
	}

	public String show() {
		User obj = userService.queryById(user.getUserId());
		ActionContext.getContext().put("user", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<User> pageInfo = userService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String login() {
		userService.checkLogin(user);
		User u = userService.queryByUserNo(user.getUserNo());
		ActionContext.getContext().getSession().put("user", u);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_User");
		return ConstantUtil.REDIRECT_ACTION;

	}

	public String loginInput() {
		return SUCCESS;
	}

	public String logout() {
		ActionContext.getContext().getSession().clear();
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_User");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String delete() {
		userService.delete(user.getUserId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_User");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String showAllJson() {
		pageInfo = userService.queryAll(query);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

	public PageInfo<User> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<User> pageInfo) {
		this.pageInfo = pageInfo;
	}

}