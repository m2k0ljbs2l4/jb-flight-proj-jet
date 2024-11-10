<%--
  Created by IntelliJ IDEA.
  User: Raccoon
  Date: 10.11.2024
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>Список перелетов: </h1>
<ul>
    <c:if test="${not empty requestScope.flights}">
        <c:forEach var="flights" items="${requestScope.flights}">
            <li>
                <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id()}">${flight.id()}
            </li>
        </c:forEach>
    </c:if>
</ul>

</body>
</html>
