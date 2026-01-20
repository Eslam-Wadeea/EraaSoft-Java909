package item.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/itemController")
public class itemController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (Objects.isNull(action)) {
			action="showItems";
			
		}
		switch(action) {
		
		case"add-item":
			addItem(request,response);
			
			break;
		case"remove-item":
			removeItem(request,response);
			
			
			break;
		case"update-item":
			updateItem(request,response);
			
			
			break;
		case"show-item":
			showItem(request,response);
	
	
			break;
		case"show-items":
			showItems(request,response);
	
	
			break;
		default:
			showItems(request,response);
			
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	private void showItems(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1>showItems</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1>showItem</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1>updateItem</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1>removeItem</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1>addItem</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	

}
