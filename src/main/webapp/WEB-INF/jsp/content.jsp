<%--
  Created by IntelliJ IDEA.
  User: Raccoon
  Date: 09.11.2024
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>CONTENT РУССКИЙ</span>
    <p>Size: ${requestScope.flights.size()}</p>
    <p>description: ${requestScope.flights.get(0).description()}</p>
    <p>id: ${requestScope.flights[1].id()}</p>
    <p>Size: ${requestScope.flights.get(0).description()}</p>
    <p>JSESSIONID: ${cookie.get("JSESSIONID")}</p>
    <p>PARAM id: ${param.id}</p>
    <p>HEADER id: ${header["cookie"]}</p>
    <p>NOT EMPTY: ${not empty flights}</p>
</div>
</body>
</html>
