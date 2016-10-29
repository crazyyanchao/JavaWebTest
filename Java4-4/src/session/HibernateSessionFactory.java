package session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {
    private static String CONFIG_FILE_LOCATION="/hibernate.cfg.xml";
    private static final ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
    private static Configuration configuration=new Configuration();
    //创建Configuration实例
    private static SessionFactory sessionFactory;
    private static String configFile=CONFIG_FILE_LOCATION;
    //初始化sessionfactory，只在第一次加载的时候执行
    static{
//读取解析配置文件
        configuration.configure(configFile);
//构件sessionFactory对象
        sessionFactory=configuration.buildSessionFactory();
    }
    //获取session
    public static Session getSession(){
        Session session=(Session) threadLocal.get();
        if(session==null||!session.isOpen()){
            if(sessionFactory==null){
                rebuildSessionFactory();
            }
            session=(sessionFactory!=null)?sessionFactory.openSession():null;
            threadLocal.set(session);
        }
        return session;
    }
    public static void rebuildSessionFactory(){
        configuration.configure(configFile);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static void closeSession(){
        Session session=(Session)threadLocal.get();
        threadLocal.set(null);
        if(session!=null){
            session.close();
        }
    }
}