package User.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import User.model.User;
import User.service.UserService;
import User.service.impl.UserServiceImpl;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
	    userService = new UserServiceImpl(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("login".equals(action)) {
	        handleLogin(request, response);
	    } else if ("signup".equals(action)) {
	        handleSignup(request, response);
	    } else if ("logout".equals(action)) {
	        handleLogout(request, response);
	    } else {
	        response.sendRedirect("login.jsp");
	    }
    }

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    if (userService.login(email, password)) {
	        HttpSession session = request.getSession();
	        session.setAttribute("isLoggedIn", true);
	        
	        Cookie userCookie = new Cookie("userEmail", email);
	        userCookie.setMaxAge(60 * 60);
	        response.addCookie(userCookie);

	        response.sendRedirect("main.jsp");
	    } else {
	        request.setAttribute("error", "Invalid username or password");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	}

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        if (userService.signup(user)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Signup failed. Email might already exist.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("isLoggedIn"); 
            session.invalidate(); 
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        response.sendRedirect("login.jsp");
    }
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
