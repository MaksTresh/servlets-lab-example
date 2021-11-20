package com.maxtr.transport;

import com.maxtr.transport.db.Database;
import com.maxtr.transport.db.TransportType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    private String pageName;
    Database database;

    @Override
    public void init() {
        pageName = "search";
        database = new Database();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchValue = request.getParameter("raceName");

        request.setAttribute("tableData", database.filter(searchValue));
        request.setAttribute("searchValue", searchValue);
        request.setAttribute("pageName", pageName);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}