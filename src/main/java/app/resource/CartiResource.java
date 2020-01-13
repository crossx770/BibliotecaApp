package app.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.dao.CartiDao;
import app.model.Carti;

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

public class CartiResource extends HttpServlet {

    private CartiDao cartiDao;

    @Override
    public void init() throws ServletException {
        cartiDao = new CartiDao();
    }

    @Override
    public void destroy() {
    }

    private String getCartiFromDb(HttpServletRequest request) throws JsonProcessingException {
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();

        String id1 = request.getQueryString();

        try {
            // Connect to the database
            cartiDao.connect();

            if (id1 != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Carti carti = cartiDao.findOne(id);
                //todoDao.delete_from_table(id);
                json = objectMapper.writeValueAsString(carti);
            } else {
                List<Carti> carti = cartiDao.findAll();
                json = objectMapper.writeValueAsString(carti);
            }

            // Disconnect from the database
            cartiDao.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return json;
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username").equals("admin")) {
            // set response content type

            response.setContentType("application/json");

//        String json = getTodoFromMemory(request);
            String json = getCartiFromDb(request);

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
                cartiDao.connect();


                int id = Integer.parseInt(request.getParameter("id"));
                cartiDao.deleteCarti(id);


                // Disconnect from the database
                cartiDao.disconnect();


            } catch (SQLException e) {
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
            Carti value = objectMapper.readValue(json, Carti.class);

            String name = value.getName();
            String author = value.getAuthor();
            String genre = value.getGenre();


            try {

                cartiDao.updateCarti(id, name, author, genre);

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
            Carti value = objectMapper.readValue(json, Carti.class);

            String name = value.getName();
            String author = value.getAuthor();
            String genre = value.getGenre();


            try {
                //response.sendRedirect("http://www.google.com");
                cartiDao.addCarti(name, author, genre);
                RequestDispatcher dispatcher = request.getRequestDispatcher("listCarti.html");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
