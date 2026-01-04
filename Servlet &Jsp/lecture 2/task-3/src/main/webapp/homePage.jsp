<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Home Page</h2>

<%
    String favPlace = request.getParameter("favPlace");

    if (favPlace != null) {
        Cookie placeCookie = new Cookie("favPlace", favPlace);
        placeCookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(placeCookie);
    }
    String savedPlace = null;
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("favPlace".equals(c.getName())) {
                savedPlace = c.getValue();
                break;
            }
        }
    }
%>

<p><b>Your Favorite Place:</b> <%= savedPlace != null ? savedPlace : "Not set yet" %></p>

</body>
</html>