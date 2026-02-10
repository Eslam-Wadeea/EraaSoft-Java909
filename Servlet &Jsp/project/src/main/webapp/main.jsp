<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 
    Requirement: Session check. 
    If the session attribute is missing or false, redirect to login page.
--%>
<%
    if (session.getAttribute("isLoggedIn") == null || !(Boolean)session.getAttribute("isLoggedIn")) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #2c3e50;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            text-align: center;
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .btn-list {
            display: inline-block;
            padding: 12px 24px;
            background-color: #1877f2;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            margin-top: 20px;
        }
        /* Requirement: Logout button styling */
        .logout-btn {
            color: #ff4d4d;
            text-decoration: none;
            border: 1px solid #ff4d4d;
            padding: 8px 16px;
            border-radius: 4px;
            font-weight: bold;
        }
        .logout-btn:hover {
            background: #ff4d4d;
            color: white;
        }
    </style>
</head>
<body>

    <nav class="navbar">
        <h2>Dashboard</h2>
        <%-- Requirement: Logout button visible only when logged in --%>
        <a href="UserController?action=logout" class="logout-btn">Logout</a>
    </nav>

    <div class="container">
        <h1>Welcome!</h1>
        <p>You have successfully logged in to the main page.</p>
        
        <hr>
        
        <%-- Link to ItemController to prevent the NullPointer error you saw --%>
        <a href="itemController" class="btn-list">View Items List</a>
    </div>

</body>
</html>