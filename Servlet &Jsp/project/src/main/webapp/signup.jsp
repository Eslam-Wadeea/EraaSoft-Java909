<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup Page</title>
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

        /* Signup Card Container */
        .signup-card {
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

        /* Form Section Styling */
        .form-section {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #4b4f56;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #dddfe2;
            border-radius: 6px;
            box-sizing: border-box; /* Crucial for width alignment */
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
            background-color: #42b72a; /* Green for "Create Account" */
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.2s;
            margin-top: 10px;
        }

        button:hover {
            background-color: #36a420;
        }

        /* Validation/Error Message Styling */
        .error {
            background-color: #ffebe8;
            border: 1px solid #dd3c10;
            color: #dd3c10;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 14px;
            text-align: center;
            font-weight: bold;
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

    <div class="signup-card">
        <h2>Create New Account</h2>

        <%-- Requirement: Show validation message if signup fails --%>
        <% 
            String errorMsg = (String) request.getAttribute("error");
            if (errorMsg != null) { 
        %>
            <div class="error"><%= errorMsg %></div>
        <% } %>

        <form action="UserController" method="POST">
            <%-- Hidden field to trigger handleSignup in your Servlet --%>
            <input type="hidden" name="action" value="signup">

            <div class="form-section">
                <label>Full Name:</label>
                <input type="text" name="name" placeholder="Enter your full name" required>
            </div>

            <div class="form-section">
                <label>Email (Username):</label>
                <input type="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-section">
                <label>Password:</label>
                <input type="password" name="password" placeholder="Create a password" required>
            </div>

            <button type="submit">Sign Up</button>
        </form>

        <div class="footer-text">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>

</body>
</html>