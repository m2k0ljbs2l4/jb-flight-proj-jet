package je.jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import je.jdbc.service.FlightService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8);
        try (var writer = response.getWriter()) {
            writer.write("<h1>Список полетов</h1>");
            writer.write("<ul>");
            flightService.findAll().stream().forEach(flightDto ->
                    writer.write("""
                            <li>
                            <a href="/tickets?flightId=%d"></a>
                            </li>
                            """.formatted(flightDto.id(), flightDto.description())));
            writer.write("</ul>");
        }
    }


}
