package classesJava;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> selectAll() throws DBSystemException, ClassNotFoundException;
    public void deleteById(int id) throws DBSystemException, ClassNotFoundException;
    public void insert (User user) throws DBSystemException, ClassNotFoundException, NotUniqueUserLoginException, NotUniqueUserEmaiException;
    public User selectById(int id) throws DBSystemException, ClassNotFoundException, SQLException;
}
