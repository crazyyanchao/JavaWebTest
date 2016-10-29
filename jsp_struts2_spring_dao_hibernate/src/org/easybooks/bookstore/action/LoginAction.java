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
	//�����û������execute����
	public String execute() throws Exception{
		boolean validated=false;//��֤�ɹ���ʶ
		ApplicationContext context=new FileSystemXmlApplicationContext("/jsp_struts2_spring_dao_hibernate/src/applicationContext.xml");
		IUserDAO userDAO=(IUserDAO)context.getBean("userDAO");
		User u=userDAO.validateUser(user.getUsername(), user.getPassword());
		if(u!=null)
		{
			validated=true;//��ʶΪtrue��ʾ��֤�ɹ�ͨ��
		}
		if(validated)
		{
			//��֤�ɹ������ַ���"success"
			return SUCCESS;
		}
		else
		{
			//��֤ʧ�ܷ����ַ���"error"
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
