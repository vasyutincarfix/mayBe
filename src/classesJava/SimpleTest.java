package classesJava;

import org.apache.log4j.BasicConfigurator;

import java.sql.SQLException;

public class SimpleTest {

    public static void main(String[] args) throws SQLException, DBSystemException, ClassNotFoundException, NotUniqueUserLoginException, NotUniqueUserEmaiException {
//        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.DBCP);
        BasicConfigurator.configure();
//        PropertyConfigurator.configure("/home/sysadmin/projects/mayBe/src/classesJava/resourses/log4j.properties");
        UserDao dao = new UserDaoJdbs();
        User user = dao.selectById(7);
        System.out.println(user);
//        List<User> listUsers = dao.selectAll();
////        dao.deleteById(7);
//       User user = new User();
//       user.setId(20);user.setLogin("ffFD");user.setEmail("fDffF");user.setUsercol("Dcoll");
////       dao.insert(user);
//        for (User us:listUsers) {
//            System.out.println(us);
//        }
//        System.out.println(listUsers.size());
    }
}
