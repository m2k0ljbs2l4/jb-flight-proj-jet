package je.jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import je.jdbc.service.TicketService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Long flightId = Long.valueOf(request.getParameter("flightId"));

        try (var writer = response.getWriter()) {
            writer.write("<h1>Купленные билеты</h1>");
            writer.write("<ul>");
            ticketService.findAllByFlightId(flightId).stream().forEach(ticketDto ->
                    writer.write("""
                            <li>%s</li>
                            """.formatted(ticketDto.seatNo())));
            writer.write("</ul>");
        }
    }
}
