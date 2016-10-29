package org.easybooks.bookstore.action;
import java.sql.*;
import org.easybooks.bookstore.dao.*;
import org.easybooks.bookstore.dao.impl.*;
import org.easybooks.bookstore.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.*;
import org.springframework.context.support.*;
public class LoginAction extends ActionSupport{
	private User user;
	//处理用户请求的execute方法
	public String execute() throws Exception{
		boolean validated=false;//验证成功标识
		ApplicationContext context=new FileSystemXmlApplicationContext("/jsp_struts2_spring_dao_hibernate/src/applicationContext.xml");
		IUserDAO userDAO=(IUserDAO)context.getBean("userDAO");
		User u=userDAO.validateUser(user.getUsername(), user.getPassword());
		if(u!=null)
		{
			validated=true;//标识为true表示验证成功通过
		}
		if(validated)
		{
			//验证成功返回字符串"success"
			return SUCCESS;
		}
		else
		{
			//验证失败返回字符串"error"
			return ERROR;
		}
	}
	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user=user;
	}
}
