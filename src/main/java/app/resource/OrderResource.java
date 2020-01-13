package app.resource;

import app.dao.OrderDao;
import app.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderResource extends HttpServlet {

    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDao();
    }

    @Override
    public void destroy() {
    }

    private String getOrderFromDb(HttpServletRequest request) throws JsonProcessingException {
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();

        String id1 = request.getQueryString();

        try {
            // Connect to the database
            OrderDao.connect();
            HttpSession session = request.getSession(false);
            if(session.getAttribute("username").equals("admin")) {
                if (id1 != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Order order = orderDao.findOne(id);
                    //todoDao.delete_from_table(id);
                    json = objectMapper.writeValueAsString(order);
                } else {
                    List<Order> order = orderDao.findAll();
                    json = objectMapper.writeValueAsString(order);
                }
            }
            else {
                String username = (String) session.getAttribute("username");
                List<Order> order = orderDao.findAll(username);
                json = objectMapper.writeValueAsString(order);
            }

            // Disconnect from the database
            OrderDao.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null ) {
            // set response content type
            response.setContentType("application/json");

//        String json = getTodoFromMemory(request);
            String json = getOrderFromDb(request);

            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username").equals("admin")) {
            try {
                // Connect to the database
                orderDao.connect();


                int id = Integer.parseInt(request.getParameter("id"));
                orderDao.deleteOrder(id);


                // Disconnect from the database
                orderDao.disconnect();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username").equals("admin")) {
            String json = "";
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream reqInputStream = request.getInputStream();
            Scanner sc = new Scanner(reqInputStream, "UTF-8").useDelimiter("\\A");

            json = sc.next();
            Order value = objectMapper.readValue(json, Order.class);

            String userName = value.getUsername();
            String bookTitle = value.getBookTitle();
            String borrowDate = value.getBorrowDate();


            try {
                //response.sendRedirect("http://www.google.com");
                orderDao.addOrder(userName, bookTitle, borrowDate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("list.html");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username").equals("admin")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String json = "";
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream reqInputStream = request.getInputStream();
            Scanner sc = new Scanner(reqInputStream, "UTF-8").useDelimiter("\\A");

            json = sc.next();
            Order value = objectMapper.readValue(json, Order.class);

            String username = value.getUsername();
            String bookTitle = value.getBookTitle();
            String borrowDate = value.getBorrowDate();


            try {

                orderDao.updateOrder(id, username, bookTitle, borrowDate);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
