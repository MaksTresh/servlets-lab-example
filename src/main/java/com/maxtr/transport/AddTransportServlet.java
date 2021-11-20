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

@WebServlet(name = "addServlet", value = "/add")
public class AddTransportServlet extends HttpServlet {
    private String pageName;
    Database database;

    @Override
    public void init() {
        pageName = "add";
        database = new Database();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        request.setAttribute("pageName", pageName);
        engine.process("add.html", context, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("pageName", pageName);
        DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        String raceName = request.getParameter("raceName").replaceAll("\"", "'");
        raceName = raceName.replaceAll("<", "(");
        raceName = raceName.replaceAll(">", ")");

        Date date;
        try {
            date = dateTimeFormatter.parse(request.getParameter("date"));
        } catch (ParseException e) {
            request.setAttribute("status", "error");
            doGet(request, response);
            return;
        }
        TransportType transportType;

        switch (request.getParameter("transportType")) {
            case "train":
                transportType = TransportType.TRAIN;
                break;
            case "airplane":
                transportType = TransportType.AIRPLANE;
                break;
            case "ship":
                transportType = TransportType.SHIP;
                break;
            default:
                request.setAttribute("status", "error");
                doGet(request, response);
                return;
        }

        TransportTime transportTime = new TransportTime(transportType, raceName, date);
        database.add(transportTime);

        request.setAttribute("status", "success");
        doGet(request, response);
    }
}