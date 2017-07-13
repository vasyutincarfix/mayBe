package classesJava.servlet.controller;

import classesJava.User;
import classesJava.servlet.QuizDao;
import classesJava.servlet.QuizDaoMock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuizAllController extends HttpServlet {
    public static int idd;
    public static String logg;
    public static String emaill;
    public static String usercol;

    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_QUIZ = "user";
    public static final String PAGE_OK = "quiz.jsp";
    public static final String PAGE_ERROR = "quizAll.do";

    private QuizDao quizDao = new QuizDaoMock();
    //TransactionManager


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter(PARAM_ID));
        System.out.println(req.getAuthType());
        System.out.println(req.getMethod());
        System.out.println(req.getPathInfo());
        System.out.println(req.getQueryString());
        System.out.println(req.getRequestURI());
//        req.getRequestDispatcher("quiz.jsp").forward(req,resp);

       // resp.getWriter().write("<html><body>" + Math.random() + "<br>" +"<a href=\"index.jsp\" methods=\"POST\">back</a></body></html>");
        String idStr = req.getParameter(PARAM_ID);
        System.out.println("ID!!!!!!!!!!" + idStr);
        if(idStr != null && !idStr.equals("")) {
            Integer id  =  Integer.valueOf(idStr);
            User user = selectById(id);
            System.out.println(user);
            if (user != null) {
                idd = user.getId(); logg = user.getLogin(); emaill = user.getEmail(); usercol = user.getUsercol();
                req.setAttribute(ATTRIBUTE_QUIZ, user);
                System.out.println(req.getQueryString());
                req.getRequestDispatcher(PAGE_OK).forward(req,resp);
                return;
            }
            else resp.sendRedirect("allUsers.jsp");
        }



    }

    private User selectById(int id) {
        return quizDao.selectByID(id);
    }

}
