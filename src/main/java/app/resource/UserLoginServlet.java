package app.resource;

import app.dao.OrderDao;
import app.dao.UserDao;
import app.model.Order;
import app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;


    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    public UserLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        try {
            boolean authenticate = userDao.checkLogin(username, password);
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Not created yet. Now do so yourself.
                session.invalidate();
            }


            if (authenticate == true && username.equals("admin")) {

                session = request.getSession(true);
                session.setAttribute("username",username );
                response.sendRedirect("#/homeAdmin");
            } else if (authenticate == true ){
                session = request.getSession(true);
                session.setAttribute("username",username );
                response.sendRedirect("#/userList");
            } else {
                response.sendRedirect("#/error");
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}