package classesJava;

import java.util.List;

public interface UserDao {
    public List<User> selectAll() throws DBSystemException;
    public void deleteById(int id) throws DBSystemException;
    public void insert (User user) throws DBSystemException;
}
