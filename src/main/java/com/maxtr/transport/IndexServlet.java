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
import java.util.Date;

@WebServlet(name = "indexServlet", value = "")
public class IndexServlet extends HttpServlet {
    private String pageName;
    Database database;

    @Override
    public void init() {
        pageName = "main";
        database = new Database();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        request.setAttribute("tableData", database.getAll());
        request.setAttribute("pageName", pageName);
        engine.process("index.html", context, response.getWriter());
    }
}