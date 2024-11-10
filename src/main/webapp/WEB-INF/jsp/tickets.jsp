<%@ page import="je.jdbc.service.TicketService" %>
<%@ page import="je.jdbc.dto.TicketDto" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%@include file="header.jsp"%>
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
