package com.maxtr.transport;

import com.maxtr.transport.db.Database;
import com.maxtr.transport.db.TransportTime;
import com.maxtr.transport.db.TransportType;
import com.maxtr.transport.template_engine.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet(name = "removeServlet", value = "/remove")
public class RemoveTransportServlet extends HttpServlet {
    private String pageName;
    Database database;

    @Override
    public void init() {
        pageName = "remove";
        database = new Database();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        request.setAttribute("pageName", pageName);
        request.setAttribute("tableData", database.getAll());
        engine.process("remove.html", context, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("pageName", pageName);
        request.setAttribute("tableData", database.getAll());

        int elementID = Integer.parseInt(request.getParameter("transportElem"));
        database.remove(elementID);

        request.setAttribute("status", "success");
        doGet(request, response);
    }
}