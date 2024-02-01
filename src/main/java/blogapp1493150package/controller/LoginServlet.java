package blogapp1493150package.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blogapp1493150package.model.bean.Category;
import blogapp1493150package.model.bean.Login;
import blogapp1493150package.model.bean.Post;
import blogapp1493150package.model.dao.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO logindao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void init() {
    	logindao = new LoginDAO();
   	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost (request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}try {
			switch (action) {
			case "login":
				login(request, response);
				break;
			case "logout":
				logout(request, response);
				break;
			case "validateSession":
				validateSession(request, response);
				break;
			case "error":
				error(request, response);
				break;
			default:
				validateSession(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
				
	}

	private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/LoginServlet?action=ValidateSession&auth=false");
	}

	private void validateSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("Login.jsp");
		} else {
		    // Session is already created.
			response.sendRedirect("success.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("username"); 
		String upassword = request.getParameter("psw"); 
		System.out.println(uname);
		System.out.println(upassword);
		Login loginBean = new Login(); 
		loginBean.setUname(uname); 
		loginBean.setUpassword(upassword);
		try {
			if (LoginDAO.validate(loginBean)) {
				HttpSession session = request.getSession(); 
				session.setAttribute("username", uname); 
				response.sendRedirect("success.jsp");
			} else {
				// HttpSession session = request.getSession();
				response.sendRedirect("error.jsp");
			}
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	}

}
