package action;

import java.util.Map;

import biz.Users;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DbService;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String counta;
	private String countb;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		DbService dbService = new DbService();
		Users user = new Users();
		user = dbService.hasUser(username, password);
		if(user.getUsername() == null) {
			this.addFieldError(username, "用户或密码不正确！");
			return INPUT;
		} else {
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			session.put("user", user.getUsername());
		}
		return SUCCESS;
	}

	public void setCounta(String counta) {
		this.counta = counta;
	}

	public String getCounta() {
		return counta;
	}

	public void setCountb(String countb) {
		this.countb = countb;
	}

	public String getCountb() {
		return countb;
	}
}
