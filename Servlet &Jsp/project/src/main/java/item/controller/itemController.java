package item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import item.model.Item;
import item.service.itemService;
import item.service.impl.itemServiceImpl;

@WebServlet("/itemController")
public class itemController extends HttpServlet {
	
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	
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
		doGet(request, response);
		
	}

	
	private void showItems(HttpServletRequest request, HttpServletResponse response) {
		try {
			itemService itemService = new itemServiceImpl(dataSource);
			List<Item> items = itemService.getItems();  
			
			request.setAttribute("allItems", items);
			
			request.getRequestDispatcher("show-items.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("ex => " + e.getMessage());
		}
		
	}


	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			itemService itemService = new itemServiceImpl(dataSource);
			
			Long id = Long.parseLong(request.getParameter("id"));
			Item item = itemService.getItem(id);
			request.setAttribute("item", item);
			
			request.getRequestDispatcher("update-item.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			System.out.println("ex => " + e.getMessage());
		}

	}


	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        itemService itemService = new itemServiceImpl(dataSource);
	        long id = Long.parseLong(request.getParameter("id"));
	        String name = request.getParameter("name");
	        Double price = Double.parseDouble(request.getParameter("price"));
	        Integer totalNumber = Integer.parseInt(request.getParameter("totalNumber"));

	        // 1. Check for duplicates (excluding this item's own ID)
	        if (itemService.isNameExistsForOtherId(name, id)) {
	            request.setAttribute("status", "duplicate");
	            updateItem(request, response); 
	            return; // Stop here if it's a duplicate
	        }

	        // 2. Perform the update
	        Item item = new Item(id, name, price, totalNumber);
	        if (itemService.updateItem(item)) {
	            request.setAttribute("status", "updateSuccess");
	            // Forward back to update-item.jsp to trigger the success popup
	            request.getRequestDispatcher("update-item.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        System.out.println("Update Error: " + e.getMessage());
	    }
	}


	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			itemService itemService = new itemServiceImpl(dataSource);
			
			Long id = Long.parseLong(request.getParameter("id"));
			
			Boolean isItemDeleted = itemService.removeItem(id);
			
			if (isItemDeleted) {
				showItems(request, response);
			}
		} catch (Exception e) {
			System.out.println("ex => " + e.getMessage());
		}
		
		
	}


	private void addItem(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        itemService itemService = new itemServiceImpl(dataSource);
	        String name = request.getParameter("name");
	        
	        // 1. Check for duplicates
	        if (itemService.isNameExists(name)) { // You must implement this in ServiceImpl
	            request.setAttribute("status", "duplicate");
	            request.getRequestDispatcher("add-item.jsp").forward(request, response);
	            return;
	        }

	        // 2. Existing logic to create item
	        Double price = Double.parseDouble(request.getParameter("price"));
	        Integer totalNumber = Integer.parseInt(request.getParameter("totalNumber"));
	        Item item = new Item(name, price, totalNumber);
	        
	        if (itemService.createItem(item)) {
	            request.setAttribute("status", "success");
	            request.getRequestDispatcher("add-item.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        System.out.println("ex => " + e.getMessage());
	    }
	}
		
		
	}


	


