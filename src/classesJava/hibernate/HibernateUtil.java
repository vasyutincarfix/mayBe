package classesJava.hibernate;

import classesJava.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
//            Configuration configuration = new Configuration();
//                    configuration.setProperty( "hibernate.connection.driver_class",
//                            "com.mysql.jdbc.Driver" )
//                    .setProperty( "hibernate.connection.url",
//                            "jdbc:mysql://" + Config.getDBUrl() )
//                    .setProperty( "hibernate.connection.username",
//                            Config.getDBLogin() )
//                    .setProperty( "hibernate.connection.password",
//                            Config.getDBPassword() )
//                    .setProperty( "hibernate.connection.pool_size", "1" )
//                    .setProperty( "hibernate.connection.autocommit", "false" )
//                    .setProperty( "hibernate.cache.provider_class",
//                            "org.hibernate.cache.NoCacheProvider" )
//                    .setProperty( "hibernate.cache.use_second_level_cache",
//                            "false" )
//                    .setProperty( "hibernate.cache.use_query_cache", "false" )
//                    .setProperty( "hibernate.dialect",
//                            "org.hibernate.dialect.MySQLDialect" )
//                    .setProperty( "hibernate.show_sql","true" )
//                    .setProperty( "hibernate.current_session_context_class",
//                            "thread" )
//                    .addPackage( "ru.miralab.db" )
//                    .addAnnotatedClass(hibernate.db.User.class)
//                    ;
//



// Create the SessionFactory
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
// Open a new Session, if this Thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null)
            s.close();
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.currentSession();
        Transaction tx= session.beginTransaction();


        User user = new User();
        user.setId(55);
        user.setLogin("sdjsdhfnlk");
        user.setEmail("dkj@com");
        user.setUsercol("coll");

        session.save(user);
        tx.commit();

        HibernateUtil.closeSession();
    }
}

