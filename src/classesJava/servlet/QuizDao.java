package classesJava.servlet;

import classesJava.User;
import java.util.List;

public interface QuizDao {
    public User selectByID(int id);
    public List<User> selectAll();
}
