package classesJava;

import java.util.List;

public interface UserDao {
    public List<User> selectAll() throws DBSystemException, ClassNotFoundException;
    public void deleteById(int id) throws DBSystemException, ClassNotFoundException;
    public void insert (User user) throws DBSystemException, ClassNotFoundException, NotUniqueUserLoginException, NotUniqueUserEmaiException;
}
