package classesJava.servlet;

import classesJava.User;

import java.util.*;

public class QuizDaoMock implements QuizDao {
   public static String string = "WORD";

    public Map<Integer, User> memory = new HashMap<>();

    public QuizDaoMock() {
        this.memory.put(1, new User(1,"Login1", "Email1", "col1"));
        this.memory.put(2, new User(2,"Login2", "Email2", "col2"));
        this.memory.put(3, new User(3,"Login3", "Email3", "col3"));
    }



    @Override

    public User selectByID(int id) {


        return memory.get(id);
    }

    @Override
    public List<User> selectAll() {
        return new ArrayList<User>(memory.values());
        }


    }


