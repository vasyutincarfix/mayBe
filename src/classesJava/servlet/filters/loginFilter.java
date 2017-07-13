package classesJava.servlet.filters;


import classesJava.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(true);
        User user = new User();
        user.setLogin("user_login");
        user.setEmail("asd");
        session.setAttribute("", user);

        String locationRedirectTo = httpRequest.getParameter(loginFilter.)
    }

    @Override
    public void destroy() {

    }
}
