package classesJava;

import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.List;

public class SimpleTest {

    public static void main(String[] args) throws SQLException, DBSystemException, ClassNotFoundException, NotUniqueUserLoginException, NotUniqueUserEmaiException {
//        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.DBCP);
        PropertyConfigurator.configure("/home/sysadmin/projects/mayBe/src/classesJava/resourses/log4j.properties");
        UserDao dao = new UserDaoJdbs();
        List<User> listUsers = dao.selectAll();
//        dao.deleteById(7);
       User user = new User();
       user.setId(20);user.setLogin("ffFD");user.setEmail("fDffF");user.setUsercol("Dcoll");
//       dao.insert(user);
        for (User us:listUsers) {
            System.out.println(us);
        }
        System.out.println(listUsers.size());
    }
}
