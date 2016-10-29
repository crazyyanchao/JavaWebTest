package org.easybooks.bookstore.action;
import java.sql.*;
import org.easybooks.bookstore.jdbc.MySQLConnBean;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	//�����û������execute����
	public String execute() throws Exception{
		String usr=getUsername();//��ȡ�ύ������
		String pwd=getPassword();//��ȡ�ύ������
		boolean validated=false;//��֤�ɹ���ʶ
		MySQLConnBean MySqlBean=new MySQLConnBean();//�������Ӷ���
		//��ѯuser���еļ�¼
		String sql="select * from user";
		MySqlBean.OpenConn();//����MySqlBean�м���JDBC�����ķ���
		ResultSet rs=MySqlBean.executeQuery(sql);//ȡ�ý����
		while(rs.next())
		{
			if((rs.getString("username").compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0))
			{
				validated=true;//��ʶΪtrue��ʾ��֤�ɹ�ͨ��
			}
		}
		rs.close();
		MySqlBean.closeStmt();
		MySqlBean.closeConn();
		if(validated)
		{
			//��֤�ɹ������ַ���"success"
			return "success";
		}
		else
		{
			//��֤ʧ�ܷ����ַ���"error"
			return "error";
		}
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
}
