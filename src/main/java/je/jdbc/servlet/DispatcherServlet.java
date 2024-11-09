package je.jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/flights");
//        req.setAttribute("dispatcher", true);
////        dispatcher.forward(req, resp);
//        dispatcher.include(req, resp);
//        Writer writer = resp.getWriter();
//        writer.write("<h1>Dispatcher Servlet</h1>");
        resp.sendRedirect("/flights");
    }
}
