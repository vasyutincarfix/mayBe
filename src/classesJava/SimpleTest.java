package classesJava;

import java.sql.SQLException;

public class SimpleTest {

    public static void main(String[] args) throws SQLException, DBSystemException, ClassNotFoundException {
        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.DBCP);
        UserDao dao = new UserDaoJdbs();
//        List<User> listUsers = dao.selectAll();
//        dao.deleteById(7);
       User user = new User();
       user.setId(6);user.setLogin("hjdg");user.setEmail("sfg");user.setUsercol("coll");
       dao.insert(user);
//        for (User us:listUsers) {
//            System.out.println(us);
//        }
//        System.out.println(listUsers.size());
    }
}
