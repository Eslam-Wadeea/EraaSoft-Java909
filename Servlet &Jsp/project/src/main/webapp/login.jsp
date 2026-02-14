<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        /* General Page Styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Login Card Container */
        .login-card {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            color: #1c1e21;
            margin-bottom: 25px;
            font-size: 24px;
        }

        /* Form Grouping */
        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #4b4f56;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #dddfe2;
            border-radius: 6px;
            box-sizing: border-box; /* Ensures padding doesn't affect width */
            font-size: 16px;
        }

        input:focus {
            outline: none;
            border-color: #1877f2;
            box-shadow: 0 0 0 2px #e7f3ff;
        }

        /* Button Styling */
        button {
            width: 100%;
            padding: 12px;
            background-color: #1877f2;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #166fe5;
        }

        /* Validation Message Styling */
        .error-message {
            background-color: #ffebe8;
            border: 1px solid #dd3c10;
            color: #dd3c10;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 14px;
            text-align: center;
        }

        .footer-text {
            text-align: center;
            margin-top: 20px;
            color: #606770;
            font-size: 14px;
        }

        .footer-text a {
            color: #1877f2;
            text-decoration: none;
        }

        .footer-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="login-card">
        <h2>Login</h2>

        <%-- Requirement: Show validation message if login fails --%>
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="error-message"><%= error %></div>
        <% } %>

        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="login">

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit">Log In</button>
        </form>

        <div class="footer-text">
            Don't have an account? <a href="signup.jsp">Sign up here</a>
        </div>
    </div>

</body>
</html>