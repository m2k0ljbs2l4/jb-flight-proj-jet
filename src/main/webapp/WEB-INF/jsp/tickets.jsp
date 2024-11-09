<%@ page import="je.jdbc.service.TicketService" %>
<%@ page import="je.jdbc.dto.TicketDto" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<h1>Купленные билеты</h1>
<ul>
    <%
        TicketService ticketService = TicketService.getInstance();
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        for (TicketDto ticketDto : ticketService.findAllByFlightId(flightId)) {
            out.write(String.format("<li>%s</li>", ticketDto.seatNo()));
        }
    %>
</ul>
<h2>Hello World!</h2>
</body>
</html>
