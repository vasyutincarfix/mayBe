package classesJava;

import java.sql.SQLException;
import java.util.List;

public class SimpleTest {

    public static void main(String[] args) throws SQLException, DBSystemException {
        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.C3P0);

        UserDao dao = new UserDaoJdbs();


       User user = new User();
       user.setId(3);user.setLogin("hjdg");user.setEmail("sfg");user.setUsercol("coll");

//dao.selectAll();

//       dao.deleteById(7);
//       dao.deleteById(52);
        List<User> listUsers = dao.selectAll();
        for (User us:listUsers) {
            System.out.println(us);
        }
        System.out.println(listUsers.size());

    }
}
