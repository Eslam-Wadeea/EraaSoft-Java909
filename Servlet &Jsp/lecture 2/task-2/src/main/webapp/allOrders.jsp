<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>All Orders </h2>

<%
    ArrayList<String> orders =
            (ArrayList<String>) session.getAttribute("orders");

    if (orders == null) {
        orders = new ArrayList<>();
    }

    String food = request.getParameter("food");

    if (food != null && !food.trim().isEmpty()) {
        orders.add(food);
    }
    session.setAttribute("orders", orders);
%>

<ul>
<%
    for (String order : orders) {
%>
        <li><%= order %></li>
<%
    }
%>
</ul>

</body>
</html>