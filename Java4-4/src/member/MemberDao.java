package member;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import session.HibernateSessionFactory;


public class MemberDao {
    //获取Session
    public Session getSession(){
        return HibernateSessionFactory.getSession();
    }
    //保存对象
    public void save(User transientInstance){
        Transaction tx=getSession().beginTransaction();//事务开始
        getSession().save(transientInstance);
        tx.commit();
    }
    //由属性获取对象
    public List findByProperty(String propertyName,Object value){
        String queryString="from User as model where model."+propertyName+"=?";
        Query queryObject=getSession().createQuery(queryString);//使用query接口
        queryObject.setParameter(0, value);
        return queryObject.list();
    }
}