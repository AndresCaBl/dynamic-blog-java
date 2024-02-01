package blogapp1493150package.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

import blogapp1493150package.model.dao.CategoryDAO;
import blogapp1493150package.model.dao.PostDAO;
import blogapp1493150package.model.bean.Category;
import blogapp1493150package.model.bean.Post;
/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() {
        // TODO Auto-generated constructor stub
    }
    @Override
   	public void init() {
    	catDAO = new CategoryDAO();
   	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}
		try {
			switch (action) {
			case "categories":
				showCategories(request, response);
				break;
			case "newCat":
				newCategory(request, response);
				break;
			case "newC":
				showNewCategory(request, response);
				break;
			default:
				showCategories(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	private void showNewCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("catform.jsp");
		dispatcher.forward(request, response);
		
	}
	private void newCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String catName = request.getParameter("catName");
		Category cat = new Category(catName);
		catDAO.newCat(cat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CategoriesServlet?action=categories");
		dispatcher.forward(request, response);
		
	}
	private void showCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> allCategories = catDAO.selectAllCategories();
		request.setAttribute("categories", allCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("categories.jsp");
		dispatcher.forward(request, response);
	}

}
